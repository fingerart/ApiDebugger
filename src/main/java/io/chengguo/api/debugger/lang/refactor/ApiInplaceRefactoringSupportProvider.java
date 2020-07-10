package io.chengguo.api.debugger.lang.refactor;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.psi.ApiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Support for in-place refactoring is specified explicitly in a refactoring support provider
 */
public class ApiInplaceRefactoringSupportProvider extends RefactoringSupportProvider {

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement elementToRename, @Nullable PsiElement context) {
        System.out.println("ApiInplaceRefactoringSupportProvider.isMemberInplaceRenameAvailable " + "elementToRename = " + elementToRename + ", context = " + context);
        return elementToRename instanceof ApiNamedElement;
    }

    @Override
    public boolean isInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        System.out.println("ApiInplaceRefactoringSupportProvider.isInplaceRenameAvailable " + "element = " + element + ", context = " + context);
        return super.isInplaceRenameAvailable(element, context);
    }
}
