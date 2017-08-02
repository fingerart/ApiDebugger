package io.chengguo.apidebugger.ui.custom;

import com.intellij.ui.BooleanTableCellRenderer;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.CollectionItemEditor;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.table.TableModelEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.util.List;

/**
 * Created by FingerArt on 2017/07/27.
 */
public class JBDebuggerTable2 extends TableModelEditor {
    public JBDebuggerTable2() {
        super(createColumnInfos(), createItemss(), "safdsa");
        modelListener(new DataChangedListener() {

            @Override
            public void dataChanged(@NotNull ColumnInfo columnInfo, int i) {

            }

            @Override
            public void tableChanged(@NotNull TableModelEvent tableModelEvent) {

            }
        });
    }

    private static CollectionItemEditor createItemss() {
        return new DialogItemEditor<ItemInfo>() {

            @NotNull
            @Override
            public Class<? extends ItemInfo> getItemClass() {
                return ItemInfo.class;
            }

            @Override
            public ItemInfo clone(@NotNull ItemInfo itemInfo, boolean b) {
                return new ItemInfo(itemInfo.checked, itemInfo.key, itemInfo.value);
            }

            @Override
            public void edit(@NotNull ItemInfo itemInfo, @NotNull Function<ItemInfo, ItemInfo> function, boolean b) {

            }

            @Override
            public void applyEdited(@NotNull ItemInfo itemInfo, @NotNull ItemInfo t1) {

            }
        };
    }

    private static ColumnInfo[] createColumnInfos() {
        ColumnInfo[] columnInfos = {
                new EditableColumnInfo<ItemInfo, Boolean>() {
                    @Override
                    public Class<?> getColumnClass() {
                        return Boolean.class;
                    }

                    @Nullable
                    @Override
                    public Boolean valueOf(ItemInfo itemInfo) {
                        return itemInfo.checked;
                    }

                    @Override
                    public void setValue(ItemInfo itemInfo, Boolean value) {
                        itemInfo.checked = value;
                    }
                },
                new EditableColumnInfo<ItemInfo, String>("Key") {
                    @Nullable
                    @Override
                    public String valueOf(ItemInfo itemInfo) {
                        return itemInfo.key;
                    }

                    @Override
                    public void setValue(ItemInfo itemInfo, String value) {
                        itemInfo.key = value;
                    }

                    @Override
                    public Class<?> getColumnClass() {
                        return String.class;
                    }
                },
                new EditableColumnInfo<ItemInfo, String>("Value") {
                    @Nullable
                    @Override
                    public String valueOf(ItemInfo itemInfo) {
                        return itemInfo.value;
                    }

                    @Override
                    public void setValue(ItemInfo itemInfo, String value) {
                        itemInfo.value = value;
                    }

                    @Override
                    public Class<?> getColumnClass() {
                        return String.class;
                    }
                }
        };
        return columnInfos;
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
