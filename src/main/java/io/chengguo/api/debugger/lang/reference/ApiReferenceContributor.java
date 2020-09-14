package io.chengguo.api.debugger.lang.reference;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.paths.StaticPathReferenceProvider;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.ApiPsiFile;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.patterns.PlatformPatterns.psiFile;

public class ApiReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                psiElement(ApiTypes.Api_VARIABLE).inFile(psiFile(ApiPsiFile.class)),
                PSI_REFERENCE_PROVIDER
        );
    }

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

    public static final StaticPathReferenceProvider FILE_REFERENCE_PROVIDER = new StaticPathReferenceProvider(FileType.EMPTY_ARRAY) {
        {
            setRelativePathsAllowed(true);
            setEndingSlashNotAllowed(true);
        }

        @Override
        public boolean createReferences(@NotNull PsiElement psiElement, int offset, String text, @NotNull List<PsiReference> references, boolean soft) {
            ContainerUtil.addAll(references, new ApiFileReferenceSet(text, psiElement, offset, null, true, true, FileType.EMPTY_ARRAY, soft).getAllReferences());
            return true;
        }
    };
}
