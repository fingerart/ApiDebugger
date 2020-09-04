package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Pair;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiRequestTarget;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public abstract class ApiRequestTargetMixin extends ApiElementImpl implements ApiRequestTarget {
    public ApiRequestTargetMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getUrl(ApiVariableReplacer replacer) {
        return ApiPsiImplUtils.getUrl(this, replacer);
    }

    @Override
    public List<Pair<String, String>> getParameters(ApiVariableReplacer replacer) {
        return ApiPsiImplUtils.getParameters(getQuery(), replacer);
    }
}