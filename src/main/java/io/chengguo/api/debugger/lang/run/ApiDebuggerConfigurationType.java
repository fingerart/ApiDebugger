package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ApiDebuggerConfigurationType implements ConfigurationType {

    private final ApiDebuggerDefaultConfigurationFactory mConfigurationFactory;

    public ApiDebuggerConfigurationType() {
        mConfigurationFactory = new ApiDebuggerDefaultConfigurationFactory(this);
    }

    public static ApiDebuggerConfigurationType getInstance() {
        return ConfigurationTypeUtil.findConfigurationType(ApiDebuggerConfigurationType.class);
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return ApiDebuggerBundle.message("api.debugger.action.name");
    }

    @Nls
    @Override
    public String getConfigurationTypeDescription() {
        return "我是描述";
    }

    @Override
    public Icon getIcon() {
        return ApiDebuggerIcons.API_DEBUGGER_RUN_CONFIGURATION;
    }

    @NotNull
    @Override
    public String getId() {
        return "ApiDebugger.ApiDebuggerConfigurationType";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "配置API debugger";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{mConfigurationFactory};
    }
}
