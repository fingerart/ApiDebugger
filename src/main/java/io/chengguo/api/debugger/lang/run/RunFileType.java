package io.chengguo.api.debugger.lang.run;

import io.chengguo.api.debugger.ApiDebuggerBundle;

public enum RunFileType {
    SINGLE_FILE("api.debugger.run.configuration.run_type.single_request"),
    ALL_FILE("api.debugger.run.configuration.run_type.all_in_file");
    private final String bundleKey;

    RunFileType(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    String typeName() {
        return ApiDebuggerBundle.message(bundleKey);
    }
}
