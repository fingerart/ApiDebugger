package io.chengguo.api.debugger.lang.liveTemplates;

import com.intellij.codeInsight.template.FileTypeBasedContextType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilCore;
import io.chengguo.api.debugger.lang.ApiFileType;
import io.chengguo.api.debugger.lang.ApiLanguage;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.psi.ApiRequest;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;

public class ApiTemplateContextType extends FileTypeBasedContextType {
    protected ApiTemplateContextType() {
        super(ApiLanguage.INSTANCE.getID(), ApiFileType.INSTANCE.getName(), ApiFileType.INSTANCE);
    }

    @Override
    public boolean isInContext(@NotNull PsiFile file, int offset) {
        if (PsiUtilCore.getLanguageAtOffset(file, offset).isKindOf(ApiLanguage.INSTANCE)) {
            PsiElement element = file.findElementAt(offset);
            if (element instanceof PsiWhiteSpace && offset > 0) {
                element = file.findElementAt(offset - 1);
            }
            return element != null && isInContext(element);
        }
        return false;
    }

    protected boolean isInContext(@NotNull final PsiElement element) {
        if (!ApiPsiUtils.isOfType(element, ApiTypes.Api_METHOD)) {
            return false;
        }
        ApiRequest request = PsiTreeUtil.getParentOfType(element, ApiRequest.class);
        return request != null && request.getTextRange().getStartOffset() == element.getTextRange().getStartOffset();
    }
}
