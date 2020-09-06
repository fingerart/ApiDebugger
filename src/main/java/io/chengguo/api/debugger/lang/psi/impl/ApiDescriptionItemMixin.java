package io.chengguo.api.debugger.lang.psi.impl;

import io.chengguo.api.debugger.lang.psi.ApiDescriptionItem;
import io.chengguo.api.debugger.lang.psi.ApiKeyValueElement;
import org.jetbrains.annotations.NotNull;

public abstract class ApiDescriptionItemMixin implements ApiKeyValueElement, ApiDescriptionItem {
    @NotNull
    @Override
    public String getName() {
        return ApiPsiImplUtils.getDescriptionKey(this);
    }

    @NotNull
    @Override
    public String getValue() {
        return ApiPsiImplUtils.getDescriptionValue(this);
    }
}
