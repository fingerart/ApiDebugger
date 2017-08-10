package io.chengguo.apidebugger.ui.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import io.chengguo.apidebugger.ui.ITabbedDebuggerWidget;

/**
 * Created by fingerart on 17/2/20.
 */
public class CloseTabAction extends AnAction {
    private final ITabbedDebuggerWidget mDebuggerWidget;

    public CloseTabAction(ITabbedDebuggerWidget debuggerWidget) {
        super("Close Tab", "Close ApiDebugger Session", AllIcons.Actions.Delete);
        mDebuggerWidget = debuggerWidget;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        mDebuggerWidget.closeCurrentDebuggerSession();
        System.out.println("CloseTabAction.actionPerformed");
    }
}
