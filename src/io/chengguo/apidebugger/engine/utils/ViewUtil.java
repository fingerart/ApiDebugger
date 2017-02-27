package io.chengguo.apidebugger.engine.utils;

import org.apache.http.util.TextUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.LinkedHashMap;

/**
 * Created by FingerArt on 16/10/1.
 */
public class ViewUtil {

    /**
     * 删除表格中选中的行
     *
     * @param table
     */
    public static void delSelectedRows(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) return;
        int selectedColumnCount = table.getSelectedRowCount();
        for (int i = 0; i < selectedColumnCount; i++) {
            model.removeRow(table.getSelectedRow());
        }
    }

    /**
     * 从Model中获取表格的数据内容
     *
     * @param table
     * @return
     */
    public static LinkedHashMap<String, String> getTableContent(TableModel table) {
        int rowCount = table.getRowCount();
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        for (int row = 0; row < rowCount; row++) {
            String key = (String) table.getValueAt(row, 0);
            if (TextUtils.isEmpty(key)) continue;
            String value = (String) table.getValueAt(row, 1);
            result.put(key, value);
        }
        return result;
    }
}
