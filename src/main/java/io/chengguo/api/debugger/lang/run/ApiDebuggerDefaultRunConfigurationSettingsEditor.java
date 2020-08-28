package io.chengguo.api.debugger.lang.run;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.ui.ApiDebugger;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ApiDebuggerDefaultRunConfigurationSettingsEditor extends SettingsEditor<ApiDebuggerDefaultRunConfiguration> {

    private final Project mProject;
    private JPanel mMainPanel;

    public ApiDebuggerDefaultRunConfigurationSettingsEditor(Project project) {
        mProject = project;
        setupUI();
    }

    private void setupUI() {
        mMainPanel = new JPanel();
        mMainPanel.setLayout(new GridLayoutManager(2, 1));

        JPanel panel = new JPanel(new GridLayoutManager(3, 2));
        panel.setBorder(IdeBorderFactory.createTitledBorder(ApiDebuggerBundle.message("api.debugger.run.configuration.configuration")));
        JBLabel jbLabel = new JBLabel();
        loadLabelText(jbLabel, ApiDebuggerBundle.message("api.debugger.run.configuration.environment"));
        panel.add(jbLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        panel.add(new TextFieldWithBrowseButton(), new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        mMainPanel.add(panel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null));
    }

    @Override
    protected void resetEditorFrom(@NotNull ApiDebuggerDefaultRunConfiguration s) {

    }

    @Override
    protected void applyEditorTo(@NotNull ApiDebuggerDefaultRunConfiguration s) throws ConfigurationException {

    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return mMainPanel;
    }


    private  void loadLabelText(final JLabel label, final String s) {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        char char1 = '\0';
        int length = -1;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '&') {
                if (++i == s.length()) {
                    break;
                }
                if (n == 0 && s.charAt(i) != '&') {
                    n = 1;
                    char1 = s.charAt(i);
                    length = sb.length();
                }
            }
            sb.append(s.charAt(i));
        }
        label.setText(sb.toString());
        if (n != 0) {
            label.setDisplayedMnemonic(char1);
            label.setDisplayedMnemonicIndex(length);
        }
    }
}
