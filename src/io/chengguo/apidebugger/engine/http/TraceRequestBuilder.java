package io.chengguo.apidebugger.engine.http;

/**
 * Created by fingerart on 17/7/25.
 */
public class TraceRequestBuilder extends GetRequestBuilder {
    @Override
    public BaseRequest build() {
        return new TraceRequest(this);
    }
}
