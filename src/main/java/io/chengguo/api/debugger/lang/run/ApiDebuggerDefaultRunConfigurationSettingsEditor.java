package io.chengguo.api.debugger.lang.run;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ApiDebuggerDefaultRunConfigurationSettingsEditor extends SettingsEditor<ApiDebuggerDefaultRunConfiguration> {

    private final Project mProject;
    private JPanel mMainPanel;
    private JBRadioButton mRunSingleRequestBtn;
    private JBRadioButton mRunAllInFileBtn;
    private ApiDebuggerEnvironmentComboBox mEnvComboBox;

    public ApiDebuggerDefaultRunConfigurationSettingsEditor(Project project) {
        mProject = project;
        setupUI();
        mRunSingleRequestBtn.addItemListener(createRunTypeChangeListener(RunFileType.SINGLE_REQUEST));
        mRunAllInFileBtn.addItemListener(createRunTypeChangeListener(RunFileType.ALL_IN_FILE));
    }

    private ItemListener createRunTypeChangeListener(RunFileType type) {
        return event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                ApiDebuggerEnvironmentComboBox.EnvironmentItem environment = mEnvComboBox.getSelectedItem();
                String environmentName = environment != null ? environment.getName() : null;
                updateState(type, environmentName);
            }
        };
    }

    private void setupUI() {
        mMainPanel = new JPanel();
        mMainPanel.setLayout(new GridLayoutManager(2, 1));

        JPanel panel = new JPanel(new GridLayoutManager(4, 2));
        // 分割线
        panel.setBorder(IdeBorderFactory.createTitledBorder(ApiDebuggerBundle.message("api.debugger.run.configuration.configuration")));
        // 单个请求/文件中的所有请求
        JBLabel runTypeJBL = new JBLabel(ApiDebuggerBundle.message("api.debugger.run.configuration.label.run_type"));
        panel.add(runTypeJBL, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        JPanel runTypePanel = new JPanel(new GridLayoutManager(1, 2));
        panel.add(runTypePanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        mRunSingleRequestBtn = new JBRadioButton(RunFileType.SINGLE_REQUEST.typeName());
        mRunAllInFileBtn = new JBRadioButton(RunFileType.ALL_IN_FILE.typeName());
        ButtonGroup runTypeBtnGroup = new ButtonGroup();
        runTypeBtnGroup.add(mRunSingleRequestBtn);
        runTypeBtnGroup.add(mRunAllInFileBtn);
        runTypePanel.add(mRunSingleRequestBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        runTypePanel.add(mRunAllInFileBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        // 环境变量
        JBLabel envJBL = new JBLabel(ApiDebuggerBundle.message("api.debugger.run.configuration.label.environment"));
        panel.add(envJBL, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        mEnvComboBox = new ApiDebuggerEnvironmentComboBox();
        panel.add(mEnvComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        // 文件路径
        JBLabel filePathJBL = new JBLabel(ApiDebuggerBundle.message("api.debugger.run.configuration.label.file"));
        panel.add(filePathJBL, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        panel.add(new TextFieldWithBrowseButton(), new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        JBLabel requestJBL = new JBLabel(ApiDebuggerBundle.message("api.debugger.run.configuration.label.request"));
        panel.add(requestJBL, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        panel.add(new ComboBox(new String[]{"Default", "TEST"}), new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        mMainPanel.add(panel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null));
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return mMainPanel;
    }

    @Override
    protected void resetEditorFrom(@NotNull ApiDebuggerDefaultRunConfiguration configuration) {
        ApiDebuggerDefaultRunConfiguration.Settings settings = configuration.getSettings();
        updateState(settings.getRunFileType(), settings.getEnvName());
    }

    @Override
    protected void applyEditorTo(@NotNull ApiDebuggerDefaultRunConfiguration configuration) throws ConfigurationException {
        ApiDebuggerDefaultRunConfiguration.Settings settings = configuration.getSettings();
        settings.setRunFileType(getSelectedRunFileType());
        settings.setEnvName(mEnvComboBox.getSelectedItem().getName());
    }

    private RunFileType getSelectedRunFileType() {
        if (mRunAllInFileBtn.isSelected()) {
            return RunFileType.ALL_IN_FILE;
        }
        return RunFileType.SINGLE_REQUEST;
    }

    private void switchRunFileType(RunFileType runFileType) {
        if (runFileType == RunFileType.ALL_IN_FILE && !mRunAllInFileBtn.isSelected()) {
            mRunAllInFileBtn.setSelected(true);
        }
        if (runFileType == RunFileType.SINGLE_REQUEST && !mRunSingleRequestBtn.isSelected()) {
            mRunSingleRequestBtn.setSelected(true);
        }
    }

    private void updateState(RunFileType runFileType, String envName) {
        switchRunFileType(runFileType);
        mEnvComboBox.update(envName);
    }
}
