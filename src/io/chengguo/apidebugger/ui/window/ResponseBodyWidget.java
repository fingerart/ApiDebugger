package io.chengguo.apidebugger.ui.window;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import io.chengguo.apidebugger.ui.action.CloseTabAction;
import io.chengguo.apidebugger.ui.custom.JBRadioAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by fingerart on 17/5/27.
 */
public class ResponseBodyWidget {
    private CardLayout mPreviewTypeCardLayout;
    public JPanel container;
    private JPanel previewTypeContainer;

    private JTextPane previewTextPane;
    private JPanel mPrettyContainer;
    private SimpleToolWindowPanel simpleToolWindowPanel1;
    private JTextPane asdfasfTextPane;

    public ResponseBodyWidget() {
        mPreviewTypeCardLayout = ((CardLayout) previewTypeContainer.getLayout());
    }

    private void createUIComponents() {
        simpleToolWindowPanel1 = new SimpleToolWindowPanel(true, true);
        ComboBoxAction comboBoxAction = new ComboBoxAction() {
            @NotNull
            @Override
            protected DefaultActionGroup createPopupActionGroup(JComponent jComponent) {
                return new DefaultActionGroup(new AnAction("one", "this is one", AllIcons.Actions.ShowReadAccess) {
                    @Override
                    public void actionPerformed(AnActionEvent anActionEvent) {
                        
                    }
                }, new AnAction("two", "this is two", AllIcons.Actions.AllRight) {
                    @Override
                    public void actionPerformed(AnActionEvent anActionEvent) {

                    }
                });
            }
        };

        ActionListener mPreviewType = e -> {
            System.out.println("ResponseBodyWidget.actionPerformed");
            mPreviewTypeCardLayout.show(previewTypeContainer, e.getActionCommand());
        };

        ButtonGroup buttonGroup = new ButtonGroup();
        ActionGroup group = new DefaultActionGroup(new JBRadioAction("Pretty", "Pretty", buttonGroup, mPreviewType, true),
                new JBRadioAction("Raw", "Raw", buttonGroup, mPreviewType),
                new JBRadioAction("Preview", "Preview", buttonGroup, mPreviewType),
                comboBoxAction,
                new CloseTabAction(null));
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, group, true);
        simpleToolWindowPanel1.setToolbar(toolbar.getComponent());
        simpleToolWindowPanel1.setContent(new JPanel(new BorderLayout()));
    }
}