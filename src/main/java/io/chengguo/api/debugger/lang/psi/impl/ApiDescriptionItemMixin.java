package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.ApiDescriptionItem;
import io.chengguo.api.debugger.lang.psi.ApiKeyValueElement;
import org.jetbrains.annotations.NotNull;

public abstract class ApiDescriptionItemMixin extends ApiElementImpl implements ApiDescriptionItem {
    public ApiDescriptionItemMixin(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public String getKey() {
        return ApiPsiImplUtils.getDescriptionKey(this);
    }

    @NotNull
    @Override
    public String getValue() {
        return ApiPsiImplUtils.getDescriptionValue(this);
    }
}
