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
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApiDebuggerDefaultRunConfiguration extends LocatableConfigurationBase {
    protected ApiDebuggerDefaultRunConfiguration(@NotNull Project project, @NotNull ConfigurationFactory factory, @Nullable String name) {
        super(project, factory, name);
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
        return new ApiHttpRequestRunProfileState(project, variableReplacer);
    }


}
