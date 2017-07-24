package io.chengguo.apidebugger.engine.http;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by fingerart on 17/7/25.
 */
public class DeleteRequest extends GetRequest {
    public DeleteRequest(DeleteRequestBuilder deleteRequestBuilder) {
        super(deleteRequestBuilder);
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        return new HttpDelete(mUrl);
    }
}
