package io.chengguo.api.debugger.lang;

import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import io.chengguo.api.debugger.lang.psi.ApiElement;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;

public class ApiVariableTrimmer {

    public static final ApiVariableTrimmer EMPTY = new ApiVariableTrimmer(ApiEnvironment.empty());

    private final ApiEnvironment mEnvironment;

    public ApiVariableTrimmer(ApiEnvironment environment) {
        mEnvironment = environment;
    }

    public static ApiVariableTrimmer create(ApiEnvironment environment) {
        return new ApiVariableTrimmer(environment);
    }

    @NotNull
    public String getValue(PsiElement element) {
        return getValue(element, Conditions.alwaysTrue());
    }

    @NotNull
    public String getValue(PsiElement element, Condition<PsiElement> filter) {
        if (element instanceof ApiVariable) {
            return getValue(((ApiVariable) element));
        }
        if (element instanceof ApiElement) {
            final StringBuilder builder = new StringBuilder();
            for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (child instanceof ApiVariable) {
                    builder.append(getValue((((ApiVariable) child))));
                } else if (filter.value(child)) {
                    builder.append(child.getText());
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
}
