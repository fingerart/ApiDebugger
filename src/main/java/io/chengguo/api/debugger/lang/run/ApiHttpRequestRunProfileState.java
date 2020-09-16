package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.openapi.project.Project;
import io.chengguo.api.debugger.lang.ApiBlockConverter;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.OutputStream;

public class ApiHttpRequestRunProfileState implements RunProfileState {

    private final Project mProject;
    private final ApiVariableReplacer mVariableReplacer;
    private final ApiDebuggerExecutionConfig mExecutionConfig;

    public ApiHttpRequestRunProfileState(Project project, ApiVariableReplacer variableReplacer, ApiDebuggerExecutionConfig executionConfig) {
        mProject = project;
        mVariableReplacer = variableReplacer;
        mExecutionConfig = executionConfig;
    }

    @Nullable
    @Override
    public ExecutionResult execute(Executor executor, @NotNull ProgramRunner runner) throws ExecutionException {
        try {
            ProcessHandler processHandler = createProcessHandler();
            ApiDebuggerRequestConsole console = createConsole(processHandler);
            processHandler.addProcessListener(new ProcessAdapter() {
                @Override
                public void processTerminated(@NotNull ProcessEvent event) {

                }
            });
            executeHttpRequest(console, processHandler);
            return new DefaultExecutionResult(console.getConsole(), processHandler);
        } catch (Exception e) {
            throw new ExecutionException(e);
        }
    }

    private ApiDebuggerRequestConsole createConsole(ProcessHandler processHandler) {
        String target = mExecutionConfig.getName();
        return new ApiDebuggerRequestConsole(mProject, target, processHandler);
    }

    private void executeHttpRequest(ApiDebuggerRequestConsole consoleView, ProcessHandler processHandler) throws ApiRequestInvalidException {
        ApiDebuggerRequest request = ApiBlockConverter.toApiBlock(mExecutionConfig.getApiBlocks().get(0), mVariableReplacer);
        ApiDebuggerBuiltinExecutor.create(mProject, request, consoleView, processHandler, false).execute();
    }

    @NotNull
    private ProcessHandler createProcessHandler() {
        return new ProcessHandler() {

            @Override
            protected void destroyProcessImpl() {
                notifyProcessTerminated(0);
            }

            @Override
            protected void detachProcessImpl() {
                notifyProcessDetached();
            }

            @Override
            public boolean detachIsDefault() {
                return true;
            }

            @Nullable
            @Override
            public OutputStream getProcessInput() {
                return null;
            }
        };
    }
}
