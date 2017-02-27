package io.chengguo.apidebugger.engine.net;

import io.chengguo.apidebugger.engine.utils.CommonUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import java.net.URI;

/**
 * Created by fingerart on 16/10/18.
 */
public class GetRequest extends BaseRequest {

    public GetRequest(GetRequestBuilder getRequestBuilder) {
        super(getRequestBuilder);
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        HttpGet get = new HttpGet();
        String urlParams = CommonUtil.mergeUrlParams(mUrl, mParams);
        get.setURI(URI.create(urlParams));
        return get;
    }
}