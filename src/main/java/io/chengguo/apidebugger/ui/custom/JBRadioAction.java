package io.chengguo.apidebugger.ui.custom;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.actionSystem.ex.CustomComponentAction;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by fingerart on 17/6/16.
 */
public class JBRadioAction extends AnAction implements CustomComponentAction {
    private ButtonGroup mButtonGroup;
    private String mActionCommand;
    private ActionListener mActionListener;
    private boolean selected;

    public JBRadioAction(@Nullable String text) {
        this(text, null);
    }

    public JBRadioAction(@Nullable String text, ButtonGroup buttonGroup) {
        super(text);
        this.mButtonGroup = buttonGroup;
    }

    public JBRadioAction(String text, String actionCommand, ButtonGroup buttonGroup, ActionListener actionListener) {
        this(text, buttonGroup);
        this.mActionCommand = actionCommand;
        this.mActionListener = actionListener;
    }

    public JBRadioAction(String text, String actionCommand, ButtonGroup buttonGroup, ActionListener actionListener, boolean selected) {
        this(text, actionCommand, buttonGroup, actionListener);
        this.selected = selected;
    }

    @Override
    public JComponent createCustomComponent(Presentation presentation) {
        JRadioButton jRadioButton = new JRadioButton("");
        jRadioButton.addActionListener(e -> {
            JRadioButton radioButton = (JRadioButton) e.getSource();
            ActionToolbar actionToolbar = UIUtil.getParentOfType(ActionToolbar.class, radioButton);
            DataContext dataContext = actionToolbar != null ? actionToolbar.getToolbarDataContext() : DataManager.getInstance().getDataContext(radioButton);
            JBRadioAction.this.actionPerformed(AnActionEvent.createFromAnAction(JBRadioAction.this, null, "unknown", dataContext));
            System.out.println("JBRadioAction.createCustomComponent");
            if (mActionListener != null) {
                mActionListener.actionPerformed(e);
            }
        });
        presentation.putClientProperty("selected", selected);
        this.updateCustomComponent(jRadioButton, presentation);
        return jRadioButton;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
    }

    protected void updateCustomComponent(JRadioButton radioButton, Presentation presentation) {
        radioButton.setText(presentation.getText());
        radioButton.setToolTipText(presentation.getDescription());
        radioButton.setMnemonic(presentation.getMnemonic());
        radioButton.setDisplayedMnemonicIndex(presentation.getDisplayedMnemonicIndex());
        radioButton.setSelected(Boolean.TRUE.equals(presentation.getClientProperty("selected")));
        radioButton.setEnabled(presentation.isEnabled());
        radioButton.setVisible(presentation.isVisible());

        if (!StringUtil.isEmpty(mActionCommand)) {
            radioButton.setActionCommand(mActionCommand);
        }
        if (mButtonGroup != null) {
            mButtonGroup.add(radioButton);
        }
    }
}
