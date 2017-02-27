package io.chengguo.apidebugger.engine.net;

/**
 * Created by fingerart on 16/10/17.
 */
public class GetRequestBuilder extends BaseRequestBuilder<GetRequestBuilder> {

    @Override
    public BaseRequest build() {
        return new GetRequest(this);
    }
}
