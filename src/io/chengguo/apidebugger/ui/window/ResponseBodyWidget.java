package io.chengguo.apidebugger.ui.window;

import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.highlighter.HtmlFileHighlighter;
import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.ide.highlighter.XmlFileHighlighter;
import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.ide.highlighter.custom.CustomFileHighlighter;
import com.intellij.json.JsonFileType;
import com.intellij.json.highlighting.JsonSyntaxHighlighterFactory;
import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.tree.IElementType;
import io.chengguo.apidebugger.ui.custom.JBComboBoxAction;
import io.chengguo.apidebugger.ui.custom.JBRadioAction;
import org.apache.http.Header;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by fingerart on 17/5/27.
 */
public class ResponseBodyWidget {
    private final Editor prettyEditor;
    private final Editor rawEditor;
    private final Editor previewEditor;
    private Project mProject;
    private CardLayout mPreviewTypeCardLayout;
    public JPanel container;
    private JPanel previewTypeContainer;

    private SimpleToolWindowPanel simpleToolWindowPanel1;
    private JPanel prettyContainer;
    private JPanel rawContainer;
    private JPanel previewContainer;
    private ButtonGroup buttonGroup;
    private static final IElementType TextElementType = new IElementType("TEXT", Language.ANY);

    public ResponseBodyWidget(Project project) {
        mProject = project;
        mPreviewTypeCardLayout = ((CardLayout) previewTypeContainer.getLayout());

        prettyEditor = createEditor();
        prettyContainer.add(prettyEditor.getComponent(), BorderLayout.CENTER);
        rawEditor = createEditor();
        rawContainer.add(rawEditor.getComponent(), BorderLayout.CENTER);
        previewEditor = createEditor();
        previewContainer.add(previewEditor.getComponent(), BorderLayout.CENTER);
    }

    private void createUIComponents() {
        simpleToolWindowPanel1 = new SimpleToolWindowPanel(true, true);

        JBComboBoxAction comboBoxAction = createFormatTypeComboAction();
        buttonGroup = new ButtonGroup();
        ActionGroup group = new DefaultActionGroup(
                new JBRadioAction("Pretty", "Pretty", buttonGroup, previewTypeListener, true),
                new JBRadioAction("Raw", "Raw", buttonGroup, previewTypeListener),
                new JBRadioAction("Preview", "Preview", buttonGroup, previewTypeListener)/*,
                comboBoxAction,
                new AnAction("wrap", "wrap", AllIcons.Actions.ToggleSoftWrap) {
                    @Override
                    public void actionPerformed(AnActionEvent anActionEvent) {

                    }
                }*/);

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

    private Editor createEditor() {
        PsiFile myFile = null;
        EditorFactory editorFactory = EditorFactory.getInstance();
        Document doc = myFile == null
                ? editorFactory.createDocument("")
                : PsiDocumentManager.getInstance(mProject).getDocument(myFile);
        Editor editor = editorFactory.createEditor(doc, mProject);
        EditorSettings editorSettings = editor.getSettings();
        editorSettings.setVirtualSpace(false);
        editorSettings.setLineMarkerAreaShown(false);
        editorSettings.setIndentGuidesShown(false);
        editorSettings.setFoldingOutlineShown(true);
        editorSettings.setAdditionalColumnsCount(3);
        editorSettings.setAdditionalLinesCount(3);
        editorSettings.setLineNumbersShown(true);
        editorSettings.setCaretRowShown(true);

        editor.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void documentChanged(DocumentEvent e) {
            }
        });
        ((EditorEx) editor).setHighlighter(createHighlighter(FileTypes.PLAIN_TEXT));
        return editor;
    }

    private EditorHighlighter createHighlighter(LanguageFileType fileType) {
//        FileType fileType = FileTypeManager.getInstance().getFileTypeByExtension("");
//        if (fileType == null) {
//            fileType = FileTypes.PLAIN_TEXT;
//        }

        SyntaxHighlighter originalHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(fileType, null, null);
        if (originalHighlighter == null) {
            originalHighlighter = new PlainSyntaxHighlighter();
        }

        EditorColorsScheme scheme = EditorColorsManager.getInstance().getGlobalScheme();
        LayeredLexerEditorHighlighter highlighter = new LayeredLexerEditorHighlighter(getFileHighlighter(fileType), scheme);// TODO: 2017/8/3 HTML
        highlighter.registerLayer(TextElementType, new LayerDescriptor(originalHighlighter, ""));
        return highlighter;
    }

    @Nullable
    private PsiFile createFile(final String text, final String name) {
        PsiFile file = PsiFileFactory.getInstance(mProject).createFileFromText(name + ".txt.ft", HtmlFileType.INSTANCE, text, 0, true);// TODO: 2017/8/3 HTML
        file.getViewProvider().putUserData(FileTemplateManager.DEFAULT_TEMPLATE_PROPERTIES, FileTemplateManager.getInstance(mProject).getDefaultProperties());
        return file;
    }

    public void showPretty(String text, Header[] contentType) {
        WriteCommandAction.runWriteCommandAction(mProject, () -> prettyEditor.getDocument().setText(text));
        LanguageFileType fileType = getFileType(contentType);
        ((EditorEx) prettyEditor).setHighlighter(createHighlighter(fileType));
    }

    private SyntaxHighlighter getFileHighlighter(FileType fileType) {
        if (fileType == HtmlFileType.INSTANCE) {
            return new HtmlFileHighlighter();
        } else if (fileType == XmlFileType.INSTANCE) {
            return new XmlFileHighlighter();
        } else if (fileType == JsonFileType.INSTANCE) {
            return JsonSyntaxHighlighterFactory.getSyntaxHighlighter(fileType, mProject, null);
        }
        return new PlainSyntaxHighlighter();
    }

    private LanguageFileType getFileType(Header[] contentTypes) {
        if (contentTypes != null && contentTypes.length > 0) {
            Header contentType = contentTypes[0];
            if (contentType.getValue().contains("text/html")) {
                return HtmlFileType.INSTANCE;
            } else if (contentType.getValue().contains("application/xml")) {
                return XmlFileType.INSTANCE;
            } else if (contentType.getValue().contains("application/json")) {
                return JsonFileType.INSTANCE;
            }
        }
        return PlainTextFileType.INSTANCE;
    }

    public void showRaw(String text) {
        WriteCommandAction.runWriteCommandAction(mProject, () -> rawEditor.getDocument().setText(text));
    }

    public void showPreview(String text) {
        WriteCommandAction.runWriteCommandAction(mProject, () -> prettyEditor.getDocument().setText(text));
    }
}