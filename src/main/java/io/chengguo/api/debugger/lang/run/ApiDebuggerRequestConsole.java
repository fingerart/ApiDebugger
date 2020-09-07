package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;

public class ApiDebuggerRequestConsole {

    private final ApiDebuggerConsolePrinter mConsolePrinter;

    public ApiDebuggerRequestConsole(Project project, String target, ProcessHandler processHandler) {
        ConsoleView consoleView = new ApiDebuggerConsoleView(project);
        consoleView.attachToProcess(processHandler);
        mConsolePrinter = new ApiDebuggerConsolePrinter(consoleView);
        mConsolePrinter.print(target, ConsoleViewContentType.SYSTEM_OUTPUT);
    }

    public ConsoleView getConsole() {
        return mConsolePrinter.getConsole();
    }


    public void onRequestStart() {

    }

    public void onRequestResponse(StringBuilder builder) {
        getConsole().print(builder.toString(), ConsoleViewContentType.SYSTEM_OUTPUT);
    }

    public void onRequestError(Exception e) {
        getConsole().print(e.getMessage(), ConsoleViewContentType.ERROR_OUTPUT);
    }
}
