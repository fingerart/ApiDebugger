package io.chengguo.apidebugger.ui.window;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.table.JBTable;
import io.chengguo.apidebugger.engine.utils.ViewUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_COLUMN_NAMES;
import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_EMPTY_DATA;
import static io.chengguo.apidebugger.engine.utils.ViewUtil.setCursor;

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
    private JBTable mFormData;
    private JBTable mUrlencoded;
    private JTextPane mEtfRaw;
    private JPanel selectFilePanel;
    private JLabel fileNameLable;
    private JButton selectFileButton;
    private ButtonGroup TypeBody;

    private CardLayout headerTypeCardLayout;
    private Project project;

    private String filePath;

    public RequestBodyWidget(Project project) {
        this.project = project;
        headerTypeCardLayout = (CardLayout) headerContainer.getLayout();

        mFormData.setModel(new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES));
        mFormData.getTableHeader().setReorderingAllowed(false);

        mUrlencoded.setModel(new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES));
        mUrlencoded.getTableHeader().setReorderingAllowed(false);

        setCursor(Cursor.HAND_CURSOR, mRbFormData, mRbXWwwFormUrlencoded, mRbRaw, mRbBinary, selectFilePanel);

        mRbFormData.addActionListener(headerTypeListener);
        mRbXWwwFormUrlencoded.addActionListener(headerTypeListener);
        mRbRaw.addActionListener(headerTypeListener);
        mRbBinary.addActionListener(headerTypeListener);

        selectFilePanel.addMouseListener(selectFileListener);
        selectFileButton.addMouseListener(selectFileListener);
    }

    private final ActionListener headerTypeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            headerTypeCardLayout.show(headerContainer, e.getActionCommand());
        }
    };

    private final MouseAdapter selectFileListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, true, true, false, false);
            VirtualFile toSelect = ProjectManager.getInstance().getOpenProjects()[0].getBaseDir();
            FileChooser.chooseFile(descriptor, null, toSelect, virtualFile -> {
                if (virtualFile.exists()) {
                    filePath = virtualFile.getPath();
                    fileNameLable.setText(virtualFile.getName());
                }
            });
        }
    };

    public String bodyType() {
        return TypeBody.getSelection().getActionCommand();
    }

    public Map<String, String> bodyUrlencode() {
        return ViewUtil.getTableContent(mUrlencoded.getModel());
    }

    public String bodyRaw() {
        return mEtfRaw.getText();
    }

    public String bodyBinary() {
        return filePath;
    }
}
