package io.chengguo.api.debugger.lang.psi;

import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import io.chengguo.api.debugger.ui.KeyValuePair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ApiRequestElement extends ApiElement {

    @Nullable
    String getMimeType();

    @Nullable
    ApiHeaderField getFirstHeader();

    @Nullable
    ApiHeaderField getHeaderField(String key);

    @NotNull
    List<KeyValuePair> getHeaders(ApiVariableReplacer replacer);

    @Nullable
    ApiBodyMessageElement getBody();
}
