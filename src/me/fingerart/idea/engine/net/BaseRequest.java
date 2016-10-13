package me.fingerart.idea.engine.net;

import me.fingerart.idea.engine.interf.UploadProgressListener;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by FingerArt on 16/10/7.
 */
public abstract class BaseRequest<T> implements Runnable {
    protected String mUrl;
    protected LinkedHashMap<String, String> mParamStr;
    private static HttpClient httpClient = new DefaultHttpClient();
    protected UploadProgressListener mCallback;

    protected abstract HttpRequestBase getRelRequest();

    public HttpResponse execute() throws IOException {
        return httpClient.execute(getRelRequest());
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
            response = httpClient.execute(getRelRequest());
            mCallback.onSuccess(response);
        } catch (IOException e) {
            mCallback.onError(response, e);
        } finally {
            mCallback.onFinish();
        }
    }
}
