package io.chengguo.api.debugger.lang.psi;

import org.jetbrains.annotations.Nullable;

public interface ApiBlockElement extends ApiElement {
    @Nullable
    ApiDescription getDescriptionByKey(String key);
}
