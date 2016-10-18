package me.fingerart.idea.engine.net;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by FingerArt on 16/10/7.
 */
public class FormRequestBuilder extends BaseRequestBuilder<FormRequestBuilder> {

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

    @Override
    public FormRequest build() {
        return new FormRequest(this);
    }
}
