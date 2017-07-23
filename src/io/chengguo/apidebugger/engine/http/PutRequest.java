package io.chengguo.apidebugger.engine.http;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by fingerart on 17/7/23.
 */
public class PutRequest extends FormRequest {

    public PutRequest(FormRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        HttpPut put = new HttpPut(mUrl);
        ArtHttp.addTag(put.toString(), put);
        put.setEntity(getEntity());
        return put;
    }
}
