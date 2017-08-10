package io.chengguo.apidebugger.ui.window;

import com.intellij.ui.table.JBTable;
import io.chengguo.apidebugger.ui.custom.JBDebuggerTable;

import javax.swing.*;
import java.util.Map;

/**
 * Created by FingerArt on 17/5/25.
 */
public class RequestHeaderWidget {
    public JPanel container;
    private JBTable headers;

    public RequestHeaderWidget() {
    }

    private void createUIComponents() {
        headers = new JBDebuggerTable();
    }

    public Map<String, String> headers() {
        return ((JBDebuggerTable) headers).getKeyValue();
    }
}
