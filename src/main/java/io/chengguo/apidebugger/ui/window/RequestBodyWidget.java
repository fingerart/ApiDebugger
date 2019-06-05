package io.chengguo.apidebugger.ui.window;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.table.JBTable;
import io.chengguo.apidebugger.ui.custom.JBDebuggerTable;
import io.chengguo.apidebugger.ui.custom.JBRadioAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import static io.chengguo.apidebugger.engine.utils.ViewUtil.setCursor;

/**
 * Created by fingerart on 17/5/17.
 */
public class RequestBodyWidget {
    public JPanel container;
    private JPanel headerContainer;
    private JBTable mFormData;
    private JBTable mUrlencoded;
    private JTextPane mEtfRaw;
    private JPanel selectFilePanel;
    private JLabel fileNameLable;
    private JButton selectFileButton;
    private SimpleToolWindowPanel simpleToolWindowPanel1;
    private CardLayout headerTypeCardLayout;
    ButtonGroup typeBody;
    private Project project;

    private String filePath;

    public RequestBodyWidget(Project project) {
        this.project = project;
        headerTypeCardLayout = (CardLayout) headerContainer.getLayout();

        setCursor(Cursor.HAND_CURSOR, selectFilePanel);

        selectFilePanel.addMouseListener(selectFileListener);
        selectFileButton.addMouseListener(selectFileListener);
    }

    private void createUIComponents() {
        simpleToolWindowPanel1 = new SimpleToolWindowPanel(true, true);

        mFormData = new JBDebuggerTable();
        mUrlencoded = new JBDebuggerTable();

        typeBody = new ButtonGroup();
//        DefaultButtonModel buttonModel = new DefaultButtonModel();
//        buttonModel.setActionCommand("FormData");
//        typeBody.setSelected(buttonModel, true);

        ActionGroup group = new DefaultActionGroup(
                new JBRadioAction("form-data", "FormData", typeBody, previewTypeListener, true),
                new JBRadioAction("x-www-urlencoded", "XWwwFormUrlencoded", typeBody, previewTypeListener),
                new JBRadioAction("raw", "Raw", typeBody, previewTypeListener),
                new JBRadioAction("binary", "Binary", typeBody, previewTypeListener)
        );

        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, group, true);
        simpleToolWindowPanel1.setToolbar(toolbar.getComponent());
        simpleToolWindowPanel1.setContent(new JPanel(new BorderLayout()));
    }

    private ActionListener previewTypeListener = e -> headerTypeCardLayout.show(headerContainer, e.getActionCommand());

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
        ButtonModel selection = typeBody.getSelection();
        return selection != null ? selection.getActionCommand() : "";
    }

    public Map<String, String> bodyFormData() {
        return ((JBDebuggerTable) mFormData).getKeyValue();
    }

    public Map<String, String> bodyUrlencode() {
        return ((JBDebuggerTable) mUrlencoded).getKeyValue();
    }

    public String bodyRaw() {
        return mEtfRaw.getText();
    }

    public String bodyBinary() {
        return filePath;
    }
}
