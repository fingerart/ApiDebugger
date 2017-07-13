package io.chengguo.apidebugger.engine.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * formData
 * Created by FingerArt on 16/10/7.
 */
public class FormRequest extends BaseRequest {
    protected LinkedHashMap<String, File> mParamFile;

    public FormRequest(FormRequestBuilder builder) {
        super(builder);
        mParamFile = builder.mParamFile;
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        HttpPost post = new HttpPost(mUrl);
        ArtHttp.addTag(post.toString(), post);
        post.setEntity(getEntity());
        return post;
    }

    @NotNull
    protected HttpEntity getEntity() {
        ProgressMultipartEntity entity = new ProgressMultipartEntity(mCallback);
        entity.addPart(mParams);
        if (mParamFile != null) {
            for (Map.Entry<String, File> entry : mParamFile.entrySet()) {
                entity.addPart(entry.getKey(), entry.getValue());
            }
        }
        return entity;
    }
}