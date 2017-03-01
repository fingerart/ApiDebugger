package io.chengguo.apidebugger.ui.custom;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Created by fingerart on 17/2/28.
 */
public class JBDebuggerButton extends JButton {
    public JBDebuggerButton() {
        System.out.println("JBDebuggerButton.JBDebuggerButton");
        setUI(new BasicButtonUI() {
            
        });
    }

    public JBDebuggerButton(Icon icon) {
        super(icon);
    }

    public JBDebuggerButton(String text) {
        super(text);
    }

    public JBDebuggerButton(Action a) {
        super(a);
    }

    public JBDebuggerButton(String text, Icon icon) {
        super(text, icon);
    }
}
