package io.chengguo.api.debugger.lang.environment;

import com.intellij.json.JsonUtil;
import com.intellij.json.psi.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import java.util.Collection;

public abstract class ApiEnvironment {

    public abstract String getVariableValue(String variableName);

    public static ApiEnvironment empty() {
        return ApiEmptyEnvironment.EMPTY_ENVIRONMENT;
    }

    public static ApiEnvironment create(Project project, String evnName) {
        if (StringUtil.isNotEmpty(evnName)) {
            Collection<VirtualFile> files = ApiEnvironmentIndex.getAllVirtualFiles(project, evnName);
            if (files.iterator().hasNext()) {
                PsiFile file = PsiManager.getInstance(project).findFile(files.iterator().next());
                if (file instanceof JsonFile) {
                    JsonValue value = ((JsonFile) file).getTopLevelValue();
                    JsonObject environment = value instanceof JsonObject ? JsonUtil.getPropertyValueOfType((JsonObject) value, evnName, JsonObject.class) : null;
                    return new ApiJsonEnvironment(environment);
                }
            }
        }
        return ApiEmptyEnvironment.EMPTY_ENVIRONMENT;
    }

    private static class ApiJsonEnvironment extends ApiEnvironment {

        private final JsonObject mVariables;

        public ApiJsonEnvironment(JsonObject variables) {
            mVariables = variables;
        }

        @Override
        public String getVariableValue(String variableName) {
            if (StringUtil.isEmpty(variableName)) {
                return null;
            }
            JsonValue value = JsonUtil.getPropertyValueOfType(mVariables, variableName, JsonValue.class);
            if (value instanceof JsonStringLiteral) {
                return ((JsonStringLiteral) value).getValue();
            }
            if (value instanceof JsonBooleanLiteral) {
                return String.valueOf(((JsonBooleanLiteral) value).getValue());
            }
            // 默认值为变量名
            return value != null ? value.getText() : variableName;
        }
    }

    private static class ApiEmptyEnvironment extends ApiEnvironment {
        private static final ApiEmptyEnvironment EMPTY_ENVIRONMENT = new ApiEmptyEnvironment();

        @Override
        public String getVariableValue(String variableName) {
            return null;
        }
    }
}
