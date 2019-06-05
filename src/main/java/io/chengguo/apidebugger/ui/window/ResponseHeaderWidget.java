package io.chengguo.apidebugger.ui.window;

import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.util.Vector;

/**
 * Created by fingerart on 17/5/27.
 */
public class ResponseHeaderWidget {
    public JPanel container;
    private JBList headerJBList;

    public ResponseHeaderWidget() {
        headerJBList.setEmptyText("Nothing header to show");
    }

    public void showHeaders(Vector<String> headers) {
        headerJBList.setListData(headers);
    }
}
