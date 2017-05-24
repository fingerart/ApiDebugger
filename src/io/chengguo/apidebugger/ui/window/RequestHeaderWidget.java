package io.chengguo.apidebugger.ui.window;

import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_COLUMN_NAMES;
import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_EMPTY_DATA;

/**
 * Created by fingerart on 17/5/25.
 */
public class RequestHeaderWidget {
    public JPanel container;
    private JBTable headers;

    public RequestHeaderWidget() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES);
        headers.setModel(defaultTableModel);
        headers.getTableHeader().setReorderingAllowed(false);

    }
}
