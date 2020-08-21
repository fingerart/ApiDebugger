package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.console.ConsoleViewWrapperBase;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.RunnerLayoutUi;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ApiDebuggerConsoleView  extends ConsoleViewWrapperBase {
    public ApiDebuggerConsoleView(Project project) {
        super(new ConsoleViewImpl(project, false));
    }
}
