package io.chengguo.apidebugger.engine.http;

import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by fingerart on 17/7/25.
 */
public class OptionsRequest extends GetRequest {
    public OptionsRequest(OptionsRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        return new HttpOptions(mUrl);
    }
}
