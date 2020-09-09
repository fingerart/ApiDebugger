package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.ApiDescription;
import org.jetbrains.annotations.NotNull;

public abstract class ApiDescriptionMixin extends ApiElementImpl implements ApiDescription {
    public ApiDescriptionMixin(@NotNull ASTNode node) {
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
