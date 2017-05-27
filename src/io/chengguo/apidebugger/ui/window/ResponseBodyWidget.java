package io.chengguo.apidebugger.ui.window;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.actionSystem.ex.CheckboxAction;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static io.chengguo.apidebugger.engine.utils.ViewUtil.setCursor;

/**
 * Created by fingerart on 17/5/27.
 */
public class ResponseBodyWidget {
    private CardLayout mPreviewTypeCardLayout;
    public JPanel container;
    private JRadioButton mRbPretty;
    private JRadioButton mRbRaw;
    private JRadioButton mRbPreview;
    private JPanel previewTypeContainer;
    private JComboBox mCbFormatType;
    private JButton mBtnWrapLine;
    private JTextPane previewTextPane;
    private JPanel mPrettyContainer;
    private SimpleToolWindowPanel simpleToolWindowPanel1;

    public ResponseBodyWidget() {
        mPreviewTypeCardLayout = ((CardLayout) previewTypeContainer.getLayout());

        setCursor(Cursor.HAND_CURSOR, mRbPretty, mRbRaw, mRbPreview, mCbFormatType, mBtnWrapLine);

        mRbPretty.addActionListener(mPreviewType);
        mRbRaw.addActionListener(mPreviewType);
        mRbPreview.addActionListener(mPreviewType);
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
        ActionGroup group = new DefaultActionGroup(new CheckboxAction("Pretty", "show Pretty", AllIcons.Actions.ShowReadAccess) {
            boolean is;

            @Override
            public boolean isSelected(AnActionEvent anActionEvent) {
                return is;
            }

            @Override
            public void setSelected(AnActionEvent anActionEvent, boolean state) {
                try {
                    Class<?> clazz = Class.forName("com.intellij.openapi.editor.actions.ToggleUseSoftWrapsMenuAction");
                    System.out.println(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                is = state;
            }
        }, comboBoxAction);
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, group, true);
        simpleToolWindowPanel1.setToolbar(toolbar.getComponent());
        simpleToolWindowPanel1.setContent(new JPanel(new BorderLayout()));
    }

    private final ActionListener mPreviewType = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mPreviewTypeCardLayout.show(previewTypeContainer, e.getActionCommand());
        }
    };
}