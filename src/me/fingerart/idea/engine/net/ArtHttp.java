package me.fingerart.idea.engine.net;

import org.apache.http.client.methods.HttpRequestBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/2.
 */
public class ArtHttp {

    private static HashMap<String, HttpRequestBase> mTags = new HashMap<>();

    public static FormRequestBuilder post() {
        return new FormRequestBuilder();
    }

    public static void get() {

    }

    public static void cancelAll() {
        for (Map.Entry<String, HttpRequestBase> entry : mTags.entrySet()) {
            entry.getValue().abort();
        }
        mTags.clear();
    }

    public static void addTag(String key, HttpRequestBase request) {
        mTags.put(key, request);
    }
}
