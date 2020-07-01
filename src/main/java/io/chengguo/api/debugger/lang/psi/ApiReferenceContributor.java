package io.chengguo.api.debugger.lang.psi;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class ApiReferenceContributor extends PsiReferenceContributor {

    private static final PsiReferenceProvider PSI_REFERENCE_PROVIDER = new PsiReferenceProvider() {
        @NotNull
        @Override
        public PsiReference[] getReferencesByElement(
                @NotNull PsiElement element,
                @NotNull ProcessingContext context) {
            if (element instanceof ApiVariable) {
                return new PsiReference[]{new ApiVariableDefinitionReference<>((ApiVariable) element, element.getTextRange())};
            }
            return element.getReferences();
        }
    };

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                PlatformPatterns.or(StandardPatterns.instanceOf(ApiVariable.class)),
                PSI_REFERENCE_PROVIDER);
    }
}
