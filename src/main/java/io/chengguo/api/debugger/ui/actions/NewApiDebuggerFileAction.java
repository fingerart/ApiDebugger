package io.chengguo.api.debugger.ui.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import io.chengguo.api.debugger.lang.ApiFileType;

/**
 * Create .api file action
 */
public class NewApiDebuggerFileAction extends CreateFileFromTemplateAction {
    private static final String NAME;
    private static final String TITLE;
    private static final String DESCRIPTION;

    static {
        NAME = ApiDebuggerBundle.message("api.debugger.action.name");
        TITLE = ApiDebuggerBundle.message("api.debugger.new.file.api.title");
        DESCRIPTION = ApiDebuggerBundle.message("api.debugger.new.file.api.description");
    }

    public NewApiDebuggerFileAction() {
        super(TITLE, DESCRIPTION, ApiDebuggerIcons.API_FILE_TYPE);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory psiDirectory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle(TITLE);
        ApiFileType fileType = ApiFileType.INSTANCE;
        builder.addKind(fileType.getName(), fileType.getIcon(), fileType.getName() + "." + fileType.getDefaultExtension());
    }

    @Override
    protected String getActionName(PsiDirectory psiDirectory, String s, String s1) {
        return NAME;
    }
}
