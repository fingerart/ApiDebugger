package io.chengguo.api.debugger;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * Icons
 */
public interface ApiDebuggerIcons {
    static Icon loadIcon(String path) {
        return IconLoader.getIcon(path);
    }

    Icon FAVICON = loadIcon("/icons/favicon.png");

    Icon API_FILE_TYPE = loadIcon("/icons/api_file_type.svg");

    Icon API_REQUEST_NAV = loadIcon("/icons/api_request_nav.svg");

    Icon API_DEBUGGER_RUN_CONFIGURATION = loadIcon("/icons/api_debugger_run_configuration.svg");
}
