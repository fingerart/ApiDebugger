package io.chengguo.apidebugger.ui.window;

import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.util.Vector;

/**
 * Created by fingerart on 17/5/27.
 */
public class ResponseCookieWidget {
    public JPanel container;
    private JBList cookiesJBList;

    public ResponseCookieWidget() {
        cookiesJBList.setEmptyText("Nothing cookie to show");
    }

    public void showCookies(Vector<String> headers) {
        cookiesJBList.setListData(headers);
    }
}
