package io.chengguo.api.debugger.actions;

import com.intellij.internal.statistic.collectors.fus.actions.persistence.ActionIdProvider;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class ApiDebuggerBaseAction extends AnAction implements ActionIdProvider {
    public ApiDebuggerBaseAction() {
    }

    public ApiDebuggerBaseAction(Icon icon) {
        super(icon);
    }

    public ApiDebuggerBaseAction(@Nls @Nullable String text) {
        super(text);
    }

    public ApiDebuggerBaseAction(@Nls @Nullable String text, @Nls @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    protected void showErrorBalloon(@NotNull String message, @NotNull AnActionEvent event) {
        Project project = event.getProject();
        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(message, MessageType.ERROR, null)
                .setFadeoutTime(1500L)
                .setDisposable((project == null) ? ProjectManager.getInstance().getDefaultProject() : project)
                .createBalloon()
                .show(JBPopupFactory.getInstance().guessBestPopupLocation(event.getDataContext()), Balloon.Position.below);
    }
}
