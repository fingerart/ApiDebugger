package io.chengguo.apidebugger.engine.net;

import io.chengguo.apidebugger.engine.interf.ArtHttpListener;
import io.chengguo.apidebugger.engine.utils.CommonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/7.
 */
public abstract class BaseRequest<T> implements Runnable {
    protected String mUrl;
    protected LinkedHashMap<String, String> mParams;
    protected LinkedHashMap<String, String> mHeaders;
    protected LinkedHashMap<String, String> mCookies;
    private static DefaultHttpClient httpClient = new DefaultHttpClient();
    protected ArtHttpListener mCallback;

    public BaseRequest(BaseRequestBuilder builder) {
        mUrl = builder.mUrl;
        mParams = builder.mParamStr;
        mHeaders = builder.mHeaders;
        mCookies = builder.mCookies;
    }

    protected abstract HttpRequestBase getRelRequest();

    public HttpResponse execute() throws IOException {
        return httpClient.execute(mergeRequest());
    }

    private HttpRequestBase mergeRequest() {
        HttpRequestBase relRequest = getRelRequest();
        if (mHeaders != null && !mHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry : mHeaders.entrySet()) {
                relRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (mCookies != null && !mCookies.isEmpty()) {
            String v = CommonUtil.mapToCookieVal(mCookies, "=", "; ");
            relRequest.addHeader("Cookie", v);
        }
        return relRequest;
    }

    public void execute(ArtHttpListener callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback not null");
        }
        ArtHttp.cancelAll();
        mCallback = callback;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            mCallback.onPre();
            HttpResponse response = execute();
            mCallback.onSuccess(response);
        } catch (IOException e) {
            mCallback.onError(e);
        } finally {
            mCallback.onFinish();
        }
    }
}
