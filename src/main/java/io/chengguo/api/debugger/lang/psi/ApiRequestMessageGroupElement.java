package io.chengguo.api.debugger.lang.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ApiRequestMessageGroupElement extends ApiElement {
    @NotNull
    List<ApiRequestMessageElement> getRequestMessageList();
}
