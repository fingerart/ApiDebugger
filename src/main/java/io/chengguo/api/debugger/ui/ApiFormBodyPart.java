package io.chengguo.api.debugger.ui;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class ApiFormBodyPart {
    protected ContentType mContentType;
    protected String mFieldName;
    protected Map<String, String> mHeaders;

    public ApiFormBodyPart(String fieldName, ContentType contentType) {
        mContentType = contentType;
        mFieldName = fieldName;
        mHeaders = new HashMap<>();
    }

    public static ApiFormBodyPart create(String fieldName, ContentType contentType, String content) {
        return new ApiFormBodyStringPart(fieldName, contentType, content);
    }

    public static ApiFormBodyPart create(String fieldName, ContentType contentType, File file, @Nullable String fileName) {
        return new ApiFormBodyFilePart(fieldName, contentType, file, fileName);
    }

    public static ApiFormBodyPart create(String fieldName, ContentType contentType, File file) {
        return new ApiFormBodyFilePart(fieldName, contentType, file);
    }

    public void addHeader(String key, String value) {
        mHeaders.put(key, value);
    }

    public void removeHeader(String key) {
        mHeaders.remove(key);
    }

    protected FormBodyPartBuilder fillHeaders(FormBodyPartBuilder builder) {
        mHeaders.forEach(builder::addField);
        return builder;
    }

    public abstract FormBodyPart toFormBodyPartBuilder();

    public static class ApiFormBodyStringPart extends ApiFormBodyPart {

        private String mContent;

        public ApiFormBodyStringPart(String fieldName, ContentType contentType, String content) {
            super(fieldName, contentType);
            mContent = content;
        }

        @Override
        public FormBodyPart toFormBodyPartBuilder() {
            FormBodyPartBuilder builder = FormBodyPartBuilder.create(mFieldName, new StringBody(mContent, mContentType));
            return fillHeaders(builder).build();
        }
    }

    public static class ApiFormBodyFilePart extends ApiFormBodyPart {

        private File mFile;
        private String mFileName;

        public ApiFormBodyFilePart(String fieldName, ContentType contentType, File file) {
            this(fieldName, contentType, file, null);
        }

        public ApiFormBodyFilePart(String fieldName, ContentType contentType, File file, String fileName) {
            super(fieldName, contentType);
            mFile = file;
            mFileName = fileName;
        }

        @Override
        public FormBodyPart toFormBodyPartBuilder() {
            FormBodyPartBuilder builder = FormBodyPartBuilder.create(mFieldName, new FileBody(mFile, mContentType, mFileName));
            return fillHeaders(builder).build();
        }

    }
}
