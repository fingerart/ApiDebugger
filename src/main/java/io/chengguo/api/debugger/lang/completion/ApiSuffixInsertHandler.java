package io.chengguo.api.debugger.lang.completion;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDocumentManager;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import org.jetbrains.annotations.NotNull;

public class ApiSuffixInsertHandler implements InsertHandler<LookupElement> {
    public static final ApiSuffixInsertHandler VARIABLE_OPTION = new ApiSuffixInsertHandler("}}");
    public static final ApiSuffixInsertHandler HEADER_OPTION = new ApiSuffixInsertHandler("=");
    public static final ApiSuffixInsertHandler SCHEME = new ApiSuffixInsertHandler("://");
    public static final ApiSuffixInsertHandler FIELD_SEPARATOR = new ApiSuffixInsertHandler(": ");
    private final String mySuffix;
    private final String myShortSuffix;

    public ApiSuffixInsertHandler(@NotNull String suffix) {
        this.mySuffix = suffix;
        this.myShortSuffix = suffix.trim();
    }

    public void handleInsert(@NotNull InsertionContext context, @NotNull LookupElement item) {
        final Project project = context.getProject();
        if (project != null) {
            Editor editor = context.getEditor();
            Document document = editor.getDocument();
            int offset = ApiPsiUtils.skipWhitespacesForward(editor.getCaretModel().getOffset(), document.getCharsSequence());
            if (document.getTextLength() == offset || !this.isEqualsToSuffix(document, offset)) {
                EditorModificationUtil.insertStringAtCaret(editor, this.mySuffix);
                PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());
            }
            editor.getCaretModel().moveToOffset(offset + this.mySuffix.length());
        }
    }

    private boolean isEqualsToSuffix(@NotNull Document document, int offset) {
        int endOffset = offset + this.myShortSuffix.length() - 1;
        return document.getTextLength() > endOffset && StringUtil.equals((CharSequence) this.myShortSuffix, (CharSequence) document.getCharsSequence().subSequence(offset, endOffset + 1).toString());
    }

}
