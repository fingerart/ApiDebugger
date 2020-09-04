package io.chengguo.api.debugger.lang.run;

import io.chengguo.api.debugger.ui.ApiDebuggerRequest;

import java.util.List;

public class ApiDebuggerSingleRequestExecutionConfig implements ApiDebuggerExecutionConfig {

    private final String mEnvironment;
    private final String mFilePath;

    public ApiDebuggerSingleRequestExecutionConfig(String envName, String filePath, int indexInFile) {
        mEnvironment = envName;
        mFilePath = filePath;
    }

    @Override
    public List<ApiDebuggerRequest> getRequests() {
        return null;
    }

    @Override
    public String getEnvironment() {
        return mEnvironment;
    }
}
