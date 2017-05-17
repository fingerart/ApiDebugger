package io.chengguo.apidebugger.ui.window;

import io.chengguo.apidebugger.engine.utils.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_COLUMN_NAMES;
import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_EMPTY_DATA;

/**
 * Created by fingerart on 17/5/16.
 */
public class RequestHeaderWidget {
    public JPanel container;
    public JScrollPane headersContainer;
    private JTable headers;

    private void createUIComponents() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES);
        headers = new JTable(defaultTableModel);
        headers.getTableHeader().setReorderingAllowed(false);
    }
}
