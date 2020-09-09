package io.chengguo.api.debugger.lang.psi;

import org.jetbrains.annotations.Nullable;

public interface ApiRequestElement extends ApiElement {

    @Nullable
    String getMimeType();

    @Nullable
    public ApiHeaderField getFirstHeader();

    @Nullable
    public ApiHeaderField getHeaderField(String key);
}
