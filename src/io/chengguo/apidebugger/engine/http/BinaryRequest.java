package io.chengguo.apidebugger.engine.http;

import org.apache.http.HttpEntity;
import org.apache.http.entity.FileEntity;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created by fingerart on 17/7/14.
 */
public class BinaryRequest extends FormRequest {
    private File file;

    public BinaryRequest(BinaryRequestBuilder binaryRequestBuilder) {
        super(binaryRequestBuilder);
        file = binaryRequestBuilder.mFile;
    }

    @NotNull
    @Override
    protected HttpEntity getEntity() {
        return new FileEntity(file);
    }
}
