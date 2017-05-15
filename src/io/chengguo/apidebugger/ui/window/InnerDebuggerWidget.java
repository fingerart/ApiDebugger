package io.chengguo.apidebugger.ui.window;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.tabs.TabInfo;
import groovy.ui.text.TextEditor;
import io.chengguo.apidebugger.ui.custom.JBDebuggerTab;

import javax.swing.*;

/**
 * Debugger inner widget
 * Created by fingerart on 17/2/28.
 */
public class InnerDebuggerWidget {
    private Project mProject;
    private Disposable parent;
    public JPanel container;
    private JComboBox method;
    private JButton send;
    private JBTextField uri;
    private JBDebuggerTab requestTabs;
    private JBDebuggerTab responseTabs;

    public InnerDebuggerWidget(Project mProject, Disposable parent) {
        this.mProject = mProject;
        this.parent = parent;
    }

    public JBDebuggerTab getJBDebuggerTab() {
        return requestTabs;
    }

    private void createUIComponents() {
        requestTabs = new JBDebuggerTab(mProject, ActionManager.getInstance(), IdeFocusManager.getInstance(mProject), parent);
        TabInfo headerInfo = new TabInfo(new RequestHeaderWidget().container);
        headerInfo.setText("header");
        requestTabs.addTab(headerInfo);

        TextEditor component2 = new TextEditor();
        component2.setText("body content");
        TabInfo info2 = new TabInfo(component2);
        info2.setText("body");
        requestTabs.addTab(info2);

        TextEditor component3 = new TextEditor();
        component3.setText("Other content");
        TabInfo info3 = new TabInfo(component3);
        info3.setText("other");
        requestTabs.addTab(info3);

        responseTabs = new JBDebuggerTab(mProject, ActionManager.getInstance(), IdeFocusManager.getInstance(mProject), parent);

        TextEditor component4 = new TextEditor();
        component4.setText("Other content");
        TabInfo info4 = new TabInfo(component4);
        info4.setText("other");
        responseTabs.addTab(info4);
    }

}
