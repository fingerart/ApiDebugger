package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.util.ArrayUtil;
import io.chengguo.api.debugger.lang.psi.ApiFilePath;
import io.chengguo.api.debugger.lang.reference.ApiReferenceContributor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public abstract class ApiFilePathMixin extends ApiElementImpl implements ApiFilePath {
    public ApiFilePathMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        PsiReference[] references = getReferences();
        return ArrayUtil.isEmpty(references) ? null : references[0];
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        ArrayList<PsiReference> references = new ArrayList<>();
        ApiReferenceContributor.FILE_REFERENCE_PROVIDER.createReferences(this, references, true);
        return references.toArray(PsiReference.EMPTY_ARRAY);
    }
}
