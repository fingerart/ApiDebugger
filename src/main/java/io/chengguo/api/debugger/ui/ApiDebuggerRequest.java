package io.chengguo.api.debugger.ui;

import com.intellij.openapi.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ApiDebuggerRequest {
    public String method;
    public String baseUrl;
    public String url;
    public final List<Pair<String, String>> parameters;

    public ApiDebuggerRequest() {
        parameters = new ArrayList<>();
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

    public static class KeyValuePair extends Pair<String, String> {

        /**
         * @param key
         * @param value
         */
        public KeyValuePair(String key, String value) {
            super(key, value);
        }

        public String getKey() {
            return first;
        }

        public String getValue() {
            return second;
        }
    }
}
