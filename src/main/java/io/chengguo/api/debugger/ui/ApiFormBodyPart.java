package io.chengguo.api.debugger.ui;

import com.intellij.openapi.util.text.StringUtil;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class ApiFormBodyPart {
    protected ContentType mContentType;
    protected String mFieldName;
    protected List<KeyValuePair> mHeaders;

    public ApiFormBodyPart(String fieldName, ContentType contentType) {
        mContentType = contentType;
        mFieldName = fieldName;
        mHeaders = new ArrayList<>();
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
        mHeaders.add(KeyValuePair.create(key, value));
    }

    public void removeHeader(String key) {
        mHeaders.removeIf(pair -> StringUtil.equals(pair.key, key));
    }

    @Nullable
    public KeyValuePair getHeader(String key) {
        return mHeaders.stream().filter(pair -> StringUtil.equals(pair.key, key)).findFirst().orElse(null);
    }

    public String getFieldName() {
        return mFieldName;
    }

    public ContentType getContentType() {
        return mContentType;
    }

    protected FormBodyPartBuilder fillHeaders(FormBodyPartBuilder builder) {
        mHeaders.forEach(pair -> builder.addField(pair.key, pair.value));
        return builder;
    }

    public abstract FormBodyPart toFormBodyPart();

    public static class ApiFormBodyStringPart extends ApiFormBodyPart {

        private final String mContent;

        public ApiFormBodyStringPart(String fieldName, ContentType contentType, String content) {
            super(fieldName, contentType);
            mContent = content;
        }

        public String getContent() {
            return mContent;
        }

        @Override
        public FormBodyPart toFormBodyPart() {
            FormBodyPartBuilder builder = FormBodyPartBuilder.create(mFieldName, new StringBody(mContent, mContentType));
            return fillHeaders(builder).build();
        }
    }

    public static class ApiFormBodyFilePart extends ApiFormBodyPart {

        private final File mFile;
        private final String mFileName;

        public ApiFormBodyFilePart(String fieldName, ContentType contentType, File file) {
            this(fieldName, contentType, file, null);
        }

        public ApiFormBodyFilePart(String fieldName, ContentType contentType, File file, String fileName) {
            super(fieldName, contentType);
            mFile = file;
            mFileName = fileName;
        }

        public File getFile() {
            return mFile;
        }

        public String getFileName() {
            return mFileName;
        }

        @Override
        public FormBodyPart toFormBodyPart() {
            FormBodyPartBuilder builder = FormBodyPartBuilder.create(mFieldName, new FileBody(mFile, mContentType, mFileName));
            return fillHeaders(builder).build();
        }

    }
}
