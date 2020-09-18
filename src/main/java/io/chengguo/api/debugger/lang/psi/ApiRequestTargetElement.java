package io.chengguo.api.debugger.lang.psi;

import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import io.chengguo.api.debugger.ui.KeyValuePair;

import java.util.List;

public interface ApiRequestTargetElement extends ApiElement {
    String getUrl(ApiVariableReplacer replacer);

    List<KeyValuePair> getParameters(ApiVariableReplacer replacer);
}
