package io.chengguo.apidebugger.ui;

import javax.swing.*;

/**
 * Created by fingerart on 17/2/27.
 */
public interface DebuggerWidget {

    /**
     * 创建一个新的Debugger会话
     */
    void createDebuggerSession();

    /**
     * 关闭当前Debugger会话
     */
    void closeCurrentDebuggerSession();

    /**
     * 获取windowTool顶层Component
     *
     * @return
     */
    JComponent getComponent();
}
