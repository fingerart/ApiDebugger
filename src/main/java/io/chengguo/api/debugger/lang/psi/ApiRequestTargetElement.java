package io.chengguo.api.debugger.lang.psi;

import com.intellij.openapi.util.Pair;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;

import java.util.List;

public interface ApiRequestTargetElement extends ApiElement {
    String getUrl(ApiVariableReplacer replacer);

    List<Pair<String, String>> getParameters(ApiVariableReplacer replacer);
}
