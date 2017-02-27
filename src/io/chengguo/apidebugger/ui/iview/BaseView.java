package io.chengguo.apidebugger.ui.iview;

import com.intellij.openapi.ui.Messages;

/**
 * Created by FingerArt on 16/10/2.
 */
public abstract class BaseView implements IView {
    @Override
    public void showE(String message) {
//        StatusBar statusBar = WindowManager.getInstance()
//                .getStatusBar(ProjectManager.getInstance().getOpenProjects()[0]);
//        JComponent component = statusBar.getComponent();
//        Rectangle rect = component.getVisibleRect();
//        Point p = new Point(rect.x + rect.width - 30, rect.y - 30);
//        RelativePoint point = new RelativePoint(component, p);
//        JBPopupFactory.getInstance().createMessage(message).show(point);
        Messages.showErrorDialog(message, "警告");
    }

    @Override
    public void showI(String message) {
        Messages.showInfoMessage(message, "提示");
    }
}
