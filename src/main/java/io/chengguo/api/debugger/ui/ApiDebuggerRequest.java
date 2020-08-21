package io.chengguo.api.debugger.ui;

public class ApiDebuggerRequest {
    public String method;
    public String baseUrl;

    public String getUrl() {
        return baseUrl;
    }

    @Override
    public String toString() {
        return getUrl();
    }
}
