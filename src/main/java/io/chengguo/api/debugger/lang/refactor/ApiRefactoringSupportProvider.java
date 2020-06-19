package io.chengguo.api.debugger.lang.refactor;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.psi.ApiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApiRefactoringSupportProvider extends RefactoringSupportProvider {

    /**
     * 支持在原始位置重命名
     */
    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, @Nullable PsiElement context) {
        return element instanceof ApiNamedElement;
    }
}
