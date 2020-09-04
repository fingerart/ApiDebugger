package io.chengguo.api.debugger.lang.run;

import io.chengguo.api.debugger.ui.ApiDebuggerRequest;

import java.util.List;

public interface ApiDebuggerExecutionConfig {
    List<ApiDebuggerRequest> getRequests();
    String getEnvironment();
}
