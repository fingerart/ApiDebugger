package io.chengguo.api.debugger.lang.refactor;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.impl.manipulators.SimpleTagManipulator;
import com.intellij.util.IncorrectOperationException;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 监听并处理节点范围内的更改
 * <p>
 * 参考 {@link SimpleTagManipulator}
 */
@Deprecated
public class ApiIdentifierManipulator extends AbstractElementManipulator<ApiVariable> {

    @Nullable
    @Override
    public ApiVariable handleContentChange(
            @NotNull ApiVariable variable, @NotNull TextRange range, String newContent)
            throws IncorrectOperationException {
        PsiReference reference = variable.getReference();
        if (reference != null && reference.getElement() instanceof PsiNamedElement) {
            ((PsiNamedElement) reference.getElement()).setName(newContent);
        }
        return variable;
    }
}