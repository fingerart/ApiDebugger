package io.chengguo.apidebugger.engine.http;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.chengguo.apidebugger.engine.http.FormRequestBuilder.EntityType.FormData;

/**
 * Created by FingerArt on 16/10/7.
 */
public class FormRequestBuilder extends BaseRequestBuilder<FormRequestBuilder> {

    protected EntityType mEntityType = FormData;
    protected LinkedHashMap<String, File> mParamFile;

    public FormRequestBuilder addFile(String key, File file) {
        if (mParamFile == null) {
            mParamFile = new LinkedHashMap<>();
        }
        mParamFile.put(key, file);
        return this;
    }

    public FormRequestBuilder addFile(HashMap<String, File> files) {
        if (mParamFile == null) {
            mParamFile = new LinkedHashMap<>();
        }
        mParamFile.putAll(files);
        return this;
    }

    public synchronized FormRequestBuilder formData() {
        return this;
    }

    public synchronized XWwwUrlencodedRequestBuilder xWwwUrlencoded() {
        return new XWwwUrlencodedRequestBuilder(this);
    }

    public synchronized RawRequestBuilder raw() {
        return new RawRequestBuilder(this);
    }

    public synchronized BinaryRequestBuilder binary() {
        return new BinaryRequestBuilder(this);
    }

    @Override
    public FormRequest build() {
        return new FormRequest(this);
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
