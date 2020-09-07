package io.chengguo.api.debugger.ui;

import java.util.HashMap;
import java.util.Map;

public class ApiDebuggerRequest {
    public String method;
    public String url;
    public Map<String, String> parameters = new HashMap<>();

    public ApiDebuggerRequest() {
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return url;
    }
}
