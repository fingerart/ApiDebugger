package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
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
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public PsiReference getReference() {
        return null;
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
//        List<PsiReference> result = ContainerUtil.newSmartList();
//        for (Map.Entry<ElementPattern<? extends PsiElement>, PsiReferenceProvider> e : ourProviders.entrySet()) {
//            ProcessingContext context = new ProcessingContext();
//            if (e.getKey().accepts(this, context)) {
//                result.addAll(Arrays.asList(e.getValue().getReferencesByElement(this, context)));
//            }
//        }
//        return result.isEmpty() ? PsiReference.EMPTY_ARRAY : ContainerUtil.toArray(result, new PsiReference[result.size()]);
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }
}
