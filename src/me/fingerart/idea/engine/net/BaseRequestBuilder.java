package me.fingerart.idea.engine.net;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/7.
 */
public abstract class BaseRequestBuilder<T> {
    protected String mUrl;
    protected LinkedHashMap<String, String> mParamStr;

    public abstract BaseRequest build();

    public T url(String url) {
        mUrl = url;
        return (T) this;
    }

    public T addParam(HashMap<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            addParam(entry.getKey(), entry.getValue());
        }
        return (T) this;
    }

    public T addParam(String key, String content) {
        if (mParamStr == null) {
            mParamStr = new LinkedHashMap<>();
        }
        mParamStr.put(key, content);
        return (T) this;
    }
}
