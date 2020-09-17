package io.chengguo.api.debugger.ui;

import com.intellij.openapi.util.text.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiDebuggerRequest {
    private final String mMethod;
    private final String mBaseUrl;
    private final Map<String, String> mParameters = new HashMap<>();
    private final Map<String, String> mHeaders = new HashMap<>();
    public String mBodyText;
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
        return StringUtil.join(mParameters.entrySet(), item -> {
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
        mParameters.put(key, value);
    }

    public void addParameter(Map<String, String> headers) {
        mParameters.putAll(headers);
    }

    public Map<String, String> getParameters() {
        return mParameters;
    }

    public void addAllHeader(String key, String value) {
        mHeaders.put(key, value);
    }

    public void addAllHeader(Map<String, String> headers) {
        mHeaders.putAll(headers);
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
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

    public void setMultipartBoundary(String boundary) {
        mMultipartBoundary = boundary;
    }

    public String getMultipartBoundary() {
        return mMultipartBoundary;
    }

    public void setFormBody(List<ApiFormBodyPart> parts) {
        mFormBodyParts = parts;
    }

    @Override
    public String toString() {
        return "ApiDebuggerRequest{" +
                "mMethod='" + mMethod + '\'' +
                ", mBaseUrl='" + mBaseUrl + '\'' +
                ", mParameters=" + mParameters +
                ", mHeaders=" + mHeaders +
                ", mBodyText='" + mBodyText + '\'' +
                ", mFilePath='" + mFilePath + '\'' +
                ", mTextToSend='" + mTextToSend + '\'' +
                ", mMultipartBoundary='" + mMultipartBoundary + '\'' +
                ", mFormBodyParts=" + mFormBodyParts +
                '}';
    }
}
