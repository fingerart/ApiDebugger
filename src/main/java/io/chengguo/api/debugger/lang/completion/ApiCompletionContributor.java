package io.chengguo.api.debugger.lang.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PatternCondition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.PlatformIcons;
import com.intellij.util.ProcessingContext;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.environment.ApiEnvironmentIndex;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.ui.header.HttpHeaderDocumentation;
import io.chengguo.api.debugger.ui.header.HttpHeadersDictionary;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

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
}
