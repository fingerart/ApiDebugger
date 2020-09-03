package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import io.chengguo.api.debugger.lang.ApiFileType;
import org.jetbrains.annotations.NotNull;

public class ApiDebuggerDefaultConfigurationFactory extends ConfigurationFactory {
    protected ApiDebuggerDefaultConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
    }

    @Override
    public boolean isApplicable(@NotNull Project project) {
        return FileTypeIndex.containsFileOfType(ApiFileType.INSTANCE, GlobalSearchScope.projectScope(project));
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new ApiDebuggerDefaultRunConfiguration(project, this);
    }
}
