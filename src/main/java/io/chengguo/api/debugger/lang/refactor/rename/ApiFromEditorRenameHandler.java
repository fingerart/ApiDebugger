package io.chengguo.api.debugger.lang.refactor.rename;

import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.refactoring.rename.RenameHandler;
import io.chengguo.api.debugger.ui.preview.ApiFileViewEditor;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class ApiFromEditorRenameHandler implements RenameHandler {
    @Override
    public boolean isAvailableOnDataContext(@NotNull DataContext dataContext) {
        final Project project = CommonDataKeys.PROJECT.getData(dataContext);
        if (project == null) {
            return false;
        }
//        final ResourceBundle bundle = ResourceBundleUtil.getResourceBundleFromDataContext(dataContext);
////        if (bundle == null) {
////            return false;
////        }
        final FileEditor fileEditor = PlatformDataKeys.FILE_EDITOR.getData(dataContext);
        if (!(fileEditor instanceof ApiFileViewEditor)) {
            return false;
        }
//        final VirtualFile virtualFile = CommonDataKeys.VIRTUAL_FILE.getData(dataContext);
//        return virtualFile instanceof ApiAsVirtualFile;
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
