package io.chengguo.apidebugger.ui;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;

/**
 * Created by fingerart on 17/2/19.
 */
public class DebuggerToolWindowPanel extends SimpleToolWindowPanel {
    private PropertiesComponent myPropertiesComponent;
    private ToolWindow myWindow;

    public DebuggerToolWindowPanel(PropertiesComponent propertiesComponent, ToolWindow window) {
        super(false, true);
        myPropertiesComponent = propertiesComponent;
        myWindow = window;
    }
}
