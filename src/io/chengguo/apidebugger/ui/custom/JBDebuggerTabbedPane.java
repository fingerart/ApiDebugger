package io.chengguo.apidebugger.ui.custom;

import com.intellij.ui.components.JBTabbedPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fingerart on 17/5/25.
 */
public class JBDebuggerTabbedPane extends JBTabbedPane{

    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        super.insertTab(title, icon, component, tip, index);
    }
}
