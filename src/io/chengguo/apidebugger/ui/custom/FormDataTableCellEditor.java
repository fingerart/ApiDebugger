package io.chengguo.apidebugger.ui.custom;

import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBoxWithWidePopup;
import com.intellij.ui.EditorTextField;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.AbstractTableCellEditor;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by fingerart on 17/8/6.
 */
public class FormDataTableCellEditor extends AbstractTableCellEditor implements TableCellRenderer {
    protected JPanel component;
    protected EditorTextField editor;

    public FormDataTableCellEditor(Project project) {
        component = new JPanel(new BorderLayout());
        editor = new EditorTextField("", project, StdFileTypes.PLAIN_TEXT);
        component.add(editor, BorderLayout.CENTER);
        ComboBoxWithWidePopup<String> combobox = new ComboBoxWithWidePopup<>(ContainerUtil.ar("Text", "File"));
        combobox.setFocusable(false);
        combobox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        component.add(combobox, BorderLayout.EAST);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        editor.setText(((String) value));
        return component;
    }

    @Override
    public String getCellEditorValue() {
        return editor.getText();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        editor.setText(((String) value));
        return component;
    }
}
