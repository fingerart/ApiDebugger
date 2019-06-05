package io.chengguo.apidebugger.engine.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * formData
 * Created by FingerArt on 16/10/7.
 */
public class PostRequest extends BaseRequest {
    protected LinkedHashMap<String, File> mParamFile;
    protected PostRequestBuilder.EntityType mEntityType;
    protected String mRaw;
    protected File mFile;

    public PostRequest(PostRequestBuilder builder) {
        super(builder);
        mParamFile = builder.mParamFile;
        mEntityType = builder.mEntityType;
        mRaw = builder.mRaw;
        mFile = builder.mFile;
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        HttpPost post = new HttpPost(mUrl);
        post.setEntity(getEntity());
        return post;
    }

    @NotNull
    protected HttpEntity getEntity() {
        switch (mEntityType) {
            case XWwwUrlencoded:
                return xWwwUrlencodedHttpEntity();
            case RAW:
                return rawHttpEntity();
            case Binary:
                return binaryHttpEntity();
            default:
                return fromDataHttpEntity();
        }
    }

    private HttpEntity binaryHttpEntity() {
        return new FileEntity(mFile);
    }

    private HttpEntity rawHttpEntity() {
        return new StringEntity(mRaw, ContentType.create("application/json", Charset.forName("UTF-8")));
    }

    private HttpEntity xWwwUrlencodedHttpEntity() {
        ArrayList<NameValuePair> ps = new ArrayList<>();
        if (mParams != null) {
            Iterator<Map.Entry<String, String>> i = mParams.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry<String, String> next = i.next();
                if (!TextUtils.isEmpty(next.getKey().trim())) {
                    ps.add(new BasicNameValuePair(next.getKey(), next.getValue()));
                }
            }
        }
        return new UrlEncodedFormEntity(ps, Charset.forName("UTF-8"));
    }

    @NotNull
    private HttpEntity fromDataHttpEntity() {
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