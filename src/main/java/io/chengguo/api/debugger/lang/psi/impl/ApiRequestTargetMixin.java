package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiRequestTarget;
import org.jetbrains.annotations.NotNull;

public abstract class ApiRequestTargetMixin extends ApiElementImpl implements ApiRequestTarget {
    public ApiRequestTargetMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getBaseUrl(ApiVariableReplacer trimmer) {
        return ApiPsiImplUtils.getBaseUrl(this, trimmer);
    }
}
