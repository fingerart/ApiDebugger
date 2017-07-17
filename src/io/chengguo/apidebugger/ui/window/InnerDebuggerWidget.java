package io.chengguo.apidebugger.ui.window;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.tabs.TabInfo;
import io.chengguo.apidebugger.presenter.DebuggerSession;
import io.chengguo.apidebugger.ui.custom.JBDebuggerTab;
import io.chengguo.apidebugger.ui.iview.IHttpView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Map;
import java.util.Vector;

import static io.chengguo.apidebugger.engine.utils.ViewUtil.setCursor;

/**
 * Debugger inner widget
 * Created by fingerart on 17/2/28.
 */
public class InnerDebuggerWidget implements IHttpView, ActionListener {
    private Project mProject;
    private Disposable parent;
    public JPanel container;
    private JComboBox method;
    private JBTextField uri;
    private JButton send;
    private JBSplitter jb;
    private JBDebuggerTab reqTabs;
    private JBDebuggerTab resTabs;
    private DebuggerSession mSession;
    private RequestBodyWidget requestBodyWidget;
    private RequestHeaderWidget requestHeaderWidget;
    private ResponseBodyWidget responseBodyWidget;
    private ResponseCookieWidget responseCookieWidget;
    private ResponseHeaderWidget responseHeaderWidget;

    public InnerDebuggerWidget(Project mProject, Disposable parent) {
        this.mProject = mProject;
        this.parent = parent;

        mSession = new DebuggerSession(this);

        setCursor(Cursor.HAND_CURSOR, method, send);
        method.addItemListener(e ->
                reqTabs.getTabAt(2).setEnabled(e.getStateChange() == ItemEvent.SELECTED && "POST".equals(e.getItem().toString())));
        send.addActionListener(this);

        //Request
        reqTabs = new JBDebuggerTab(mProject, ActionManager.getInstance(), IdeFocusManager.getInstance(mProject), parent);

        TabInfo reqAuthorInfo = new TabInfo(new RequestAuthorizationWidget().container);
        reqAuthorInfo.setText("Authorization");
        reqTabs.addTab(reqAuthorInfo);

        requestHeaderWidget = new RequestHeaderWidget();
        TabInfo reqHeaderInfo = new TabInfo(requestHeaderWidget.container);
        reqHeaderInfo.setText("Header");
        reqTabs.addTab(reqHeaderInfo);

        requestBodyWidget = new RequestBodyWidget(mProject);
        TabInfo reqBodyInfo = new TabInfo(requestBodyWidget.container);
        reqBodyInfo.setText("Body");
        reqBodyInfo.setEnabled(false);
        reqTabs.addTab(reqBodyInfo);

        //Response
        resTabs = new JBDebuggerTab(mProject, ActionManager.getInstance(), IdeFocusManager.getInstance(mProject), parent);

        responseBodyWidget = new ResponseBodyWidget(mProject);
        TabInfo resBodyInfo = new TabInfo(responseBodyWidget.container);
        resBodyInfo.setText("Body");
        resTabs.addTab(resBodyInfo);

        responseCookieWidget = new ResponseCookieWidget();
        TabInfo resCookiesInfo = new TabInfo(responseCookieWidget.container);
        resCookiesInfo.setText("Cookies");
        resTabs.addTab(resCookiesInfo);

        responseHeaderWidget = new ResponseHeaderWidget();
        TabInfo resHeadersInfo = new TabInfo(responseHeaderWidget.container);
        resHeadersInfo.setText("Headers");
        resTabs.addTab(resHeadersInfo);

        jb.setFirstComponent(reqTabs);
        jb.setSecondComponent(resTabs);
    }

    @Override
    public String method() {
        return method.getSelectedItem().toString();
    }

    @Override
    public String url() {
        return uri.getText();
    }

    @Override
    public Map<String, String> headers() {
        return requestHeaderWidget.headers();
    }

    @Override
    public String bodyType() {
        return requestBodyWidget.bodyType();
    }

    @Override
    public Map<String, String> bodyFormData() {

        return null;
    }

    @Override
    public Map<String, String> bodyUrlencode() {
        return requestBodyWidget.bodyUrlencode();
    }

    @Override
    public String bodyRaw() {
        return requestBodyWidget.bodyRaw();
    }

    @Override
    public String bodyBinary() {
        return requestBodyWidget.bodyBinary();
    }

    @Override
    public void showPretty(String text) {
        responseBodyWidget.showPretty(text);
    }

    @Override
    public void setCookies(Vector<String> headers) {
        responseCookieWidget.showCookies(headers);
    }

    @Override
    public void setHeaders(Vector<String> headers) {
        responseHeaderWidget.showHeaders(headers);
    }

    @Override
    public void showRaw(String text) {
        responseBodyWidget.showRaw(text);
    }

    @Override
    public void showPreview(String text) {
        responseBodyWidget.showPreview(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Send":
                mSession.execute();
                break;
        }
    }
}
