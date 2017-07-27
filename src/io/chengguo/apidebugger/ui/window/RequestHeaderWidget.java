package io.chengguo.apidebugger.ui.window;

import com.intellij.ui.table.JBTable;
import io.chengguo.apidebugger.engine.utils.ViewUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_COLUMN_NAMES;
import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_EMPTY_DATA;

/**
 * Created by FingerArt on 17/5/25.
 */
public class RequestHeaderWidget {
    public JPanel container;
    private JBTable headers;

    public RequestHeaderWidget() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES);
        headers.setModel(defaultTableModel);
        headers.getTableHeader().setReorderingAllowed(false);
    }

    public Map<String, String> headers() {
        return ViewUtil.getTableContent(headers.getModel());
    }
}
