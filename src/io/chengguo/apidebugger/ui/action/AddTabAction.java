package io.chengguo.apidebugger.ui.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import io.chengguo.apidebugger.ui.ITabbedDebuggerWidget;

/**
 * Created by fingerart on 17/2/20.
 */
public class AddTabAction extends AnAction {

    private final ITabbedDebuggerWidget mDebuggerWidget;

    public AddTabAction(ITabbedDebuggerWidget debuggerWidget) {
        super("Add Tab", "Create New ApiDebugger Tab", AllIcons.General.Add);
        mDebuggerWidget = debuggerWidget;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        mDebuggerWidget.createDebuggerSession();
        System.out.println("AddTabAction.actionPerformed");
    }
}
