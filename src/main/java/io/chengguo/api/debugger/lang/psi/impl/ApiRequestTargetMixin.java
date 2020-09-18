package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiRequestTarget;
import io.chengguo.api.debugger.ui.KeyValuePair;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class ApiRequestTargetMixin extends ApiElementImpl implements ApiRequestTarget {
    public ApiRequestTargetMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getUrl(ApiVariableReplacer replacer) {
        return ApiPsiImplUtil.getUrl(this, replacer);
    }

    @Override
    public List<KeyValuePair> getParameters(ApiVariableReplacer replacer) {
        return ApiPsiImplUtil.getParameters(getQuery(), replacer);
    }
}
