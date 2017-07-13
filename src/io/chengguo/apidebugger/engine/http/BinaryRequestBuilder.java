package io.chengguo.apidebugger.engine.http;

import java.io.File;

/**
 * Created by fingerart on 17/7/14.
 */
public class BinaryRequestBuilder extends FormRequestBuilder {
    protected File mFile;

    public BinaryRequestBuilder(FormRequestBuilder formRequestBuilder) {
        mUrl = formRequestBuilder.mUrl;
        mHeaders = formRequestBuilder.mHeaders;
        mCookies = formRequestBuilder.mCookies;
    }

    /**
     * 添加文件
     *
     * @param file
     * @return
     */
    public BinaryRequestBuilder addFile(File file) {
        mFile = file;
        return this;
    }

    @Override
    public FormRequest build() {
        return new BinaryRequest(this);
    }
}
