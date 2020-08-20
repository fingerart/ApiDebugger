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
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.ui.ApiDebugger;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.OutputStream;

import static com.intellij.execution.ui.ConsoleViewContentType.registerNewConsoleViewType;

public class ApiHttpRequestRunProfileState implements RunProfileState {

    private final ApiDebuggerRequest mRequest;
    private final Project mProject;
    private final ApiApiBlock mElement;
    private final static Key KK = Key.create("debug.level.title");

    static {
        registerNewConsoleViewType(KK, new ConsoleViewContentType(KK.toString(), ConsoleViewContentType.ERROR_OUTPUT_KEY));
    }

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
        ApiDebugger debugger = new ApiDebugger(mProject);
        debugger.debug(mRequest, new ApiDebugger.IDebugListener() {
            @Override
            public void onResponse(StringBuffer buffer) {
                consoleView.print(buffer.toString(), ConsoleViewContentType.NORMAL_OUTPUT);
            }

            @Override
            public void onDone() {
                processHandler.detachProcess();
            }
        });
        return new DefaultExecutionResult(consoleView, processHandler);
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
