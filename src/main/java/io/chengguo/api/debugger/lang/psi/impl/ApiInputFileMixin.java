package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.ArrayUtil;
import io.chengguo.api.debugger.lang.psi.ApiInputFile;
import io.chengguo.api.debugger.lang.psi.ApiPsiTreeUtil;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.lang.reference.ApiReferenceContributor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;


public abstract class ApiInputFileMixin extends ApiElementImpl implements ApiInputFile {
    public ApiInputFileMixin(@NotNull ASTNode node) {
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
        PsiElement filePathElement = ApiPsiImplUtils.getRelativeFilePathElement(this);
        if (filePathElement != null) {
            ApiReferenceContributor.FILE_REFERENCE_PROVIDER.createReferences(filePathElement, references, true);
        }
        return references.toArray(PsiReference.EMPTY_ARRAY);
    }
}
