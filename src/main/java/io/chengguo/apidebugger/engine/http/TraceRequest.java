package io.chengguo.apidebugger.engine.http;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;

/**
 * Created by fingerart on 17/7/25.
 */
public class TraceRequest extends GetRequest {
    public TraceRequest(TraceRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        return new HttpTrace(mUrl);
    }
}
