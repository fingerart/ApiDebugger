package io.chengguo.api.debugger.lang.refactoring.rename;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.refactoring.rename.RenameHandler;
import org.jetbrains.annotations.NotNull;

public class ResourceBundleFromEditorRenameHandler implements RenameHandler {
    @Override
    public boolean isAvailableOnDataContext(@NotNull DataContext dataContext) {
        return false;
    }

    @Override
    public boolean isRenaming(@NotNull DataContext dataContext) {
        return false;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file, DataContext dataContext) {

    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiElement[] elements, DataContext dataContext) {

    }
}
