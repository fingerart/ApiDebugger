package io.chengguo.api.debugger.ui.header;

import com.intellij.openapi.extensions.*;
import com.intellij.openapi.project.*;

public interface ApiDebuggerDataProvider
{
    public static final ExtensionPointName<ApiDebuggerDataProvider> EP_NAME = ExtensionPointName.create("io.chengguo.api.debugger.dataProvider");
    
    String[] getAllPaths(Project project);
    
    String[] getAllMimeTypes(Project project);
}