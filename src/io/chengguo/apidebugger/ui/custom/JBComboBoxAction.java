package io.chengguo.apidebugger.ui.custom;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 解决直接继承{@link ComboBoxAction}的情况下，无法自动更新{@link ComboBoxButton}的内容
 * <p>
 * Created by fingerart on 17/6/17.
 */
public abstract class JBComboBoxAction extends ComboBoxAction implements DumbAware, PropertyChangeListener {
    private ComboBoxButton comboBoxButton;

    public JBComboBoxAction() {
        getTemplatePresentation().addPropertyChangeListener(this);
    }

    @Override
    protected ComboBoxButton createComboBoxButton(Presentation presentation) {
        comboBoxButton = super.createComboBoxButton(presentation);
        return comboBoxButton;
    }

    @Override
    protected Condition<AnAction> getPreselectCondition() {
        return anAction -> {
            String text = getTemplatePresentation().getText();
            return !StringUtil.isEmpty(text) && text.equals(anAction.getTemplatePresentation().getText());
        };
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (comboBoxButton == null) return;
        String propertyName = evt.getPropertyName();
        if ("text".equals(propertyName)) {
            comboBoxButton.setText((String) evt.getNewValue());
        } else if ("description".equals(propertyName)) {
            String tooltip = KeymapUtil.createTooltipText(((String) evt.getNewValue()), this);
            comboBoxButton.setToolTipText(!tooltip.isEmpty() ? tooltip : null);
        } else if ("icon".equals(propertyName)) {
            comboBoxButton.setIcon((Icon) evt.getNewValue());
        } else if ("enabled".equals(propertyName)) {
            comboBoxButton.setEnabled((Boolean) evt.getNewValue());
        }
    }
}
