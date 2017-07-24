package io.chengguo.apidebugger.engine.http;

import org.apache.http.client.methods.HttpRequestBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/2.
 */
public class ArtHttp {

    private static HashMap<String, HttpRequestBase> mTags = new HashMap<>();

    /**
     * post 请求
     *
     * @return
     */
    public static PostRequestBuilder post() {
        return new PostRequestBuilder();
    }

    /**
     * get 请求
     *
     * @return
     */
    public static GetRequestBuilder get() {
        return new GetRequestBuilder();
    }

    /**
     * put 请求
     *
     * @return
     */
    public static PutRequestBuilder put() {
        return new PutRequestBuilder();
    }

    /**
     * patch 请求
     *
     * @return
     */
    public static PatchRequestBuilder patch() {
        return new PatchRequestBuilder();
    }

    /**
     * delete 请求
     */
    public static DeleteRequestBuilder delete() {
        return new DeleteRequestBuilder();
    }

    /**
     * head 请求
     *
     * @return
     */
    public static HeadRequestBuilder head() {
        return new HeadRequestBuilder();
    }

    /**
     * options 请求
     *
     * @return
     */
    public static OptionsRequestBuilder options() {
        return new OptionsRequestBuilder();
    }

    /**
     * trace 请求
     *
     * @return
     */
    public static TraceRequestBuilder trace() {
        return new TraceRequestBuilder();
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
