package io.chengguo.apidebugger.engine.http;

import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by fingerart on 17/7/23.
 */
public class PatchRequest extends PostRequest {
    public PatchRequest(PostRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        HttpPatch patch = new HttpPatch(mUrl);
        patch.setEntity(getEntity());
        return patch;
    }
}
