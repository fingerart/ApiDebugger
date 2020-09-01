package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.ui.ApiDebugger;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.OutputStream;

import static com.intellij.execution.ui.ConsoleViewContentType.registerNewConsoleViewType;

public class ApiHttpRequestRunProfileState implements RunProfileState {

    private final Project mProject;
    private final ApiVariableReplacer mVariableReplacer;

    public ApiHttpRequestRunProfileState(Project project, ApiVariableReplacer variableReplacer) {
        mProject = project;
        mVariableReplacer = variableReplacer;
    }

    @Nullable
    @Override
    public ExecutionResult execute(Executor executor, @NotNull ProgramRunner runner) {
        ProcessHandler processHandler = createProcessHandler();
        ApiDebuggerRequestConsole console = createConsole(processHandler);
        processHandler.addProcessListener(new ProcessAdapter() {
            @Override
            public void processTerminated(@NotNull ProcessEvent event) {

            }
        });
        executeHttpRequest(console, processHandler);
        return new DefaultExecutionResult(console.getConsole(), processHandler);
    }

    private ApiDebuggerRequestConsole createConsole(ProcessHandler processHandler) {
        String target = mRequest.method + " " + mRequest.toString() + "\r\n\r\n";
        return new ApiDebuggerRequestConsole(mProject, target, processHandler);
    }

    private void executeHttpRequest(ApiDebuggerRequestConsole consoleView, ProcessHandler processHandler) {
        ApiDebugger debugger = ApiDebugger.create(mProject, mRequest, consoleView, processHandler, false);
        debugger.debug();
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
