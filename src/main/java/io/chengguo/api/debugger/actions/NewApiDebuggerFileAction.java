package io.chengguo.api.debugger.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import io.chengguo.api.debugger.lang.ApiFileType;
import org.jetbrains.annotations.NotNull;

/**
 * Create .api file action
 */
public class NewApiDebuggerFileAction extends CreateFileFromTemplateAction {
    private static final String NAME = ApiDebuggerBundle.message("api.debugger.action.name");
    private static final String TITLE = ApiDebuggerBundle.message("api.debugger.new.file.api.title");
    private static final String DESCRIPTION = ApiDebuggerBundle.message("api.debugger.new.file.api.description");

    public NewApiDebuggerFileAction() {
        super(TITLE, DESCRIPTION, ApiDebuggerIcons.API_FILE_TYPE);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory psiDirectory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle(TITLE);
        ApiFileType apiFileType = ApiFileType.INSTANCE;
        builder.addKind(apiFileType.getName(), apiFileType.getIcon(), apiFileType.getName() + "." + apiFileType.getDefaultExtension());
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NotNull String newName, String templateName) {
        return NAME;
    }
}
