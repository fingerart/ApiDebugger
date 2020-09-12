package io.chengguo.api.debugger.lang.completion;

import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.ChooseByNameContributorEx;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileTypes.FileNameMatcher;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PatternCondition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.impl.PsiMultiReference;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.*;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.ProjectScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.PlatformIcons;
import com.intellij.util.ProcessingContext;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.FindSymbolParameters;
import io.chengguo.api.debugger.lang.environment.ApiEnvironmentIndex;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.ui.header.HttpHeaderDocumentation;
import io.chengguo.api.debugger.ui.header.HttpHeadersDictionary;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.*;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.psi.TokenType.BAD_CHARACTER;

/**
 * 完成贡献者
 * <br/>
 * 当输入中 或 按 ⌃Space 时的内容展示
 */
public class ApiCompletionContributor extends CompletionContributor {
    private static final Logger LOG = Logger.getInstance("#io.chengguo.api.debugger.lang.completion.ApiCompletionContributor");

    public static final List<String> METHODS = ContainerUtil.newArrayList("GET", "POST", "HEAD", "PUT", "DELETE", "CONNECT", "PATCH", "OPTIONS", "TRACE");
    public static final List<String> SCHEMES = ContainerUtil.newArrayList("http", "https");
    public static final List<String> DESCR_KEYS = ContainerUtil.newArrayList("title", "description");

    public ApiCompletionContributor() {
        extend(CompletionType.BASIC, psiElement(BAD_CHARACTER), MethodCompletionProvider.INSTANCE);
//        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_RELATIVE_FILE_PATH), FilePathCompletionProvider.INSTANCE);
        extend(CompletionType.BASIC, SchemeCompletionProvider.PLACE, SchemeCompletionProvider.INSTANCE);
        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_IDENTIFIER), VariableCompletionProvider.INSTANCE);
        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_HEADER_FIELD_NAME), HeaderNameProvider.INSTANCE);
        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_HEADER_FIELD_VALUE), HeaderValueProvider.INSTANCE);
        extend(CompletionType.BASIC, DescriptionKeyCompletionProvider.PLACE, DescriptionKeyCompletionProvider.INSTANCE);
    }

    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        super.beforeCompletion(context);
    }

    private static PatternCondition<PsiElement> debug() {
        return new PatternCondition<PsiElement>("debug") {
            @Override
            public boolean accepts(@NotNull PsiElement t, final ProcessingContext context) {
                System.out.println("t = " + t + ", context = " + context);
                return true;
            }
        };
    }

    private static class VariableCompletionProvider extends CompletionProvider<CompletionParameters> {
        public static final VariableCompletionProvider INSTANCE = new VariableCompletionProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            PsiElement element = parameters.getOriginalPosition();
            if (element != null) {
                TextRange range = element.getTextRange();
                if (range.contains(parameters.getOffset()) || range.getEndOffset() == parameters.getOffset()) {
                    int endOffset = parameters.getOffset() - range.getStartOffset();
                    result = result.withPrefixMatcher((endOffset != 0) ? element.getText().substring(0, endOffset) : "");
                }
                addCompletions(parameters, context, result, element);
            }
        }

        private void addCompletions(CompletionParameters parameters, ProcessingContext context, CompletionResultSet result, PsiElement element) {
            Project project = element.getProject();
            Collection<String> allVariables = ApiEnvironmentIndex.getAllVariables(project, element.getContainingFile());
            fillVariables(allVariables, element, result);
        }

        private void fillVariables(Collection<String> allVariables, PsiElement element, CompletionResultSet result) {
            CompletionResultSet resultSet = result.caseInsensitive();
            for (String variable : allVariables) {
                LookupElementBuilder lookupBuilder = LookupElementBuilder.create(variable)
                        .withIcon(PlatformIcons.VARIABLE_ICON)
                        .withInsertHandler(ApiSuffixInsertHandler.VARIABLE_OPTION);
                resultSet.addElement(lookupBuilder);
            }
        }
    }

    private static class MethodCompletionProvider extends CompletionProvider<CompletionParameters> {
        private static final MethodCompletionProvider INSTANCE = new MethodCompletionProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            CompletionResultSet resultSet = result.caseInsensitive();
            for (String method : METHODS) {
                LookupElementBuilder lookupBuilder = LookupElementBuilder.create(method)
                        .withIcon(PlatformIcons.METHOD_ICON)
                        .withBoldness(true)
                        .withInsertHandler(AddSpaceInsertHandler.INSTANCE);
                resultSet.addElement(lookupBuilder);
            }
        }
    }

    private static class SchemeCompletionProvider extends CompletionProvider<CompletionParameters> {
        private static final SchemeCompletionProvider INSTANCE = new SchemeCompletionProvider();
        private static final ElementPattern<? extends PsiElement> PLACE =
                psiElement(ApiTypes.Api_HOST_VALUE)
                        .andNot(
                                psiElement().withSuperParent(2, psiElement().withFirstChild(psiElement(ApiTypes.Api_SCHEME)))
                        );

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            CompletionResultSet resultSet = result.caseInsensitive();
            for (String scheme : SCHEMES) {
                LookupElementBuilder lookupBuilder = LookupElementBuilder.create(scheme)
                        .withBoldness(true)
                        .withIcon(PlatformIcons.FIELD_ICON)
                        .withInsertHandler(ApiSuffixInsertHandler.SCHEME);
                resultSet.addElement(lookupBuilder);
            }
        }
    }

    private static class HeaderNameProvider extends CompletionProvider<CompletionParameters> {
        private static final HeaderNameProvider INSTANCE = new HeaderNameProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            CompletionResultSet resultSet = result.caseInsensitive();
            for (HttpHeaderDocumentation header : HttpHeadersDictionary.getHeaders().values()) {
                LookupElementBuilder lookupBuilder = LookupElementBuilder.create(header, header.getName())
                        .withStrikeoutness(header.isDeprecated())
                        .withIcon(PlatformIcons.METHOD_ICON)
                        .withTypeIconRightAligned(true)
                        .withInsertHandler(ApiSuffixInsertHandler.FIELD_SEPARATOR);
                resultSet.addElement(lookupBuilder);
            }
        }
    }

    private static class HeaderValueProvider extends CompletionProvider<CompletionParameters> {
        private static final HeaderValueProvider INSTANCE = new HeaderValueProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            ApiHeaderField headerField = PsiTreeUtil.getParentOfType(parameters.getPosition(), ApiHeaderField.class);
            String fieldName = headerField != null ? headerField.getKey() : null;
            if (StringUtil.isNotEmpty(fieldName)) {
                CompletionResultSet resultSet = result.caseInsensitive();
                Collection<String> headerValues = HttpHeadersDictionary.getHeaderValues(parameters.getPosition().getProject(), fieldName);
                for (String headerValue : headerValues) {
                    resultSet.addElement(LookupElementBuilder.create(headerValue));
                }
                Collection<String> headerOptionNames = HttpHeadersDictionary.getHeaderOptionNames(fieldName);
                for (String headerOptionName : headerOptionNames) {
                    resultSet.addElement(LookupElementBuilder.create(headerOptionName).withInsertHandler(ApiSuffixInsertHandler.HEADER_OPTION));
                }
            }
        }
    }

    private static class DescriptionKeyCompletionProvider extends CompletionProvider<CompletionParameters> {
        private static final DescriptionKeyCompletionProvider INSTANCE = new DescriptionKeyCompletionProvider();
        private static final ElementPattern<? extends PsiElement> PLACE = psiElement(ApiTypes.Api_DESCRIPTION_KEY);

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            CompletionResultSet resultSet = result.caseInsensitive();
            for (String method : DESCR_KEYS) {
                LookupElementBuilder lookupBuilder = LookupElementBuilder.create(method)
                        .withIcon(PlatformIcons.CLASS_ICON)
                        .withBoldness(true)
                        .withInsertHandler(ApiSuffixInsertHandler.FIELD_SEPARATOR);
                resultSet.addElement(lookupBuilder);
            }
        }
    }

    private static class FilePathCompletionProvider extends CompletionProvider<CompletionParameters> {
        public static final FilePathCompletionProvider INSTANCE = new FilePathCompletionProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            result = result.caseInsensitive();
            PsiElement element = parameters.getPosition();
            Project project = element.getProject();
            PsiFile originalFile = element.getContainingFile().getOriginalFile();
            VirtualFile contextFile = originalFile.getVirtualFile();

            PsiReference psiReference = parameters.getPosition().getContainingFile().findReferenceAt(parameters.getOffset());
            Pair<FileReference, Boolean> fileReferencePair = getReference(psiReference);
            if (fileReferencePair != null) {
                FileReference first = fileReferencePair.getFirst();
                if (first == null) {
                    return;
                }

                FileReferenceSet set = first.getFileReferenceSet();
                int end = parameters.getOffset() - set.getElement().getTextRange().getStartOffset() - set.getStartInElement();
                String pathString = set.getPathString();
                if (pathString.length() < end) {
                    return;
                }

                String relativePrefix = getRelativePathPrefix(pathString);

                System.out.println(relativePrefix);
            }
            ProjectFileIndex index = ProjectRootManager.getInstance(project).getFileIndex();
            Module contextModule = index.getModuleForFile(contextFile);
            if (contextModule != null) {
                TreeSet<String> resultNames = new TreeSet<String>();
                processAllNames(project, fileName -> {
                    resultNames.add(fileName);
                    return true;
                });
                Iterator<String> resultNameIterator = resultNames.iterator();
                List<FileReferenceHelper> helpers = FileReferenceHelperRegistrar.getHelpers(originalFile);
                Iterator<FileReferenceHelper> helperIterator = helpers.iterator();
                GlobalSearchScope scope = ProjectScope.getProjectScope(project);
                while (resultNameIterator.hasNext()) {
                    String fileName = resultNameIterator.next();
                    PsiFile[] files = FilenameIndex.getFilesByName(project, fileName, scope);
                    for (PsiFile psiFile : files) {
                        VirtualFile virtualFile = psiFile.getVirtualFile();
                        List<FileReferenceHelper> referenceHelpers = new ArrayList<>();
                        while (helperIterator.hasNext()) {
                            FileReferenceHelper referenceHelper = helperIterator.next();
                            if (referenceHelper.isMine(project, virtualFile)) {
                                referenceHelpers.add(referenceHelper);
                            }
                        }
                        if (virtualFile != null && virtualFile.isValid()) {
                            result.addElement(new FilePathLookupItem(psiFile, referenceHelpers));
                        }
                    }
                }
            }
        }

        private static boolean filenameMatchesPrefixOrType(String fileName,
                                                           String prefix,
                                                           FileType[] suitableFileTypes,
                                                           int invocationCount) {
            boolean prefixMatched = prefix.length() == 0 || StringUtil.startsWithIgnoreCase(fileName, prefix);
            if (prefixMatched && (suitableFileTypes.length == 0 || invocationCount > 2)) return true;

            if (prefixMatched) {
                String extension = FileUtilRt.getExtension(fileName);
                if (extension.length() == 0) return false;

                for (FileType fileType : suitableFileTypes) {
                    for (FileNameMatcher matcher : FileTypeManager.getInstance().getAssociations(fileType)) {
                        if (matcher.acceptsCharSequence(fileName)) return true;
                    }
                }
            }

            return false;
        }

        private static String getRelativePathPrefix(@NotNull String path) {
            if (!path.startsWith("./") && !path.startsWith("../")) return null;

            int index = 0;
            char currentChar = path.charAt(index);

            while (currentChar == '.' || currentChar == '/') {
                index++;
                if (index >= path.length()) break;
                currentChar = path.charAt(index);
            }

            return path.substring(0, index);
        }

        private static boolean fileMatchesPathPrefix(@Nullable final PsiFileSystemItem file,
                                                     @Nullable VirtualFile stopParent,
                                                     @NotNull final List<String> pathPrefix) {
            if (file == null) return false;

            final List<String> contextParts = new ArrayList<>();
            PsiFileSystemItem parentFile = file;
            PsiFileSystemItem parent;
            while ((parent = parentFile.getParent()) != null &&
                    (stopParent == null || !Objects.equals(parent.getVirtualFile(), stopParent))) {
                if (parent.getName().length() > 0) contextParts.add(0, StringUtil.toLowerCase(parent.getName()));
                parentFile = parent;
            }

            final String path = StringUtil.join(contextParts, "/");

            int nextIndex = 0;
            for (@NonNls final String s : pathPrefix) {
                if ((nextIndex = path.indexOf(StringUtil.toLowerCase(s), nextIndex)) == -1) return false;
            }

            return true;
        }

        private static void processAllNames(@NotNull Project project, @NotNull Processor<String> processor) {
            for (ChooseByNameContributor contributor : ChooseByNameContributor.FILE_EP_NAME.getExtensionList()) {
                try {
                    if (contributor instanceof ChooseByNameContributorEx) {
                        ((ChooseByNameContributorEx) contributor).processNames(processor, FindSymbolParameters.searchScopeFor(project, false), null);
                    } else {
                        ContainerUtil.process(contributor.getNames(project, false), processor);
                    }
                } catch (ProcessCanceledException ex) {
                    // index corruption detected, ignore
                } catch (Exception ex) {
                    LOG.error(ex);
                }
            }
        }

        @Nullable
        private static Pair<FileReference, Boolean> getReference(final PsiReference original) {
            if (original == null) {
                return null;
            }

            if (original instanceof PsiMultiReference) {
                final PsiMultiReference multiReference = (PsiMultiReference) original;
                for (PsiReference reference : multiReference.getReferences()) {
                    if (reference instanceof FileReference) {
                        if (((FileReference) reference).getFileReferenceSet().supportsExtendedCompletion()) {
                            return Pair.create((FileReference) reference, false);
                        }
                    }
                }
            } else if (original instanceof FileReferenceOwner) {
                final PsiFileReference fileReference = ((FileReferenceOwner) original).getLastFileReference();
                if (fileReference instanceof FileReference) {
                    if (((FileReference) fileReference).getFileReferenceSet().supportsExtendedCompletion()) {
                        return Pair.create((FileReference) fileReference, true);
                    }
                }
            }

            return null;
        }

        public static class FilePathLookupItem extends LookupElement {
            private final String myName;
            private final String myPath;
            private final String myInfo;
            private final Icon myIcon;
            private final PsiFile myFile;
            private final List<? extends FileReferenceHelper> myHelpers;

            public FilePathLookupItem(@NotNull final PsiFile file, @NotNull final List<? extends FileReferenceHelper> helpers) {
                myName = file.getName();
                myPath = file.getVirtualFile().getPath();

                myHelpers = helpers;

                myInfo = FileInfoManager.getFileAdditionalInfo(file);
                myIcon = file.getIcon(0);

                myFile = file;
            }

            @SuppressWarnings({"HardCodedStringLiteral"})
            @Override
            public String toString() {
                return String.format("%s%s", myName, myInfo == null ? "" : " (" + myInfo + ")");
            }

            @NotNull
            @Override
            public Object getObject() {
                return myFile;
            }

            @Override
            @NotNull
            public String getLookupString() {
                return myName;
            }

            @Override
            public void handleInsert(@NotNull InsertionContext context) {
                context.commitDocument();
                if (myFile.isValid()) {
                    final PsiReference psiReference = context.getFile().findReferenceAt(context.getStartOffset());
                    final Pair<FileReference, Boolean> fileReferencePair = getReference(psiReference);
                    if (fileReferencePair != null) {
                        FileReference ref = fileReferencePair.getFirst();
                        context.setTailOffset(ref.getRangeInElement().getEndOffset() + ref.getElement().getTextRange().getStartOffset());
                        ref.bindToElement(myFile);
                    }
                }
            }

            @Override
            public void renderElement(LookupElementPresentation presentation) {
                final String relativePath = getRelativePath();

                final StringBuilder sb = new StringBuilder();
                if (myInfo != null) {
                    sb.append(" (").append(myInfo);
                }

                if (relativePath != null && !relativePath.equals(myName)) {
                    if (myInfo != null) {
                        sb.append(", ");
                    } else {
                        sb.append(" (");
                    }

                    sb.append(relativePath);
                }

                if (sb.length() > 0) {
                    sb.append(')');
                }

                presentation.setItemText(myName);

                if (sb.length() > 0) {
                    presentation.setTailText(sb.toString(), true);
                }

                presentation.setIcon(myIcon);
            }

            @Nullable
            private String getRelativePath() {
                final VirtualFile virtualFile = myFile.getVirtualFile();
                LOG.assertTrue(virtualFile != null);
                for (FileReferenceHelper helper : myHelpers) {
                    PsiFileSystemItem root = helper.findRoot(myFile.getProject(), virtualFile);
                    String path = PsiFileSystemItemUtil.findRelativePath(root, helper.getPsiFileSystemItem(myFile.getProject(), virtualFile));
                    if (path != null) return path;
                }
                return null;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                FilePathLookupItem that = (FilePathLookupItem) o;

                if (!myName.equals(that.myName)) return false;
                if (!myPath.equals(that.myPath)) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = myName.hashCode();
                result = 31 * result + myPath.hashCode();
                return result;
            }
        }
    }
}
