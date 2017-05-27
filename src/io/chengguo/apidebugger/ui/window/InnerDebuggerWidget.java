package io.chengguo.apidebugger.ui.window;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.tabs.TabInfo;
import io.chengguo.apidebugger.ui.custom.JBDebuggerTab;

import javax.swing.*;
import java.awt.*;

import static io.chengguo.apidebugger.engine.utils.ViewUtil.setCursor;

/**
 * Debugger inner widget
 * Created by fingerart on 17/2/28.
 */
public class InnerDebuggerWidget {
    private Project mProject;
    private Disposable parent;
    public JPanel container;
    private JComboBox method;
    private JBTextField uri;
    private JButton send;
    private JBDebuggerTab reqTabs;
    private JBDebuggerTab resTabs;

    public InnerDebuggerWidget(Project mProject, Disposable parent) {
        this.mProject = mProject;
        this.parent = parent;

        setCursor(Cursor.HAND_CURSOR, method, send);
    }

    private void createUIComponents() {
        //Request
        reqTabs = new JBDebuggerTab(mProject, ActionManager.getInstance(), IdeFocusManager.getInstance(mProject), parent);

        TabInfo reqAuthorInfo = new TabInfo(new RequestAuthorizationWidget().container);
        reqAuthorInfo.setText("Authorization");
        reqTabs.addTab(reqAuthorInfo);

        TabInfo reqHeaderInfo = new TabInfo(new RequestHeaderWidget().container);
        reqHeaderInfo.setText("Header");
        reqTabs.addTab(reqHeaderInfo);

        TabInfo reqBodyInfo = new TabInfo(new RequestBodyWidget(mProject).container);
        reqBodyInfo.setText("Body");
        reqTabs.addTab(reqBodyInfo);

        //Response
        resTabs = new JBDebuggerTab(mProject, ActionManager.getInstance(), IdeFocusManager.getInstance(mProject), parent);

        TabInfo resBodyInfo = new TabInfo(new ResponseBodyWidget().container);
        resBodyInfo.setText("Body");
        resTabs.addTab(resBodyInfo);

        TabInfo resCookiesInfo = new TabInfo(new ResponseCookieWidget().container);
        resCookiesInfo.setText("Cookies");
        resTabs.addTab(resCookiesInfo);

        TabInfo resHeadersInfo = new TabInfo(new ResponseHeaderWidget().container);
        resHeadersInfo.setText("Headers");
        resTabs.addTab(resHeadersInfo);
    }

}
