package io.chengguo.apidebugger.engine.resources;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * Created by fingerart on 17/2/19.
 */
public interface DebuggerIcons {
    static Icon load(String path) {
        return IconLoader.getIcon(path);
    }

    Icon LOGO = load("/image/favicon.png");
}
