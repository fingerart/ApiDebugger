package io.chengguo.api.debugger.lang.refactor;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Support for in-place refactoring is specified explicitly in a refactoring support provider
 */
public class JsonInplaceRefactoringSupportProvider extends RefactoringSupportProvider {

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement elementToRename, @Nullable PsiElement context) {
        System.out.println("JsonInplaceRefactoringSupportProvider.isMemberInplaceRenameAvailable " + "elementToRename = " + elementToRename + ", context = " + context);
        return true;
    }


}
