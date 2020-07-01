package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ArrayUtil;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;

public abstract class ApiVariableMixin extends ApiNamedElementImpl implements ApiVariable {

    public ApiVariableMixin(IElementType type) {
        super(type);
    }

    @Override
    public PsiReference getReference() {
        PsiReference[] references = getReferences();
        return ArrayUtil.isEmpty(references) ? null : references[0];
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        // 向 ReferenceContributor 获取 PsiReference
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }

    @Override
    public ItemPresentation getPresentation() {
        return ApiPsiElementPresentationFactory.getItemPresentation(this);
    }
}