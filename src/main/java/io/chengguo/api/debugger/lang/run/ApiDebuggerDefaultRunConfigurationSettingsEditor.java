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
    private JBRadioButton mRunSingleFileBtn;
    private JBRadioButton mRunAllFileBtn;

    public ApiDebuggerDefaultRunConfigurationSettingsEditor(Project project) {
        mProject = project;
        setupUI();
        mRunSingleFileBtn.addItemListener(createRunTypeChangeListener(RunFileType.SINGLE_FILE));
        mRunAllFileBtn.addItemListener(createRunTypeChangeListener(RunFileType.ALL_FILE));
    }

    private ItemListener createRunTypeChangeListener(RunFileType type) {
        return event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                updateState(type);
            }
        };
    }

    private void setupUI() {
        mMainPanel = new JPanel();
        mMainPanel.setLayout(new GridLayoutManager(2, 1));

        JPanel panel = new JPanel(new GridLayoutManager(4, 2));
        // 分割线
        panel.setBorder(IdeBorderFactory.createTitledBorder(ApiDebuggerBundle.message("api.debugger.run.configuration.configuration")));
        // 单个请求/所有请求
        JBLabel runTypeJBL = new JBLabel(ApiDebuggerBundle.message("api.debugger.run.configuration.label.run_type"));
        panel.add(runTypeJBL, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        JPanel runTypePanel = new JPanel(new GridLayoutManager(1, 2));
        panel.add(runTypePanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        mRunSingleFileBtn = new JBRadioButton(RunFileType.SINGLE_FILE.typeName());
        mRunAllFileBtn = new JBRadioButton(RunFileType.ALL_FILE.typeName());
        ButtonGroup runTypeBtnGroup = new ButtonGroup();
        runTypeBtnGroup.add(mRunSingleFileBtn);
        runTypeBtnGroup.add(mRunAllFileBtn);
        runTypePanel.add(mRunSingleFileBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        runTypePanel.add(mRunAllFileBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        // 环境变量
        JBLabel envJBL = new JBLabel(ApiDebuggerBundle.message("api.debugger.run.configuration.label.environment"));
        panel.add(envJBL, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        panel.add(new ComboBox(new String[]{"Default", "TEST"}), new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        // 文件路径
        JBLabel filePathJBL = new JBLabel(ApiDebuggerBundle.message("api.debugger.run.configuration.label.file"));
        panel.add(filePathJBL, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        panel.add(new TextFieldWithBrowseButton(), new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        JBLabel requestJBL = new JBLabel(ApiDebuggerBundle.message("api.debugger.run.configuration.label.request"));
        panel.add(requestJBL, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null));
        panel.add(new ComboBox(new String[]{"Default", "TEST"}), new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

        mMainPanel.add(panel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null));
    }

    @Override
    protected void resetEditorFrom(@NotNull ApiDebuggerDefaultRunConfiguration configuration) {

    }

    @Override
    protected void applyEditorTo(@NotNull ApiDebuggerDefaultRunConfiguration configuration) throws ConfigurationException {

    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return mMainPanel;
    }

    private void updateState(RunFileType runFileType) {

    }
}
