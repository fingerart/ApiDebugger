package io.chengguo.apidebugger.engine.http;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.chengguo.apidebugger.engine.http.PostRequestBuilder.EntityType.*;

/**
 * Created by FingerArt on 16/10/7.
 */
public class PostRequestBuilder extends BaseRequestBuilder<PostRequestBuilder> {

    protected EntityType mEntityType = FormData;
    protected LinkedHashMap<String, File> mParamFile;
    protected String mRaw;
    protected File mFile;

    public PostRequestBuilder addFile(String key, File file) {
        if (mParamFile == null) {
            mParamFile = new LinkedHashMap<>();
        }
        mParamFile.put(key, file);
        return this;
    }

    public PostRequestBuilder addFile(HashMap<String, File> files) {
        if (mParamFile == null) {
            mParamFile = new LinkedHashMap<>();
        }
        mParamFile.putAll(files);
        return this;
    }

    public synchronized PostRequestBuilder formData() {
        mEntityType = FormData;
        return this;
    }

    public synchronized PostRequestBuilder xWwwUrlencoded() {
        mEntityType = XWwwUrlencoded;
        return this;
    }

    public synchronized PostRequestBuilder raw(String raw) {
        mEntityType = RAW;
        mRaw = raw;
        return this;
    }

    public synchronized PostRequestBuilder binary(File file) {
        mEntityType = Binary;
        mFile = file;
        return this;
    }

    @Override
    public PostRequest build() {
        return new PostRequest(this);
    }

    /**
     * Request Entity Type
     */
    enum EntityType {
        FormData,
        RAW,
        XWwwUrlencoded,
        Binary
    }
}
