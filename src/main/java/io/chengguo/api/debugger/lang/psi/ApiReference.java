package io.chengguo.api.debugger.lang.psi;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ApiReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    public ApiReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
        key = element.getText();
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        List<ApiVariableName> variables = ApiPsiUtils.findVariables(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (ApiVariableName variable : variables) {
            results.add(new PsiElementResolveResult(variable));
        }
        return results.toArray(ResolveResult.EMPTY_ARRAY);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<ApiVariableName> variableNames = ApiPsiUtils.findVariables(project);
        List<LookupElement> variants = new ArrayList<>();
        for (ApiVariableName variableName : variableNames) {
            if (variableName.getName() != null && variableName.getName().length() > 0) {
                LookupElementBuilder lookupElementBuilder = LookupElementBuilder
                        .create(variableName)
                        .withIcon(ApiDebuggerIcons.FAVICON)
                        .withTypeText(variableName.getContainingFile().getName());
                variants.add(lookupElementBuilder);
            }
        }
        return variants.toArray();
    }
}
