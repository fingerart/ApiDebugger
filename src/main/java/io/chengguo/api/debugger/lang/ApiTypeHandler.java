package io.chengguo.api.debugger.lang;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * 输入处理器
 */
public class ApiTypeHandler extends TypedHandlerDelegate {
    @NotNull
    @Override
    public Result beforeCharTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file, @NotNull FileType fileType) {
        if (fileType != ApiFileType.INSTANCE || (c != '{' && c != '-')) {
            return super.beforeCharTyped(c, project, editor, file, fileType);
        }
        final int offset = editor.getCaretModel().getOffset();
        final Document document = editor.getDocument();
        final char charBefore = getCharAt(document, offset - 1);
        final char charAfter = getCharAt(document, offset);
        if (charBefore == '{') {
            if (c == '{' && charAfter != '}') {
                return addBrace(project, editor, document, "{}}", 1);
            }
        } else if (charBefore == '-') {
            if (c == '-' && charAfter != '-') {
                return addBrace(project, editor, document, "-- ", 3);
            }
        }
        return super.beforeCharTyped(c, project, editor, file, fileType);
    }

    @NotNull
    private TypedHandlerDelegate.Result addBrace(@NotNull Project project, @NotNull Editor editor, @NotNull Document document, @NotNull String text, int caretShift) {
        PsiDocumentManager documentManager = PsiDocumentManager.getInstance(project);
        if (documentManager != null) {
            EditorModificationUtil.insertStringAtCaret(editor, text, true, caretShift);
            documentManager.commitDocument(document);
            return TypedHandlerDelegate.Result.STOP;
        }
        return TypedHandlerDelegate.Result.CONTINUE;
    }

    private static char getCharAt(Document document, int offset) {
        if (offset >= document.getTextLength() || offset < 0) {
            return '\0';
        }
        return document.getCharsSequence().charAt(offset);
    }
}
