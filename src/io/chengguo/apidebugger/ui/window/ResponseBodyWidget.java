package io.chengguo.apidebugger.ui.window;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import io.chengguo.apidebugger.ui.custom.JBComboBoxAction;
import io.chengguo.apidebugger.ui.custom.JBRadioAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by fingerart on 17/5/27.
 */
public class ResponseBodyWidget {
    private Project mProject;
    private CardLayout mPreviewTypeCardLayout;
    public JPanel container;
    private JPanel previewTypeContainer;

    private JTextPane previewTextPane;
    private SimpleToolWindowPanel simpleToolWindowPanel1;
    private JTextPane rawTextPane;
    private JTextPane prettyTextPane;

    public ResponseBodyWidget(Project project) {
        mProject = project;
        mPreviewTypeCardLayout = ((CardLayout) previewTypeContainer.getLayout());
    }

    private void createUIComponents() {
        simpleToolWindowPanel1 = new SimpleToolWindowPanel(true, true);
        JBComboBoxAction comboBoxAction = createFormatTypeComboAction();

        ButtonGroup buttonGroup = new ButtonGroup();
        ActionGroup group = new DefaultActionGroup(new JBRadioAction("Pretty", "Pretty", buttonGroup, previewTypeListener, true),
                new JBRadioAction("Raw", "Raw", buttonGroup, previewTypeListener),
                new JBRadioAction("Preview", "Preview", buttonGroup, previewTypeListener),
                comboBoxAction,
                new AnAction("wrap", "wrap", AllIcons.Actions.ToggleSoftWrap) {
                    @Override
                    public void actionPerformed(AnActionEvent anActionEvent) {

                    }
                });

        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, group, true);
        simpleToolWindowPanel1.setToolbar(toolbar.getComponent());
        simpleToolWindowPanel1.setContent(new JPanel(new BorderLayout()));
    }

    private ActionListener previewTypeListener = e -> mPreviewTypeCardLayout.show(previewTypeContainer, e.getActionCommand());

    /**
     * create format combo
     *
     * @return
     */
    @NotNull
    private JBComboBoxAction createFormatTypeComboAction() {
        return new JBComboBoxAction() {
            private JBComboBoxAction formatCombo = this;
            private final String[] TYPES = {"HTML", "JSON", "XML", "Text", "Auto"};

            @Override
            public void update(AnActionEvent e) {
                e.getPresentation().setText(TYPES[0]);
            }

            @NotNull
            @Override
            protected DefaultActionGroup createPopupActionGroup(JComponent jComponent) {
                DefaultActionGroup group = new DefaultActionGroup();
                for (String type : TYPES) {
                    group.add(new AnAction(type) {
                        @Override
                        public void actionPerformed(AnActionEvent anActionEvent) {
                            formatCombo.getTemplatePresentation().setText(anActionEvent.getPresentation().getText());
                        }
                    });
                }
                return group;
            }
        };
    }

    public void showPretty(String text) {
        prettyTextPane.setText(text);
    }

    public void showRaw(String text) {
        rawTextPane.setText(text);
    }

    public void showPreview(String text) {
        previewTextPane.setText(text);
    }
}