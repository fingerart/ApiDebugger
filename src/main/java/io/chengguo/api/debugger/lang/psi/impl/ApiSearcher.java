package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.openapi.application.QueryExecutorBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.util.Processor;
import io.chengguo.api.debugger.lang.ApiPsiFile;
import org.jetbrains.annotations.NotNull;

public class ApiSearcher extends QueryExecutorBase<PsiReference, ReferencesSearch.SearchParameters> {
    @Override
    public void processQuery(@NotNull ReferencesSearch.SearchParameters queryParameters, @NotNull Processor<? super PsiReference> consumer) {
        final PsiElement target = queryParameters.getElementToSearch();
        if (!(target instanceof PsiNamedElement)) return;

        SearchScope scope = queryParameters.getEffectiveSearchScope();
        if (!(scope instanceof LocalSearchScope)) return;

        PsiFile file = target.getContainingFile();
        if (!(file instanceof ApiPsiFile)) return;
    }
}
