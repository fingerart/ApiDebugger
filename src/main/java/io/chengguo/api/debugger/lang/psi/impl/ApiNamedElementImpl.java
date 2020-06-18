package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.ElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiReferenceService;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ProcessingContext;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.psi.ApiNamedElement;
import io.chengguo.api.debugger.lang.psi.ApiReference;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class ApiNamedElementImpl extends ApiElementImpl implements ApiNamedElement {

    public ApiNamedElementImpl(IElementType type) {
        super(type);
    }

    public ApiNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        ASTNode keyNode = getNode().findChildByType(ApiTypes.Api_IDENTIFIER);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    @Override
    public int getTextOffset() {
        PsiElement identifier = getNameIdentifier();
        return identifier != null ? identifier.getTextOffset() : super.getTextOffset();
    }

    @Override
    public PsiReference getReference() {
        return new ApiReference<>(this, getTextRange());
    }

    @Override
    public boolean isEquivalentTo(PsiElement another) {
        return this == another || (another instanceof ApiNamedElement && Objects.equals(getName(), ((ApiNamedElement) another).getName()));
    }
}