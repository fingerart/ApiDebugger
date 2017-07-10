package io.chengguo.apidebugger.engine.http;

/**
 * Created by fingerart on 17/7/11.
 */
public class XWwwUrlencodedRequestBuilder extends FormRequestBuilder {

    public XWwwUrlencodedRequestBuilder(FormRequestBuilder formRequestBuilder) {
        mUrl = formRequestBuilder.mUrl;
        mHeaders = formRequestBuilder.mHeaders;
        mParamStr = formRequestBuilder.mParamStr;
        mCookies = formRequestBuilder.mCookies;
    }

    @Override
    public FormRequest build() {
        return new XWwwUrlencodedRequest(this);
    }
}
