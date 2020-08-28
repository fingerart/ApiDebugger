package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ApiDebuggerDefaultConfigurationFactory extends ConfigurationFactory {
    protected ApiDebuggerDefaultConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new ApiDebuggerDefaultRunConfiguration(project, this);
    }
}
