package io.chengguo.api.debugger.lang.refactor.rename;

import com.intellij.json.psi.JsonProperty;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.search.SearchScope;
import com.intellij.refactoring.rename.inplace.InplaceRefactoring;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenameHandler;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenamer;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.util.containers.NotNullList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class ApiVariableInplaceRenameHandler extends VariableInplaceRenameHandler {

    @Override
    protected boolean isAvailable(@Nullable PsiElement element, @NotNull Editor editor, @NotNull PsiFile file) {
        System.out.println("ApiVariableInplaceRenameHandler: element = " + element + ", editor = " + editor + ", file = " + file);
        return element instanceof JsonProperty;
    }

    @Nullable
    @Override
    protected VariableInplaceRenamer createRenamer(@NotNull PsiElement elementToRename, @NotNull Editor editor) {
        System.out.println("ApiVariableInplaceRenameHandler: elementToRename = " + elementToRename + ", editor = " + editor);
        return new VariableInplaceRenamer((PsiNamedElement) elementToRename, editor) {
            @Override
            protected void addReferenceAtCaret(Collection<PsiReference> refs) {
            }

            @Override
            protected boolean isReferenceAtCaret(PsiElement selectedElement, PsiReference ref) {
                return false;
            }

            @Override
            protected boolean notSameFile(@Nullable VirtualFile file, @NotNull PsiFile containingFile) {
                return false;
            }

            @Nullable
            @Override
            protected PsiElement checkLocalScope() {
                return super.checkLocalScope();
            }

            @Override
            public boolean performInplaceRefactoring(@Nullable LinkedHashSet<String> nameSuggestions) {
                System.out.println("performInplaceRefactoring: nameSuggestions = " + nameSuggestions);
                myNameSuggestions = nameSuggestions;
                if (InjectedLanguageUtil.isInInjectedLanguagePrefixSuffix(myElementToRename)) {
                    return false;
                }

                final FileViewProvider fileViewProvider = myElementToRename.getContainingFile().getViewProvider();
                VirtualFile file = getTopLevelVirtualFile(fileViewProvider);

                SearchScope referencesSearchScope = getReferencesSearchScope(file);

                final Collection<PsiReference> refs = collectRefs(referencesSearchScope);

                addReferenceAtCaret(refs);

                //no need to process further when file is read-only
                if (!CommonRefactoringUtil.checkReadOnlyStatus(myProject, myElementToRename.getContainingFile()))
                    return true;

                myEditor.putUserData(INPLACE_RENAMER, this);
                ourRenamersStack.push(this);

                final List<Pair<PsiElement, TextRange>> stringUsages = new NotNullList<>();
                collectAdditionalElementsToRename(stringUsages);
                return buildTemplateAndStart(refs, stringUsages, null, myElementToRename.getContainingFile());
            }
        };
    }
}