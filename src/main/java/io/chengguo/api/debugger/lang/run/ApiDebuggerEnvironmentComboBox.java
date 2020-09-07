package io.chengguo.api.debugger.lang.run;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiFile;
import com.intellij.ui.CollectionComboBoxModel;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import io.chengguo.api.debugger.lang.environment.ApiEnvironmentIndex;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApiDebuggerEnvironmentComboBox extends ComboBox<ApiDebuggerEnvironmentComboBox.EnvironmentItem> {
    private static final EnvironmentItem DEFAULT = new EnvironmentItem(ApiEnvironment.empty().getName(), true);

    public ApiDebuggerEnvironmentComboBox() {
    }

    public void update(Project project, PsiFile file, String selectedEnvName) {
        List<EnvironmentItem> items = findAllEnvironments(project, file);
        EnvironmentItem selectedEnvironmentItem = findSelectedEnvironmentItem(items, selectedEnvName);
        setModel(new CollectionComboBoxModel<>(items, selectedEnvironmentItem));
        setRenderer(new ColoredListCellRenderer<EnvironmentItem>() {
            @Override
            protected void customizeCellRenderer(@NotNull JList<? extends EnvironmentItem> list, EnvironmentItem item, int index, boolean selected, boolean hasFocus) {
                String name = item.getName();
                boolean isValid = item.isValid();
                append(name, isValid ? SimpleTextAttributes.REGULAR_ATTRIBUTES : SimpleTextAttributes.ERROR_ATTRIBUTES);
            }
        });
    }

    @NotNull
    private EnvironmentItem findSelectedEnvironmentItem(List<EnvironmentItem> items, String envName) {
        if (StringUtil.isEmpty(envName)) return DEFAULT;
        for (EnvironmentItem item : items) {
            if (item.getName().equals(envName)) {
                return item;
            }
        }
        return new EnvironmentItem(envName, false);
    }

    @NotNull
    private List<EnvironmentItem> findAllEnvironments(Project project, PsiFile file) {
        Collection<String> environments = ApiEnvironmentIndex.getAllEnvironments(project, file);
        ArrayList<EnvironmentItem> result = new ArrayList<>();
        for (String environment : environments) {
            result.add(new EnvironmentItem(environment, true));
        }
        result.add(DEFAULT);
        return result;
    }

    @Override
    public EnvironmentItem getSelectedItem() {
        return (EnvironmentItem) super.getSelectedItem();
    }

    public static class EnvironmentItem {
        private final String name;
        private final boolean isValid;

        public EnvironmentItem(@NotNull String name, boolean isValid) {
            this.name = name;
            this.isValid = isValid;
        }

        public String getName() {
            return name;
        }

        public boolean isValid() {
            return isValid;
        }
    }
}
