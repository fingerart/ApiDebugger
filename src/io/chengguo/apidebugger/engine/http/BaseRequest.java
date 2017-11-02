package io.chengguo.apidebugger.engine.http;

import com.intellij.util.net.ssl.CertificateUtil;
import io.chengguo.apidebugger.engine.interf.ArtHttpListener;
import io.chengguo.apidebugger.engine.utils.CertificateUtils;
import io.chengguo.apidebugger.engine.utils.CommonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.chengguo.apidebugger.engine.utils.CommonUtil.async;
import static io.chengguo.apidebugger.engine.utils.CommonUtil.fixProtocol;

/**
 * Created by FingerArt on 16/10/7.
 */
public abstract class BaseRequest implements Runnable {
    protected String mUrl;
    protected LinkedHashMap<String, String> mParams;
    protected LinkedHashMap<String, String> mHeaders;
    protected LinkedHashMap<String, String> mCookies;
    private static HttpClient httpClient;
    protected ArtHttpListener mCallback;
    protected BaseRequestBuilder builder;

    static {
        try {
//            String keyStoreFile = "D:\\code\\ttt.ks";
//            String password = "poiuyt";
//            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
//            FileInputStream in = new FileInputStream(keyStoreFile);
//            ks.load(in, password.toCharArray());
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            TrustManager[] tms = CertificateUtils.creatNotCheckTrustManager();

            SSLContext sslCtx = SSLContext.getInstance("TLS");
            sslCtx.init(null, tms, null);
            httpClient = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(sslCtx)).build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public BaseRequest(BaseRequestBuilder builder) {
        this.builder = builder;
        mUrl = builder.mUrl;
        mParams = builder.mParamStr;
        mHeaders = builder.mHeaders;
        mCookies = builder.mCookies;

        mUrl = fixProtocol(mUrl);
    }

    protected abstract HttpRequestBase getRelRequest();

    public HttpResponse execute() throws IOException {
        return httpClient.execute(mergeRequest());
    }

    private HttpRequestBase mergeRequest() {
        HttpRequestBase relRequest = getRelRequest();
        ArtHttp.addTag(relRequest.toString(), relRequest);
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
        async(this);
    }

    @Override
    public void run() {
        try {
            mCallback.onPre();
            HttpResponse response = execute();
            mCallback.onSuccess(response);
        } catch (Exception e) {
            mCallback.onError(e);
        } finally {
            mCallback.onFinish();
        }
    }
}
