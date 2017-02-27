package io.chengguo.apidebugger.engine.net;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by FingerArt on 16/10/7.
 */
public abstract class BaseRequestBuilder<T> {
    protected String mUrl;
    protected LinkedHashMap<String, String> mParamStr;
    protected LinkedHashMap<String, String> mHeaders;
    protected LinkedHashMap<String, String> mCookies;

    public BaseRequestBuilder() {
        mHeaders = new LinkedHashMap<>();
        mHeaders.put("User-Agent", "ApiDebugger");
    }

    public abstract BaseRequest build();

    public T url(String url) {
        mUrl = url;
        return (T) this;
    }

    public T addHeader(String key, String value) {
        mHeaders.put(key, value);
        return (T) this;
    }

    public T addHeader(HashMap<String, String> headers) {
        mHeaders.putAll(headers);
        return (T) this;
    }

    public synchronized T addCookie(String key, String value) {
        if (mCookies == null) {
            mCookies = new LinkedHashMap<>();
        }
        mCookies.put(key, value);
        return (T) this;
    }

    public synchronized T addCookie(HashMap<String, String> cookies) {
        if (mCookies == null) {
            mCookies = new LinkedHashMap<>();
        }
        mCookies.putAll(cookies);
        return (T) this;
    }

    public synchronized T addParam(HashMap<String, String> params) {
        if (mParamStr == null) {
            mParamStr = new LinkedHashMap<>();
        }
        mParamStr.putAll(params);
        return (T) this;
    }

    public synchronized T addParam(String key, String content) {
        if (mParamStr == null) {
            mParamStr = new LinkedHashMap<>();
        }
        mParamStr.put(key, content);
        return (T) this;
    }
}
