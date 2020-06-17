package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.openapi.application.QueryExecutorBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.util.Processor;
import io.chengguo.api.debugger.lang.ApiPsiFile;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import io.chengguo.api.debugger.lang.psi.ApiVariableName;
import org.jetbrains.annotations.NotNull;

public class ApiReferenceSearcher extends QueryExecutorBase<PsiReference, ReferencesSearch.SearchParameters> {
    @Override
    public void processQuery(@NotNull ReferencesSearch.SearchParameters queryParameters, @NotNull Processor<? super PsiReference> consumer) {
        final PsiElement target = queryParameters.getElementToSearch();
        if (!(target instanceof ApiVariableName)) return;

        SearchScope scope = queryParameters.getEffectiveSearchScope();
        if (!(scope instanceof LocalSearchScope)) return;

        PsiFile file = target.getContainingFile();
        if (!(file instanceof ApiPsiFile)) return;

        ApiVariableName targetVariableName = (ApiVariableName) target;
        ApiVariable[] variables = ApiPsiUtils.findVariables(file);
        for (ApiVariable variable : variables) {
            ApiVariableName currentVariableName = variable.getVariableName();
            if (currentVariableName == null) continue;
            if (targetVariableName.getName().equals(currentVariableName.getName())) {
                PsiReference reference = currentVariableName.getReference();
                if (reference != null && reference.isReferenceTo(target)) {
                    if (!consumer.process(reference)) return;
                }
            }
        }
    }
}
