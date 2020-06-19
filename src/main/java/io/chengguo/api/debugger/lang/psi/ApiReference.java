package io.chengguo.api.debugger.lang.psi;

import com.google.common.base.Strings;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ApiReference<T extends PsiElement> extends PsiPolyVariantReferenceBase<T> {
    private final String key;

    public ApiReference(@NotNull T element, TextRange rangeInElement) {
        super(element, rangeInElement);
        key = element.getText();
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
//        List<ApiVariableName> variableNames = ApiPsiUtils.findVariableNames(project, key);
        List<ResolveResult> results = new ArrayList<>();
//        for (ApiVariableName variableName : variableNames) {
//            results.add(new PsiElementResolveResult(variableName));
//        }
        ResolveResult[] resolveResults = results.toArray(ResolveResult.EMPTY_ARRAY);
        System.out.println("resolveResults = " + resolveResults);
        return resolveResults;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
//        List<ApiVariableName> variableNames = ApiPsiUtils.findVariableNames(project);
        List<LookupElement> variants = new ArrayList<>();
//        for (ApiVariableName variableName : variableNames) {
//            if (!Strings.isNullOrEmpty(variableName.getName())) {
//                LookupElementBuilder lookupElementBuilder = LookupElementBuilder
//                        .create(variableName)
//                        .withIcon(ApiDebuggerIcons.FAVICON)
//                        .withTypeText(variableName.getContainingFile().getName());
//                variants.add(lookupElementBuilder);
//            }
//        }
        return variants.toArray();
    }

    @Override
    public boolean isReferenceTo(@NotNull PsiElement element) {
        return couldBeReferenceTo(element) && super.isReferenceTo(element);
    }

    private boolean couldBeReferenceTo(@NotNull PsiElement element) {
//        return element instanceof ApiVariableName;
        return false;
    }
}
