package io.chengguo.apidebugger.ui.custom;

import com.intellij.ui.BooleanTableCellRenderer;
import com.intellij.ui.EditorTextFieldCellRenderer;
import com.intellij.ui.TableUtil;
import com.intellij.ui.table.TableView;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

/**
 * Created by FingerArt on 2017/07/27.
 */
public class JBDebuggerTable extends TableView {
    public JBDebuggerTable() {
        super(new ListTableModel(createColumnInfos(), createItems()));

        getColumnModel().getColumn(0).setResizable(false);
        TableUtil.setupCheckboxColumn(getColumnModel().getColumn(0));
    }

    private static ColumnInfo[] createColumnInfos() {
        TableCellRenderer booleanCellRenderer = createBooleanCellRenderer();
//        EditorTextFieldCellRenderer

        return new ColumnInfo[0];
    }

    private static List createItems() {
        return ContainerUtil.newSmartList(new ItemInfo(false, "", ""));
    }

    static class ItemInfo {
        public ItemInfo(boolean checked, String key, String value) {
            this.checked = checked;
            this.key = key;
            this.value = value;
        }

        boolean checked;
        String key;
        String value;
    }

    private static BooleanTableCellRenderer createBooleanCellRenderer() {
        return new BooleanTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(final JTable table,
                                                           final Object value,
                                                           final boolean isSelected,
                                                           final boolean hasFocus,
                                                           final int row,
                                                           final int column) {
                return setLabelColors(super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column), table, isSelected, row);
            }
        };
    }

    private static Component setLabelColors(final Component label, final JTable table, final boolean isSelected, final int row) {
        if (label instanceof JComponent) {
            ((JComponent) label).setOpaque(true);
        }
        label.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        label.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        return label;
    }
}
