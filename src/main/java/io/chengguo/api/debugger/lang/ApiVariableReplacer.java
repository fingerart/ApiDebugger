package io.chengguo.api.debugger.lang;

import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import io.chengguo.api.debugger.lang.psi.ApiElement;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;

public class ApiVariableReplacer {

    public static final ApiVariableReplacer EMPTY = new ApiVariableReplacer(ApiEnvironment.empty());
    public static final ApiVariableReplacer PLAIN = new ApiVariablePlainReplacer(ApiEnvironment.empty());

    private final ApiEnvironment mEnvironment;

    public ApiVariableReplacer(ApiEnvironment environment) {
        mEnvironment = environment;
    }

    public static ApiVariableReplacer create(ApiEnvironment environment) {
        return new ApiVariableReplacer(environment);
    }

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
        if (element instanceof ApiVariable) {
            return getValue(((ApiVariable) element));
        }
        if (element instanceof ApiElement) {
            StringBuilder builder = new StringBuilder();
            for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (child instanceof ApiVariable) {
                    builder.append(getValue((((ApiVariable) child))));
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
        return promiseNotNull(null);
    }

    @NotNull
    public String getValue(ApiVariable variable) {
        String value = mEnvironment.getVariableValue(variable.getName());
        return promiseNotNull(value);
    }

    @NotNull
    private String promiseNotNull(String value) {
        return value != null ? value : "";
    }

    private static class ApiVariablePlainReplacer extends ApiVariableReplacer {

        public ApiVariablePlainReplacer(ApiEnvironment environment) {
            super(environment);
        }

        @NotNull
        @Override
        public String getValue(PsiElement element) {
            return element.getText();
        }

        @NotNull
        @Override
        public String getValue(PsiElement element, Condition<PsiElement> filter) {
            return element.getText();
        }

        @NotNull
        @Override
        public String getValue(PsiElement element, boolean deepTraversal) {
            return element.getText();
        }

        @NotNull
        @Override
        public String getValue(PsiElement element, Condition<PsiElement> filter, boolean deepTraversal) {
            return element.getText();
        }

        @NotNull
        @Override
        public String getValue(ApiVariable variable) {
            return variable.getText();
        }
    }
}
