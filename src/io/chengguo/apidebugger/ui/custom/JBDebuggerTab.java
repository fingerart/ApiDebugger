package io.chengguo.apidebugger.ui.custom;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.ui.tabs.JBTabsPosition;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.impl.TabLabel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * 自定义Tab组件
 * 
 * Created by fingerart on 17/2/28.
 */
public class JBDebuggerTab extends JBEditorTabs {
    public JBDebuggerTab(@Nullable Project project, @NotNull ActionManager actionManager, IdeFocusManager focusManager, @NotNull Disposable parent) {
        super(project, actionManager, focusManager, parent);
    }

    @Override
    public Component add(Component comp) {
        //set tab cursor
        if (comp instanceof TabLabel) {
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        return super.add(comp);
    }

    @Override
    protected void doPaintInactive(Graphics2D g2d, boolean leftGhostExists, TabLabel label, Rectangle effectiveBounds, boolean rightGhostExists, int row, int column) {
        //Empty
    }

    @Override
    protected void doPaintBackground(Graphics2D g2d, Rectangle clip) {
        g2d.setPaint(getEmptySpaceColor());
        g2d.fill(clip);
        g2d.setPaint(getBackground());
        Rectangle bounds = getSelectedLabel().getBounds();
        g2d.drawLine((int) clip.getMinX(), (int) bounds.getMaxY(), (int) clip.getMaxX(), (int) bounds.getMaxY());
    }

    @Override
    protected ShapeInfo _computeSelectedLabelShape() {
        ShapeInfo shape = new ShapeInfo();
        shape.path = getEffectiveLayout().createShapeTransform(getSize());
        shape.insets = shape.path.transformInsets(getLayoutInsets());
        shape.labelPath = shape.path.createTransform(getSelectedLabel().getBounds());
        shape.labelBottomY = shape.labelPath.getMaxY() - shape.labelPath.deltaY(getActiveTabUnderlineHeight() - 1);
        boolean isTop = getPosition() == JBTabsPosition.top;
        boolean isBottom = getPosition() == JBTabsPosition.bottom;
        shape.labelTopY = shape.labelPath.getY() + (isTop ? shape.labelPath.deltaY(1) : (isBottom ? shape.labelPath.deltaY(-1) : 0));
        shape.labelLeftX = shape.labelPath.getX() + (!isTop && !isBottom ? shape.labelPath.deltaX(1) : 0);
        shape.labelRightX = shape.labelPath.getMaxX();
        shape.path.moveTo(shape.labelLeftX, shape.labelBottomY);
        shape.path.lineTo(shape.labelRightX, shape.labelBottomY);
        shape.path.lineTo(shape.labelRightX, shape.labelBottomY + shape.labelPath.deltaY(this.getActiveTabUnderlineHeight() - 1));
        shape.path.lineTo(shape.labelLeftX, shape.labelBottomY + shape.labelPath.deltaY(this.getActiveTabUnderlineHeight() - 1));
        shape.path.closePath();
        shape.fillPath = shape.path.copy();
        return shape;
    }
}
