package io.chengguo.api.debugger.ui.component;

import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.FoldRegion;
import com.intellij.openapi.editor.FoldingModel;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileTypes.PlainTextLanguage;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.LanguageTextField;
import com.intellij.util.Consumer;
import com.intellij.util.TextFieldCompletionProvider;
import com.intellij.util.TextFieldCompletionProviderDumbAware;
import com.intellij.util.textCompletion.TextCompletionUtil;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

/**
 * 可高亮变量的TextField
 *
 * @see com.intellij.openapi.externalSystem.service.ui.ExternalProjectPathField
 * @see com.intellij.ui.TextFieldWithAutoCompletionListProvider
 */
public class ApiDebuggerVariableField extends EditorTextField {

    private final boolean mShouldHaveBorder;
    private final Consumer<Editor> mEditorConstructionCallback;

    public ApiDebuggerVariableField(Project project) {
        this(project, true, false, true);
    }

    public ApiDebuggerVariableField(Project project, boolean shouldHaveBorder, boolean isViewer, boolean oneLineMode) {
        super(createDocument(project, ""), project, PlainTextLanguage.INSTANCE.getAssociatedFileType(), isViewer, oneLineMode);
        setBackground(UIUtil.getTextFieldBackground());
        mShouldHaveBorder = shouldHaveBorder;
        mEditorConstructionCallback = editor -> {
            collapseIfPossible(editor, project);
            editor.getSettings().setShowIntentionBulb(false);
        };
    }

    @Override
    protected boolean shouldHaveBorder() {
        return mShouldHaveBorder;
    }

    @Override
    protected void updateBorder(@NotNull EditorEx editor) {
        if (mShouldHaveBorder) {
            super.updateBorder(editor);
        } else {
            editor.setBorder(null);
        }
    }

    @Override
    protected EditorEx createEditor() {
        EditorEx editor = super.createEditor();
        if (mEditorConstructionCallback != null) {
            mEditorConstructionCallback.consume(editor);
        }
        return editor;
    }

    private static Document createDocument(Project project, @NotNull String text) {
        TextFieldCompletionProvider provider = new TextFieldCompletionProviderDumbAware() {
            @Override
            protected void addCompletionVariants(@NotNull String text, int offset, @NotNull String prefix, @NotNull CompletionResultSet result) {
                CompletionResultSet completionResult = result.caseInsensitive();
                for (int i = 0; i < 5; i++) {
                    completionResult.addElement(LookupElementBuilder.create("test:variable - " + i));
                }
                completionResult.stopHere();
            }
        };
        return LanguageTextField.createDocument(text, PlainTextLanguage.INSTANCE, project, new TextCompletionUtil.DocumentWithCompletionCreator(provider, true));
    }

    private static void collapseIfPossible(@NotNull Editor editor,
                                           @NotNull Project project) {
        String rawText = editor.getDocument().getText();
        collapse(editor, "变量");
    }

    public static void collapse(@NotNull Editor editor, @NotNull String placeholder) {
        final FoldingModel foldingModel = editor.getFoldingModel();
        foldingModel.runBatchFoldingOperation(() -> {
            for (FoldRegion region : foldingModel.getAllFoldRegions()) {
                foldingModel.removeFoldRegion(region);
            }
            FoldRegion region = foldingModel.addFoldRegion(0, editor.getDocument().getTextLength(), placeholder);
            if (region != null) {
                region.setExpanded(false);
            }
        });
    }


}
