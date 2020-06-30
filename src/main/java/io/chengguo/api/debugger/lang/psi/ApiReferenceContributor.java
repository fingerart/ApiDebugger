package io.chengguo.api.debugger.lang.psi;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import io.chengguo.api.debugger.lang.psi.impl.ApiVariableMixin;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.StandardPatterns.instanceOf;

public class ApiReferenceContributor extends PsiReferenceContributor {

    private static final PsiReferenceProvider PSI_REFERENCE_PROVIDER = new PsiReferenceProvider() {
        @NotNull
        @Override
        public PsiReference[] getReferencesByElement(
                @NotNull PsiElement element,
                @NotNull ProcessingContext context) {
            return element.getReferences();
        }
    };

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                PlatformPatterns.or(instanceOf(ApiVariableMixin.class)),
                PSI_REFERENCE_PROVIDER);
    }
}
