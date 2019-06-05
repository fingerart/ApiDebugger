package io.chengguo.apidebugger.ui.custom;

import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBoxWithWidePopup;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.EditorTextField;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.AbstractTableCellEditor;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Created by fingerart on 17/8/6.
 */
public class FormDataTableCellEditor extends AbstractTableCellEditor implements TableCellRenderer {
    protected JPanel component;
    protected EditorTextField editor;
    protected ComboBoxWithWidePopup<String> combobox;

    public FormDataTableCellEditor(Project project, JBDebuggerFormTable.ItemInfo itemInfo) {
        component = new JPanel(new BorderLayout());
        editor = new EditorTextField("", project, StdFileTypes.PLAIN_TEXT);
        component.add(editor, BorderLayout.CENTER);
        combobox = new ComboBoxWithWidePopup<>(ContainerUtil.ar("Text", "File"));
        combobox.setSelectedItem(StringUtil.isEmpty(itemInfo.type) ? "Text" : itemInfo.type);
        combobox.setFocusable(false);
        combobox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                itemInfo.type = e.getItem().toString();
            }
        });
        component.add(combobox, BorderLayout.EAST);
    }

    public void setDocumentListener(DocumentListener documentListener) {
        editor.addDocumentListener(documentListener);
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
