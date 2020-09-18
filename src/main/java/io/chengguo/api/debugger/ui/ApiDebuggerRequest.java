package io.chengguo.api.debugger.ui;

import com.intellij.openapi.util.text.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ApiDebuggerRequest {
    private final String mMethod;
    private final String mBaseUrl;
    private final List<KeyValuePair> mParameters = new ArrayList<>();
    private final List<KeyValuePair> mHeaders = new ArrayList<>();
    private String mFilePath;
    private String mTextToSend;
    private String mMultipartBoundary;
    private List<ApiFormBodyPart> mFormBodyParts;

    public ApiDebuggerRequest(String method, String url) {
        mMethod = method;
        mBaseUrl = url;
    }

    public String getUrl() {
        return mBaseUrl;
    }

    public String getFullUrl() {
        return mBaseUrl + "?" + createQueryString();
    }

    public String createQueryString() {
        return StringUtil.join(mParameters, item -> {
            try {
                String key = URLEncoder.encode(item.getKey(), StandardCharsets.UTF_8.name());
                String value = URLEncoder.encode(item.getValue(), StandardCharsets.UTF_8.name());
                return key + "=" + value;
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }, "&");
    }

    public String getMethod() {
        return mMethod;
    }

    public void addParameter(String key, String value) {
        mParameters.add(KeyValuePair.create(key, value));
    }

    public void addAllParameter(List<KeyValuePair> headers) {
        mParameters.addAll(headers);
    }

    public List<KeyValuePair> getParameters() {
        return mParameters;
    }

    public void addAllHeader(String key, String value) {
        mHeaders.add(KeyValuePair.create(key, value));
    }

    public void addAllHeader(List<KeyValuePair> headers) {
        mHeaders.addAll(headers);
    }

    public void addHeader(KeyValuePair header) {
        mHeaders.add(header);
    }

    public List<KeyValuePair> getHeaders() {
        return mHeaders;
    }

    public KeyValuePair getHeader(String key, String defaultValue) {
        return mHeaders.stream().filter(pair -> StringUtil.equals(pair.first, key)).findFirst().orElse(KeyValuePair.create(key, defaultValue));
    }

    public KeyValuePair getHeader(String key) {
        return getHeader(key, null);
    }

    public void setFileToSend(String filePath) {
        mFilePath = filePath;
    }

    public String getFileToSend() {
        return mFilePath;
    }

    public void setTextToSend(String text) {
        mTextToSend = text;
    }

    public String getTextToSend() {
        return mTextToSend;
    }

    public void setMultipartBoundary(String boundary) {
        mMultipartBoundary = boundary;
    }

    public String getMultipartBoundary() {
        return mMultipartBoundary;
    }

    public void setFormBody(List<ApiFormBodyPart> parts) {
        mFormBodyParts = parts;
    }

    public List<ApiFormBodyPart> getFormBodyParts() {
        return mFormBodyParts;
    }

    @Override
    public String toString() {
        return "ApiDebuggerRequest{" +
                "mMethod='" + mMethod + '\'' +
                ", mBaseUrl='" + mBaseUrl + '\'' +
                ", mParameters=" + mParameters +
                ", mHeaders=" + mHeaders +
                ", mFilePath='" + mFilePath + '\'' +
                ", mTextToSend='" + mTextToSend + '\'' +
                ", mMultipartBoundary='" + mMultipartBoundary + '\'' +
                ", mFormBodyParts=" + mFormBodyParts +
                '}';
    }
}
