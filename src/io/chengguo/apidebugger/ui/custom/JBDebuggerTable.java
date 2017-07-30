package io.chengguo.apidebugger.ui.custom;

import com.intellij.ui.BooleanTableCellRenderer;
import com.intellij.ui.TableUtil;
import com.intellij.ui.table.TableView;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import org.jetbrains.annotations.Nullable;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.util.List;

/**
 * Created by FingerArt on 2017/07/27.
 */
public class JBDebuggerTable extends TableView<JBDebuggerTable.ItemInfo> {

    public JBDebuggerTable(List<ItemInfo> list) {
        super(new ListTableModel<>(createColumnInfos(), list));
        setAutoResizeMode(AUTO_RESIZE_LAST_COLUMN);
        getColumnModel().getColumn(0).setResizable(false);
        TableUtil.setupCheckboxColumn(getColumnModel().getColumn(0));
    }

    private static ColumnInfo[] createColumnInfos() {
        ColumnInfo[] columnInfos = {
                new ColumnInfo<ItemInfo, Boolean>(" ") {
                    @Override
                    public Class getColumnClass() {
                        return Boolean.class;
                    }

                    @Override
                    public Boolean valueOf(ItemInfo o) {
                        return o.checked;
                    }

                    @Override
                    public boolean isCellEditable(ItemInfo itemInfo) {
                        return true;
                    }

                    @Override
                    public void setValue(ItemInfo itemInfo, final Boolean value) {
                        itemInfo.checked = value;
                    }

                    @Override
                    public TableCellRenderer getRenderer(ItemInfo injection) {
                        return new BooleanTableCellRenderer();
                    }
                },
                new ColumnInfo<ItemInfo, String>("Name") {
                    @Nullable
                    @Override
                    public String valueOf(ItemInfo itemInfo) {
                        return itemInfo.key;
                    }

                    @Override
                    public boolean isCellEditable(ItemInfo itemInfo) {
                        return true;
                    }

                    @Override
                    public void setValue(ItemInfo itemInfo, String value) {
                        itemInfo.key  = value;
                    }

                    @Nullable
                    @Override
                    public TableCellRenderer getRenderer(ItemInfo itemInfo) {
                        return new DefaultTableCellRenderer();
                    }
                },
                new ColumnInfo<ItemInfo, String>("Value") {
                    @Nullable
                    @Override
                    public String valueOf(ItemInfo itemInfo) {
                        return itemInfo.value;
                    }

                    @Override
                    public boolean isCellEditable(ItemInfo itemInfo) {
                        return true;
                    }

                    @Override
                    public void setValue(ItemInfo itemInfo, String value) {
                        itemInfo.value  = value;
                    }

                    @Nullable
                    @Override
                    public TableCellRenderer getRenderer(ItemInfo itemInfo) {
                        return new DefaultTableCellRenderer();
                    }
                }
        };
        return columnInfos;
    }

    public static class ItemInfo {
        public ItemInfo() {
            this(false, "", "");
        }

        public ItemInfo(boolean checked, String key, String value) {
            this.checked = checked;
            this.key = key;
            this.value = value;
        }

        boolean checked;
        String key;
        String value;
    }
}
