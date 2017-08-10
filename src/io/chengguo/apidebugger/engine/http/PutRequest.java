package io.chengguo.apidebugger.engine.http;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by fingerart on 17/7/23.
 */
public class PutRequest extends PostRequest {

    public PutRequest(PostRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        HttpPut put = new HttpPut(mUrl);
        put.setEntity(getEntity());
        return put;
    }
}
