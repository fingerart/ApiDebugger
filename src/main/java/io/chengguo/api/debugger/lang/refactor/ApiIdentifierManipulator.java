package io.chengguo.api.debugger.lang.refactor;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import io.chengguo.api.debugger.lang.psi.impl.ApiVariableMixin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class ApiIdentifierManipulator extends AbstractElementManipulator {

    @Nullable
    @Override
    public PsiElement handleContentChange(
            @NotNull PsiElement element, @NotNull TextRange range, String newContent)
            throws IncorrectOperationException {
        if (element instanceof ApiVariableMixin) {
            ApiVariableMixin variableMixin = (ApiVariableMixin) element;
            PsiReference reference = variableMixin.getReference();
            if (reference != null && reference.getElement() instanceof PsiNamedElement) {
                ((PsiNamedElement) reference.getElement()).setName(newContent);
            }
        }
        return ((PsiNamedElement) element).setName(newContent);
    }
}