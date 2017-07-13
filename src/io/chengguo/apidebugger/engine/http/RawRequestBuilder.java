package io.chengguo.apidebugger.engine.http;

/**
 * Created by fingerart on 17/7/14.
 */
public class RawRequestBuilder extends FormRequestBuilder {
    protected String mRaw;

    public RawRequestBuilder(FormRequestBuilder formRequestBuilder) {
        mUrl = formRequestBuilder.mUrl;
        mHeaders = formRequestBuilder.mHeaders;
        mCookies = formRequestBuilder.mCookies;
    }

    /**
     * 设置Raw内容
     *
     * @param raw
     * @return
     */
    public RawRequestBuilder addRaw(String raw) {
        mRaw = raw;
        return this;
    }

    @Override
    public FormRequest build() {
        return new RawRequest(this);
    }
}
