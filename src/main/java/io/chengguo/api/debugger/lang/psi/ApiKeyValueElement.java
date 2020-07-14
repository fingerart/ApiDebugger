package io.chengguo.api.debugger.lang.psi;

import org.jetbrains.annotations.NotNull;

public interface ApiKeyValueElement extends ApiElement {

    @NotNull
    String getName();

    @NotNull
    String getValue();
}
