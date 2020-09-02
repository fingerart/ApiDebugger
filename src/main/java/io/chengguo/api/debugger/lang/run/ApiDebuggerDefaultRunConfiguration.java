package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.LocatableConfigurationBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApiDebuggerDefaultRunConfiguration extends LocatableConfigurationBase {

    private Settings mSettings;

    protected ApiDebuggerDefaultRunConfiguration(@NotNull Project project, @NotNull ConfigurationFactory factory, @Nullable String name) {
        super(project, factory, name);
        mSettings = new Settings();
    }

    protected ApiDebuggerDefaultRunConfiguration(@NotNull Project project, @NotNull ConfigurationFactory factory) {
        this(project, factory, null);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new ApiDebuggerDefaultRunConfigurationSettingsEditor(getProject());
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
        Project project = getProject();
        String envName = "Default";
        ApiVariableReplacer variableReplacer = ApiVariableReplacer.create(ApiEnvironment.create(project, envName));
        ApiDebuggerExecutionConfig executionConfig;
        return new ApiHttpRequestRunProfileState(project, variableReplacer);
    }

    public Settings getSettings() {
        return mSettings;
    }

    public static class Settings {
        private RunFileType runFileType;
        private String envName;

        public Settings() {
            this.runFileType = RunFileType.SINGLE_REQUEST;
            envName = ApiEnvironment.empty().getName();
        }

        public RunFileType getRunFileType() {
            return runFileType;
        }

        public void setRunFileType(@Nullable RunFileType runFileType) {
            if (runFileType == null) {
                runFileType = RunFileType.SINGLE_REQUEST;
            }
            this.runFileType = runFileType;
        }

        public String getEnvName() {
            return envName;
        }

        public void setEnvName(@Nullable String envName) {
            if (StringUtil.isEmpty(envName)) {
                envName = ApiEnvironment.empty().getName();
            }
            this.envName = envName;
        }

        @Override
        public String toString() {
            return "Settings{" +
                    "runFileType=" + runFileType +
                    ", envName='" + envName + '\'' +
                    '}';
        }
    }
}
