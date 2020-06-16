package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import io.chengguo.api.debugger.lang.psi.ApiElementGenerator;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.lang.psi.ApiVariableName;
import org.jetbrains.annotations.NotNull;

public abstract class ApiVariableNameMixin extends ApiElementImpl implements ApiVariableName {
    public ApiVariableNameMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiElement setName(@NotNull String newName) throws IncorrectOperationException {
        return new ApiElementGenerator(getProject()).createVariableName(newName);
    }
}