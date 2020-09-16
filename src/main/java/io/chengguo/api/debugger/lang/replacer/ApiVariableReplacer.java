package io.chengguo.api.debugger.lang.replacer;

import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;

public class ApiVariableReplacer extends ApiElementReplacer<ApiVariable> {

    public static final ApiVariableReplacer EMPTY = new ApiVariableReplacer(ApiEnvironment.empty());
    public static final ApiVariableReplacer PLAIN = new ApiVariablePlainReplacer(ApiEnvironment.empty());

    private final ApiEnvironment mEnvironment;

    public ApiVariableReplacer(ApiEnvironment environment) {
        mEnvironment = environment;
    }

    public static ApiVariableReplacer create(ApiEnvironment environment) {
        return new ApiVariableReplacer(environment);
    }

    @Override
    public String getTargetValue(ApiVariable element) {
        String value = mEnvironment.getVariableValue(element.getName());
        return StringUtil.notNullize(value);
    }

    @Override
    protected boolean isTargetElement(PsiElement element) {
        return element instanceof ApiVariable;
    }

    private static class ApiVariablePlainReplacer extends ApiVariableReplacer {

        public ApiVariablePlainReplacer(ApiEnvironment environment) {
            super(environment);
        }

        @NotNull
        @Override
        public String getValue(PsiElement element, Condition<PsiElement> filter, boolean deepTraversal) {
            return element.getText();
        }

        @Override
        public String getTargetValue(ApiVariable element) {
            return element.getText();
        }
    }
}
