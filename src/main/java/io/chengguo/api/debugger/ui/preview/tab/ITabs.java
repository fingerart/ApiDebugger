package io.chengguo.api.debugger.ui.preview.tab;

import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.JBEditorTabs;

import javax.swing.*;

public interface ITabs {
    ITabs addListener(TabListener listener);

    ITabs addTab(JComponent component, String name);

    int getTabCount();

    TabInfo getTabAt(int i);

    ITabs closeTab(int index);

    ITabs closeCurrentTab();

    JBEditorTabs getComponent();

    String getTitleAt(int i);

    JComponent getCurrentComponent();
}