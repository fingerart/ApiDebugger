package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.tree.IElementType;
import io.chengguo.api.debugger.lang.psi.ApiVariableName;
import org.jetbrains.annotations.NotNull;

public abstract class ApiVariableNameMixin extends ApiElementImpl implements ApiVariableName {

    public ApiVariableNameMixin(IElementType type) {
        super(type);
    }

    @NotNull
    @Override
    public SearchScope getUseScope() {
        return new LocalSearchScope(getContainingFile());
    }
}