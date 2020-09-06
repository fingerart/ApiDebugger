package io.chengguo.api.debugger.lang.run;

import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;

import java.util.List;

public interface ApiDebuggerExecutionConfig {
    List<ApiApiBlock> getApiBlocks();

    String getName();
}