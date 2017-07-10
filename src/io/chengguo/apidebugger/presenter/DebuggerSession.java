package io.chengguo.apidebugger.presenter;

import io.chengguo.apidebugger.engine.http.ArtHttp;
import io.chengguo.apidebugger.engine.http.FormRequestBuilder;
import io.chengguo.apidebugger.engine.interf.ArtHttpListener;
import io.chengguo.apidebugger.ui.iview.HttpInterf;
import org.apache.http.HttpResponse;
import org.apache.http.util.TextUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by fingerart on 17/7/11.
 */
public class DebuggerSession implements ArtHttpListener {

    private HttpInterf mView;

    public DebuggerSession(HttpInterf view) {
        mView = view;
    }

    /**
     * 执行http请求
     */
    public void execte() {
        String url = mView.url().trim();
        if (validUrl(url)) {
            System.out.println("url not empty");
            return;
        }
        switch (mView.method()) {
            case "get":
                get(url);
                break;
            case "post":
                post(url);
                break;
        }
    }

    private void get(String url) {
        Map<String, String> headers = mView.headers();
        ArtHttp.get()
                .url(url)
                .addHeader(headers)
                .build()
                .execute(this);
    }

    private void post(String url) {
        Map<String, String> headers = mView.headers();

        FormRequestBuilder builder = ArtHttp.post().url(url).addHeader(headers);

        String bodyType = mView.bodyType();
        switch (bodyType) {
            case "formData"://todo 情况复杂！！
                Map<String, String> formData = mView.bodyFormData();
                builder.addParam(formData);
                break;
            case "xWwwUrlencoded":
                Map<String, String> urlencode = mView.bodyUrlencode();
                builder.addParam(urlencode).xWwwUrlencoded();
                break;
            case "raw":
                break;
            case "binary":
                break;
        }

        builder.build().execute(this);
    }

    private boolean validUrl(String url) {
        return TextUtils.isEmpty(url);
    }

    @Override
    public void onPre() {

    }

    @Override
    public void onSuccess(HttpResponse response) {

    }

    @Override
    public void onError(IOException e) {

    }

    @Override
    public void onFinish() {

    }
}










