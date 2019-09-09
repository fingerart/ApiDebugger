package io.chengguo.api.debugger.ui.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.NotNull;

public abstract class ApiDebuggerBaseAction extends AnAction {
    protected void showErrorBalloon(@NotNull final String message, @NotNull final AnActionEvent event) {
        if (message == null) {
            $$$reportNull$$$0(0);
        }
        if (event == null) {
            $$$reportNull$$$0(1);
        }
        final Project project = event.getProject();
        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(message, MessageType.ERROR, null)
                .setFadeoutTime(1500L)
                .setDisposable((project == null) ? ProjectManager.getInstance().getDefaultProject() : project)
                .createBalloon()
                .show(JBPopupFactory.getInstance().guessBestPopupLocation(event.getDataContext()), Balloon.Position.below);
    }

    private static void $$$reportNull$$$0(final int n) {
        final String s = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        final Object[] array = new Object[3];
        switch (n) {
            default: {
                array[0] = "message";
                break;
            }
            case 1: {
                array[0] = "e";
                break;
            }
        }
        array[1] = "io/chengguo/api/debugger/ui/actions/ApiDebuggerBaseAction";
        array[2] = "showErrorBalloon";
        throw new IllegalArgumentException(String.format(s, array));
    }
}
