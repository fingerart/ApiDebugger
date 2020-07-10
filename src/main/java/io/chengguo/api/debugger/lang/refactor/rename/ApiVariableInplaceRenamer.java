package io.chengguo.api.debugger.lang.refactor.rename;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenamer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class ApiVariableInplaceRenamer extends VariableInplaceRenamer {
    public ApiVariableInplaceRenamer(@NotNull PsiNamedElement elementToRename, @NotNull Editor editor) {
        super(elementToRename, editor);
    }

    public ApiVariableInplaceRenamer(@Nullable PsiNamedElement elementToRename, @NotNull Editor editor, @NotNull Project project) {
        super(elementToRename, editor, project);
    }

    public ApiVariableInplaceRenamer(@Nullable PsiNamedElement elementToRename, @NotNull Editor editor, @NotNull Project project, @Nullable String initialName, @Nullable String oldName) {
        super(elementToRename, editor, project, initialName, oldName);
    }

    @Override
    protected boolean notSameFile(@Nullable VirtualFile file, @NotNull PsiFile containingFile) {
        return false;
//        return super.notSameFile(file, containingFile);
    }
}
