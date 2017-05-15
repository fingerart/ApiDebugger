package io.chengguo.apidebugger.ui.window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by fingerart on 17/5/16.
 */
public class RequestHeaderWidget {
    private final static String[] DEFAULT_COLUMN_NAMES = {"Key", "Value"};
    private final static String[][] DEFAULT_EMPTY_DATA = {{"", ""},{"", ""},{"", ""},{"", ""},{"", ""},{"", ""},{"", ""},{"", ""}};
    public JScrollPane container;
    private JTable headers;

    private void createUIComponents() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES);
        headers = new JTable(defaultTableModel);
        headers.getTableHeader().setReorderingAllowed(false);
    }
}
