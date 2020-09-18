package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.project.Project;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import org.jetbrains.annotations.NotNull;

public class ApiDebuggerBuiltinExecutor extends ApiRequestExecutor {
    private final ApiDebuggerRequest mRequest;
    private final ApiDebuggerRequestConsole mConsole;
    private final ProcessHandler mProcessHandler;
    private final boolean mIsWithProgress;

    public ApiDebuggerBuiltinExecutor(Project project, ApiDebuggerRequest request, ApiDebuggerRequestConsole console, ProcessHandler processHandler, boolean isWithProgress) {
        super(project);
        mRequest = request;
        mConsole = console;
        mProcessHandler = processHandler;
        mIsWithProgress = isWithProgress;
    }

    public static ApiDebuggerBuiltinExecutor create(Project project, ApiDebuggerRequest request, ApiDebuggerRequestConsole consoleView, ProcessHandler processHandler, boolean isWithProgress) {
        return new ApiDebuggerBuiltinExecutor(project, request, consoleView, processHandler, isWithProgress);
    }

    public void execute() {
        IDebugListener debugListener = createDebugListener();
        execute(mRequest, debugListener, mIsWithProgress);
    }

    @NotNull
    private IDebugListener createDebugListener() {
        return new IDebugListener() {
            @Override
            public void onStart() {
                mConsole.onRequestStart();
            }

            @Override
            public void onResponse(StringBuilder builder) {
                mConsole.onRequestResponse(builder);
            }

            @Override
            public void onError(Exception e) {
                mConsole.onRequestError(e);
            }

            @Override
            public void onDone() {
                mProcessHandler.destroyProcess();
            }
        };
    }
}
