package io.chengguo.apidebugger.ui.custom;

import com.intellij.ui.treeStructure.treetable.TreeTableModel;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

/**
 * Created by fingerart on 17/6/22.
 */
public class RequestHeaderTreeTableModel extends DefaultTreeModel  implements TreeTableModel {

    public RequestHeaderTreeTableModel() {
        super(null);

    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getColumnName(int i) {
        return null;
    }

    @Override
    public Class getColumnClass(int i) {
        return null;
    }

    @Override
    public Object getValueAt(Object o, int i) {
        return null;
    }

    @Override
    public boolean isCellEditable(Object o, int i) {
        return false;
    }

    @Override
    public void setValueAt(Object o, Object o1, int i) {

    }

    @Override
    public void setTree(JTree jTree) {

    }
}
