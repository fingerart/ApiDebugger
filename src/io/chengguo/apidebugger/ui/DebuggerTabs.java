package io.chengguo.apidebugger.ui;

import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.JBEditorTabs;

import javax.swing.*;

/**
 * Created by fingerart on 17/2/27.
 */
public interface DebuggerTabs {
    DebuggerTabs addListener(DebuggerTabsIml.DebuggerTabListener listener);

    DebuggerTabs addTab(JComponent component, String name);

    int getTabCount();

    TabInfo getTabAt(int i);

    DebuggerTabs closeTab(int index);

    DebuggerTabs closeCurrentTab();

    JBEditorTabs getComponent();

    String getTitleAt(int i);

    JComponent getCurrentComponent();
}
