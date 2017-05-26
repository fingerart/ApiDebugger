package io.chengguo.apidebugger.ui.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.impl.FocusTrackbackProvider;
import com.intellij.openapi.wm.ex.IdeFocusTraversalPolicy;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.FocusTrackback;
import com.intellij.ui.components.JBComboBoxLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.editors.JBComboBoxTableCellEditorComponent;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_COLUMN_NAMES;
import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_EMPTY_DATA;
import static javax.swing.AbstractButton.FOCUS_PAINTED_CHANGED_PROPERTY;

/**
 * Created by fingerart on 17/5/17.
 */
public class RequestBodyWidget {
    public JPanel container;
    private JRadioButton mRbFormData;
    private JRadioButton mRbXWwwFormUrlencoded;
    private JRadioButton mRbRaw;
    private JRadioButton mRbBinary;
    private JPanel headerContainer;
    private JBTable mformData;
    private JBTable mUrlencoded;
    private EditorTextField mEtfRaw;

    private CardLayout headerTypeCardLayout;
    private Project project;

    public RequestBodyWidget(Project project) {
        this.project = project;
        headerTypeCardLayout = (CardLayout) headerContainer.getLayout();

        mformData.setModel(new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES));
        mformData.getTableHeader().setReorderingAllowed(false);

        mUrlencoded.setModel(new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES));
        mUrlencoded.getTableHeader().setReorderingAllowed(false);

        mEtfRaw.setBorder(null);
        mEtfRaw.setFocusTraversalPolicy(new IdeFocusTraversalPolicy());
        JBComboBoxTableCellEditorComponent b;
        JBComboBoxLabel c;
        JComboBox d;

        mRbFormData.addActionListener(headerTypeListener);
        mRbXWwwFormUrlencoded.addActionListener(headerTypeListener);
        mRbRaw.addActionListener(headerTypeListener);
        mRbBinary.addActionListener(headerTypeListener);
    }

    private final ActionListener headerTypeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            headerTypeCardLayout.show(headerContainer, e.getActionCommand());
        }
    };
}
