package io.chengguo.apidebugger.ui;

import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.JBEditorTabs;

import javax.swing.*;

/**
 * Created by fingerart on 17/2/27.
 */
public interface IDebuggerTabs {
    IDebuggerTabs addListener(DebuggerTabsIml.DebuggerTabListener listener);

    IDebuggerTabs addTab(JComponent component, String name);

    int getTabCount();

    TabInfo getTabAt(int i);

    IDebuggerTabs closeTab(int index);

    IDebuggerTabs closeCurrentTab();

    JBEditorTabs getComponent();

    String getTitleAt(int i);

    JComponent getCurrentComponent();
}
