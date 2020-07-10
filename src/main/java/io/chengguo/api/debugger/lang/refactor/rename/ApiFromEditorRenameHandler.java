package io.chengguo.api.debugger.lang.refactor.rename;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.refactoring.rename.RenameHandler;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenameHandler;
import org.jetbrains.annotations.NotNull;

/**
 * @see VariableInplaceRenameHandler
 */
@Deprecated
public class ApiFromEditorRenameHandler implements RenameHandler {

    @Override
    public boolean isAvailableOnDataContext(@NotNull DataContext dataContext) {
        System.out.println("ApiFromEditorRenameHandler.isAvailableOnDataContext " + "dataContext = " + dataContext);
//        final Project project = CommonDataKeys.PROJECT.getData(dataContext);
//        if (project == null) {
//            return false;
//        }
//        final ResourceBundle bundle = ResourceBundleUtil.getResourceBundleFromDataContext(dataContext);
////        if (bundle == null) {
////            return false;
////        }
//        final FileEditor fileEditor = PlatformDataKeys.FILE_EDITOR.getData(dataContext);
//        if (!(fileEditor instanceof ApiFileViewEditor)) {
//            return false;
//        }
//        final VirtualFile virtualFile = CommonDataKeys.VIRTUAL_FILE.getData(dataContext);
//        return virtualFile instanceof ApiAsVirtualFile;
        return true;
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
