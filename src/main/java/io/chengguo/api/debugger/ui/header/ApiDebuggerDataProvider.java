package io.chengguo.api.debugger.ui.header;

import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.project.Project;

public interface ApiDebuggerDataProvider {
    public static final ExtensionPointName<ApiDebuggerDataProvider> EP_NAME = ExtensionPointName.create("io.chengguo.api.debugger.dataProvider");

    String[] getAllPaths(Project project);

    String[] getAllMimeTypes(Project project);
}