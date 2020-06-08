package io.chengguo.api.debugger.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class RunApiRequestAction extends ApiDebuggerBaseAction {

    public RunApiRequestAction(@Nls @Nullable String text, @Nls @Nullable String description, @Nullable Icon icon) {
        super(text, description, icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        System.out.println("RunApiRequestAction.actionPerformed");
    }

    @Override
    public String getId() {
        return "Api.Debugger.RunApiRequest";
    }

    public static class WithEnvDefault extends RunApiRequestAction {

        public WithEnvDefault() {
            super(ApiDebuggerBundle.message("api.debugger.editor.action.run.current"), ApiDebuggerBundle.message("api.debugger.editor.action.run.current"), AllIcons.RunConfigurations.TestState.Run);
        }
    }

    public static class WithEnvTest extends RunApiRequestAction {

        public WithEnvTest() {
            super("Run Api with test", ApiDebuggerBundle.message("api.debugger.editor.action.run.current"), AllIcons.RunConfigurations.TestState.Yellow2);
        }
    }
}
