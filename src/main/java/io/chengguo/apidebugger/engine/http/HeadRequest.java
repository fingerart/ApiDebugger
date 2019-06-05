package io.chengguo.apidebugger.engine.http;

import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by fingerart on 17/7/25.
 */
public class HeadRequest extends GetRequest {
    public HeadRequest(HeadRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        return new HttpHead(mUrl);
    }
}
