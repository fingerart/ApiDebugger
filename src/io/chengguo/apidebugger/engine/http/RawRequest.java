package io.chengguo.apidebugger.engine.http;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;

/**
 * Created by fingerart on 17/7/14.
 */
public class RawRequest extends FormRequest {

    private String mRaw;

    public RawRequest(RawRequestBuilder builder) {
        super(builder);
        mRaw = builder.mRaw;
    }

    @NotNull
    @Override
    protected HttpEntity getEntity() {
        return new StringEntity(mRaw, ContentType.create("application/json", Charset.forName("UTF-8")));
    }
}
