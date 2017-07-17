package io.chengguo.apidebugger.ui.iview;

import java.util.Map;
import java.util.Vector;

public interface IHttpView {

    /**
     * 获取Method
     *
     * @return
     */
    String method();

    /**
     * 获取URL
     *
     * @return
     */
    String url();

    /**
     * 获取请求头
     *
     * @return
     */
    Map<String, String> headers();

    String bodyType();

    Map<String, String> bodyFormData();

    Map<String, String> bodyUrlencode();

    String bodyRaw();

    String bodyBinary();

    void showRaw(String text);

    void showPreview(String text);

    void showPretty(String text);

    void setCookies(Vector<String> cookies);

    void setHeaders(Vector<String> headers);
}