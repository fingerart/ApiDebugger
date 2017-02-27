package io.chengguo.apidebugger.ui;

import com.google.common.eventbus.Subscribe;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import io.chengguo.apidebugger.engine.eventbus.DebuggerEventBus;
import io.chengguo.apidebugger.engine.eventbus.event.NoActionSessionsEvent;
import io.chengguo.apidebugger.engine.log.Log;
import io.chengguo.apidebugger.ui.action.AddTabAction;
import io.chengguo.apidebugger.ui.action.CloseTabAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by fingerart on 17/2/19.
 */
public class ApiDebuggerView implements ProjectComponent {

    private Project mProject;

    public ApiDebuggerView(Project project) {
        mProject = project;
        DebuggerEventBus.getDefault().register(this);
    }

    public static ApiDebuggerView getInstance(@NotNull Project project) {
        return project.getComponent(ApiDebuggerView.class);
    }

    public void initApiDebugger(ToolWindow toolWindow) {
        Content content = createApiDebuggerContentPanel(toolWindow);
        content.setCloseable(true);
        toolWindow.getContentManager().addContent(content);
        ((ToolWindowManagerEx) ToolWindowManager.getInstance(mProject)).addToolWindowManagerListener(createToolWindowListener());
    }

    private ToolWindowManagerListener createToolWindowListener() {
        return new ToolWindowManagerListener() {
            @Override
            public void toolWindowRegistered(@NotNull String s) {
            }

            @Override
            public void stateChanged() {
                ToolWindow toolWindow = ToolWindowManager.getInstance(mProject).getToolWindow(ApiDebuggerToolWindowFactory.TOOL_WINDOW_ID);
                if (toolWindow != null) {
                    if (toolWindow.isVisible() && toolWindow.getContentManager().getContentCount() == 0) {
                        Log.d("ApiDebuggerView.isVisible ContentCount>0");
                        initApiDebugger(toolWindow);
                    }
                }
            }
        };
    }

    private Content createApiDebuggerContentPanel(ToolWindow toolWindow) {
        toolWindow.setToHideOnEmptyContent(true);

        ApiDebuggerToolWindowPanel panel = new ApiDebuggerToolWindowPanel(PropertiesComponent.getInstance(mProject), toolWindow);
        Content content = ContentFactory.SERVICE.getInstance().createContent(panel, "", false);

        DebuggerWidget debuggerWidget = createContent(content);
        ActionToolbar toolBar = createToolBar(debuggerWidget);

        panel.setToolbar(toolBar.getComponent());
        panel.setContent(debuggerWidget.getComponent());

        return content;
    }

    private ActionToolbar createToolBar(DebuggerWidget debuggerWidget) {
        DefaultActionGroup group = new DefaultActionGroup();
        group.addAll(new AddTabAction(debuggerWidget), new CloseTabAction(debuggerWidget));
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, group, false);
        toolbar.setOrientation(SwingConstants.VERTICAL);
        return toolbar;
    }

    private DebuggerWidget createContent(Content content) {
        DebuggerWidget debuggerWidget = new TabbedDebuggerWidget(mProject, content);
        debuggerWidget.createDebuggerSession();
        return debuggerWidget;
    }

    @Subscribe
    public void onNoActiveSessions(NoActionSessionsEvent event) {
        Log.d("ApiDebuggerView.onNoActionSessions");
        ToolWindowManager.getInstance(mProject).getToolWindow(ApiDebuggerToolWindowFactory.TOOL_WINDOW_ID).getContentManager().removeAllContents(true);
    }

    @Override
    public void projectOpened() {
        Log.d("ApiDebuggerView.projectOpened");
    }

    @Override
    public void projectClosed() {
        Log.d("ApiDebuggerView.projectClosed");
    }

    @Override
    public void initComponent() {
        Log.d("ApiDebuggerView.initComponent");
    }

    @Override
    public void disposeComponent() {
        Log.d("ApiDebuggerView.disposeComponent");
    }

    @NotNull
    @Override
    public String getComponentName() {
        return ApiDebuggerView.class.getSimpleName();
    }
}
