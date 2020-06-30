package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import io.chengguo.api.debugger.lang.psi.ApiVariableDefinitionReference;

public abstract class ApiVariableMixin extends ApiNamedElementImpl implements ApiVariable {

    public ApiVariableMixin(IElementType type) {
        super(type);
    }

    @Override
    public PsiReference getReference() {
        PsiElement identifier = getIdentifier();
        if (identifier != null) {
            int startOffset = identifier.getTextRange().getStartOffset() - getTextRange().getStartOffset();
            TextRange rangeInElement = new TextRange(startOffset, startOffset + identifier.getTextLength());
            return new ApiVariableDefinitionReference<>(this, identifier.getText(), getTextRange(), rangeInElement);
        }
        return null;
    }
}