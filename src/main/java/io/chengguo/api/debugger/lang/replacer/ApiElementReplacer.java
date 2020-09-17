package io.chengguo.api.debugger.lang.replacer;

import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.ApiPsiUtil;
import io.chengguo.api.debugger.lang.psi.ApiElement;
import org.jetbrains.annotations.NotNull;

public abstract class ApiElementReplacer<T extends PsiElement> {

    @NotNull
    public String getValue(PsiElement element) {
        return getValue(element, Conditions.alwaysTrue());
    }

    @NotNull
    public String getValue(PsiElement element, Condition<PsiElement> filter) {
        return getValue(element, filter, false);
    }

    @NotNull
    public String getValue(PsiElement element, boolean deepTraversal) {
        return getValue(element, Conditions.alwaysTrue(), deepTraversal);
    }

    @NotNull
    public String getValue(PsiElement element, Condition<PsiElement> filter, boolean deepTraversal) {
        if (isTargetElement(element)) {
            return getTargetValue(((T) element));
        }
        if (element instanceof ApiElement) {
            StringBuilder builder = new StringBuilder();
            for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (isTargetElement(child)) {
                    builder.append(getTargetValue(((T) child)));
                } else if (filter.value(child)) {
                    if (ApiPsiUtil.isLeafElement(child) || !deepTraversal) {
                        builder.append(child.getText());
                    } else {
                        // 深度遍历
                        builder.append(getValue(child, filter, true));
                    }
                }
            }
            return builder.toString();
        }
        return StringUtil.notNullize(null);
    }

    protected abstract boolean isTargetElement(PsiElement element);

    public abstract String getTargetValue(T element);
}
