package me.fingerart.idea.engine.net;

import io.netty.handler.codec.http.DefaultCookie;
import me.fingerart.idea.engine.interf.UploadProgressListener;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieIdentityComparator;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.Date;
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
    protected UploadProgressListener mCallback;

    public BaseRequest(BaseRequestBuilder builder) {
        mUrl = builder.mUrl;
        mParams = builder.mParamStr;
    }

    protected abstract HttpRequestBase getRelRequest();

    public HttpResponse execute() throws IOException {
        return httpClient.execute(mergeRequest());
    }

    private HttpRequestBase mergeRequest() {
        HttpRequestBase relRequest = getRelRequest();
        for (Map.Entry<String, String> entry : mHeaders.entrySet()) {
            relRequest.addHeader(entry.getKey(), entry.getValue());
        }
        BasicCookieStore cookieStore = new BasicCookieStore();
        for (Map.Entry<String, String> entry : mCookies.entrySet()) {
            cookieStore.addCookie(new ArtCookie(entry.getKey(), entry.getValue()));
        }
        httpClient.setCookieStore(cookieStore);
        return relRequest;
    }

    public void execute(UploadProgressListener callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback not null");
        }
        ArtHttp.cancelAll();
        mCallback = callback;
        new Thread(this).start();
    }

    @Override
    public void run() {
        HttpResponse response = null;
        try {
            mCallback.onPre();
            response = httpClient.execute(mergeRequest());
            mCallback.onSuccess(response);
        } catch (IOException e) {
            mCallback.onError(response, e);
        } finally {
            mCallback.onFinish();
        }
    }
}
