package io.chengguo.api.debugger.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class RunApiRequestAction extends ApiDebuggerBaseAction {
    public RunApiRequestAction() {
        super("允许接口");
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        showErrorBalloon("你点击了", e);
    }

    @Override
    public String getId() {
        return "Api.Debugger.RunApiRequest";
    }
}
