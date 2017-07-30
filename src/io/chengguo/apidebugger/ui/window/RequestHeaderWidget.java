package io.chengguo.apidebugger.ui.window;

import com.intellij.ui.table.JBTable;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.apidebugger.engine.utils.ViewUtil;
import io.chengguo.apidebugger.ui.custom.JBDebuggerTable;

import javax.swing.*;
import java.util.List;
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
        List<JBDebuggerTable.ItemInfo> list = ContainerUtil.list(new JBDebuggerTable.ItemInfo());
        headers = new JBDebuggerTable(list);
    }

    public Map<String, String> headers() {
        return ViewUtil.getTableContent(headers.getModel());
    }
}
