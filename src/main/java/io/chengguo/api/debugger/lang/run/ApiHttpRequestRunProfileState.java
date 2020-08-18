package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.execution.ui.ExecutionConsole;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.OutputStream;

public class ApiHttpRequestRunProfileState implements RunProfileState {

    private final ApiDebuggerRequest mRequest;
    private final Project mProject;
    private final ApiApiBlock mElement;

    public ApiHttpRequestRunProfileState(Project project, ApiDebuggerRequest request, ApiApiBlock element) {
        mRequest = request;
        mProject = project;
        mElement = element;
    }

    @Nullable
    @Override
    public ExecutionResult execute(Executor executor, @NotNull ProgramRunner runner) throws ExecutionException {
        ProcessHandler processHandler = createProcessHandler();
        ConsoleViewImpl consoleView = new ConsoleViewImpl(mProject, false);
        consoleView.attachToProcess(processHandler);
        consoleView.print(mRequest.baseUrl, ConsoleViewContentType.SYSTEM_OUTPUT);
        return new DefaultExecutionResult(consoleView, processHandler, new AnAction("其它操作") {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {

            }
        });
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
