package io.chengguo.api.debugger.lang.psi;

import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import org.jetbrains.annotations.NotNull;

public interface ApiKeyValueElement extends ApiElement {

    @NotNull
    String getKey();

    @NotNull
    String getValue();

    @NotNull
    String getKey(ApiVariableReplacer replacer);

    @NotNull
    String getValue(ApiVariableReplacer replacer);
}
