package io.chengguo.api.debugger.lang.psi;

import org.jetbrains.annotations.Nullable;

public interface ApiBlockElment extends ApiElement {
    @Nullable
    String getMimeType();
}
