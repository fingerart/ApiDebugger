package io.chengguo.apidebugger.engine.http;

/**
 * Created by fingerart on 17/7/24.
 */
public class PatchRequestBuilder extends PostRequestBuilder {
    @Override
    public PostRequest build() {
        return new PatchRequest(this);
    }
}
