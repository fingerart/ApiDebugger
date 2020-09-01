package io.chengguo.api.debugger.lang.psi;

import io.chengguo.api.debugger.lang.ApiVariableReplacer;

public interface ApiRequestTargetElement extends ApiElement {
    String getBaseUrl(ApiVariableReplacer trimmer);
}
