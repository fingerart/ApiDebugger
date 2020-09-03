package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import io.chengguo.api.debugger.lang.ApiPsiFile;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ApiDebuggerDefaultRunConfiguration extends LocatableConfigurationBase {

    private final Settings mSettings;

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

    @Override
    public void readExternal(@NotNull Element element) throws InvalidDataException {
        super.readExternal(element);
    }

    @Override
    public void writeExternal(@NotNull Element element) {
        super.writeExternal(element);
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        createConfig();
    }

    private ApiDebuggerExecutionConfig createConfig() throws RuntimeConfigurationException {
        Project project = getProject();
        String filePath = mSettings.getFilePath();
        if (StringUtil.isEmpty(filePath)) {
            throw new RuntimeConfigurationException("文件路径不能为空");
        }
        PsiFile file = ApiPsiUtils.findFileByPath(project, filePath);
        if (file == null) {
            throw new RuntimeConfigurationException("找不到Api文件");
        }
        if (file instanceof ApiPsiFile && mSettings.getRunFileType() == RunFileType.ALL_IN_FILE) {
            return new ApiDebuggerFileExecutionConfig();
        }
        return new ApiDebuggerFileExecutionConfig();
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
        private String filePath;
        private int indexInFile;

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
            this.envName = envName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public int getIndexInFile() {
            return indexInFile;
        }

        public void setIndexInFile(int indexInFile) {
            this.indexInFile = indexInFile;
        }

        @Override
        public String toString() {
            return "Settings{" +
                    "runFileType=" + runFileType +
                    ", envName='" + envName + '\'' +
                    ", filePath='" + filePath + '\'' +
                    ", indexInFile=" + indexInFile +
                    '}';
        }
    }
}
