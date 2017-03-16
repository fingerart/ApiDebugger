package io.chengguo.apidebugger.ui;

import com.google.common.collect.Sets;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;
import io.chengguo.apidebugger.engine.eventbus.DebuggerEventBus;
import io.chengguo.apidebugger.engine.eventbus.event.NoActionSessionsEvent;
import io.chengguo.apidebugger.ui.window.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * 顶层TAB标签面板
 *
 * Created by fingerart on 17/2/27.
 */
public class TabbedDebuggerWidget extends JPanel implements ITabbedDebuggerWidget {
    private Project mProject;
    private Disposable mParent;
    private JBPanel<JBPanel> mPanel;
    private IDebuggerTabs mTabs;
    private JComponent mInnerDebuggerWidget;

    public TabbedDebuggerWidget(Project project, Disposable disposable) {
        super(new BorderLayout());
        mProject = project;
        mParent = disposable;
        mPanel = new JBPanel<>(new BorderLayout());
        mPanel.add(this, BorderLayout.CENTER);
    }

    private IDebuggerTabs setupTabs() {
        IDebuggerTabs tabs = createTabPanel();
        tabs.addListener(createTabsListener());
        remove(mInnerDebuggerWidget);
        addTab(mInnerDebuggerWidget, tabs);
        add(tabs.getComponent(), BorderLayout.CENTER);
        mInnerDebuggerWidget = null;
        return tabs;
    }

    private DebuggerTabsIml.DebuggerTabListener createTabsListener() {
        return new DebuggerTabsIml.DebuggerTabListener() {
            @Override
            public void onLast() {
                removeTabbedPanel();
            }
        };
    }

    private void removeTabbedPanel() {
        mInnerDebuggerWidget = mTabs.getCurrentComponent();
        remove(mTabs.getComponent());
        mTabs = null;
        add(mInnerDebuggerWidget, BorderLayout.CENTER);
    }

    private IDebuggerTabs createTabPanel() {
        return new DebuggerTabsIml(mProject, mParent);
    }

    @Override
    public void createDebuggerSession() {
        JComponent innerDebuggerWidget = createInnerDebuggerWidget();
        if (mInnerDebuggerWidget == null && mTabs == null) {
            mInnerDebuggerWidget = innerDebuggerWidget;
            add(mInnerDebuggerWidget, BorderLayout.CENTER);
        } else {
            if (mTabs == null) {
                mTabs = setupTabs();
            }
            addTab(innerDebuggerWidget, mTabs);
        }
    }

    @Override
    public void closeCurrentDebuggerSession() {
        if (mTabs == null) {
            DebuggerEventBus.getDefault().post(new NoActionSessionsEvent());
        } else {
            mTabs.closeCurrentTab();
        }
    }

    private void addTab(JComponent innerDebuggerWidget, IDebuggerTabs tabs) {
        String uniqueName = generateUniqueName("Tab", tabs);//TODO 配置化
        tabs.addTab(innerDebuggerWidget, uniqueName);
    }

    private static String generateUniqueName(String suggestedName, IDebuggerTabs tabs) {
        final Set<String> names = Sets.newHashSet();
        for (int i = 0; i < tabs.getTabCount(); i++) {
            names.add(tabs.getTitleAt(i));
        }
        String newSdkName = suggestedName;
        int i = 0;
        while (names.contains(newSdkName)) {
            newSdkName = suggestedName + " (" + (++i) + ")";
        }
        return newSdkName;
    }

    private JComponent createInnerDebuggerWidget() {
        return new Test(mProject, mParent).container;
    }

    @Override
    public JComponent getComponent() {
        return mPanel;
    }
}
