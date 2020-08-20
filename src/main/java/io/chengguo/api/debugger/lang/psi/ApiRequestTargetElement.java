package io.chengguo.api.debugger.lang.psi;

import io.chengguo.api.debugger.lang.ApiVariableTrimmer;

public interface ApiRequestTargetElement extends ApiElement {
    String getBaseUrl(ApiVariableTrimmer trimmer);
}
