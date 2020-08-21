package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import org.jetbrains.annotations.NotNull;

public class ApiDebuggerConsolePrinter {

    private final ConsoleView mConsoleView;

    public ApiDebuggerConsolePrinter(ConsoleView consoleView) {
        mConsoleView = consoleView;
    }

    public  void print(@NotNull String text, @NotNull ConsoleViewContentType contentType){
        mConsoleView.print(text, contentType);
    }

    public ConsoleView getConsole() {
        return mConsoleView;
    }
}
