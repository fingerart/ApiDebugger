package io.chengguo.apidebugger.ui.window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_COLUMN_NAMES;
import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_EMPTY_DATA;

/**
 * Created by fingerart on 17/5/17.
 */
public class BodyTypeFormData {
    public JPanel container;
    private JTable table1;

    private void createUIComponents() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES);
        table1 = new JTable(defaultTableModel);
        table1.getTableHeader().setReorderingAllowed(false);
    }
}
