package io.chengguo.apidebugger.ui;

import com.intellij.ui.components.JBPanel;

import javax.swing.*;

/**
 * Created by fingerart on 17/2/27.
 */
public class ApiDebuggerWidget {
    private JBPanel<JBPanel> mPanel;

    public ApiDebuggerWidget() {
        mPanel = new JBPanel<>();
        mPanel.add(createInputUi());
    }

    private JComponent createInputUi() {
        JComboBox<Object> comboBox = new JComboBox<>(new String[]{"GET", "POST", "DELETE"});
        return comboBox;
    }

    public JComponent getComponent() {
        return mPanel;
    }
}
