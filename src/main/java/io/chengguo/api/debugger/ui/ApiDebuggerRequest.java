package io.chengguo.api.debugger.ui;

import com.intellij.openapi.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiDebuggerRequest {
    public String method;
    public String baseUrl;
    public String url;
    public Map<String, String> parameters = new HashMap<>();

    public ApiDebuggerRequest() {
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return getBaseUrl();
    }
}
