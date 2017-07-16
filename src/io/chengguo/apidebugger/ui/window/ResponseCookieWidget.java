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
        cookiesJBList.getEmptyText().setText("暂无Cookie");
        Vector listData = new Vector();
        listData.add("item1");
        listData.add("item2");
        listData.add("item3");
        listData.add("item4");
        listData.add("item5");
        cookiesJBList.setListData(listData);
    }
}
