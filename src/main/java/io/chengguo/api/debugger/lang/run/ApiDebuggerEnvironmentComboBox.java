package io.chengguo.api.debugger.lang.run;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.CollectionComboBoxModel;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ApiDebuggerEnvironmentComboBox extends ComboBox<ApiDebuggerEnvironmentComboBox.EnvironmentItem> {
    private static final EnvironmentItem EMPTY = new EnvironmentItem("<No Environment>", true);

    public ApiDebuggerEnvironmentComboBox() {
    }

    public void update(String selectedEnvName) {
        List<EnvironmentItem> items = new ArrayList<>();
        //TODO 获取所有环境变量
        items.add(EMPTY);
        setModel(new CollectionComboBoxModel<>(items));
        setRenderer(new ColoredListCellRenderer<EnvironmentItem>() {
            @Override
            protected void customizeCellRenderer(@NotNull JList<? extends EnvironmentItem> list, EnvironmentItem item, int index, boolean selected, boolean hasFocus) {
                String name = item.getName();
                boolean isValid = item.isValid();
                append(name, isValid ? SimpleTextAttributes.GRAYED_BOLD_ATTRIBUTES : SimpleTextAttributes.ERROR_ATTRIBUTES);
            }
        });
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
