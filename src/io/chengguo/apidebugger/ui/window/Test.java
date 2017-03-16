package io.chengguo.apidebugger.ui.window;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.tabs.TabInfo;
import groovy.ui.text.TextEditor;
import io.chengguo.apidebugger.ui.custom.JBDebuggerButton;
import io.chengguo.apidebugger.ui.custom.JBDebuggerTab;

import javax.swing.*;

/**
 * Created by fingerart on 17/2/28.
 */
public class Test {
    public JPanel container;
    private JBDebuggerTab jBDebuggerTab;
    private JComboBox comboBox1;
    private JButton sendButton;
    private JBTextField JBTextField1;
    private JBDebuggerButton helloJBDebuggerButton;
    private Project mProject;
    private Disposable parent;

    public Test(Project mProject, Disposable parent) {
        this.mProject = mProject;
        this.parent = parent;
    }

    public JBDebuggerTab getJBDebuggerTab() {
        return jBDebuggerTab;
    }

    private void createUIComponents() {
        jBDebuggerTab = new JBDebuggerTab(mProject, ActionManager.getInstance(), IdeFocusManager.getInstance(mProject), parent);
        TextEditor component1 = new TextEditor();
        component1.setText("header content");
        TabInfo info1 = new TabInfo(component1);
        info1.setText("header");
        jBDebuggerTab.addTab(info1);

        TextEditor component2 = new TextEditor();
        component2.setText("body content");
        TabInfo info2 = new TabInfo(component2);
        info2.setText("body");
        jBDebuggerTab.addTab(info2);

        TextEditor component3 = new TextEditor();
        component3.setText("Other content");
        TabInfo info3 = new TabInfo(component3);
        info3.setText("other");
        jBDebuggerTab.addTab(info3);
    }

}
