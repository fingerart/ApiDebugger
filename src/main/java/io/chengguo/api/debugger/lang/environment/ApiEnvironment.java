package io.chengguo.api.debugger.lang.environment;

import com.intellij.json.JsonUtil;
import com.intellij.json.psi.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import io.chengguo.api.debugger.lang.ApiPsiUtils;

import java.util.Collection;

public abstract class ApiEnvironment {

    private final String mName;

    public ApiEnvironment(String name) {
        this.mName = name;
    }

    public abstract String getVariableValue(String variableName);

    public String getName() {
        return mName;
    }

    public static ApiEnvironment empty() {
        return ApiEmptyEnvironment.EMPTY_ENVIRONMENT;
    }

    public static ApiEnvironment create(Project project, String envName) {
        if (StringUtil.isNotEmpty(envName)) {
            Collection<VirtualFile> files = ApiEnvironmentIndex.getAllVirtualFiles(project, envName);
            if (files.iterator().hasNext()) {
                PsiFile psiFile = ApiPsiUtils.findFileByVF(project, files.iterator().next());
                if (psiFile instanceof JsonFile) {
                    JsonValue value = ((JsonFile) psiFile).getTopLevelValue();
                    JsonObject environment = value instanceof JsonObject ? JsonUtil.getPropertyValueOfType((JsonObject) value, envName, JsonObject.class) : null;
                    return new ApiJsonEnvironment(envName, environment);
                }
            }
        }
        return ApiEmptyEnvironment.EMPTY_ENVIRONMENT;
    }

    private static class ApiJsonEnvironment extends ApiEnvironment {

        private final JsonObject mVariables;

        public ApiJsonEnvironment(String envName, JsonObject variables) {
            super(envName);
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
        private static final ApiEmptyEnvironment EMPTY_ENVIRONMENT = new ApiEmptyEnvironment("<Default Environment>");

        public ApiEmptyEnvironment(String name) {
            super(name);
        }

        @Override
        public String getVariableValue(String variableName) {
            return "";
        }
    }
}
