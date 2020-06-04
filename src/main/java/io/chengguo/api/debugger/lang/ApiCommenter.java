package io.chengguo.api.debugger.lang;

import com.intellij.codeInsight.generation.CommenterDataHolder;
import com.intellij.codeInsight.generation.SelfManagingCommenter;
import com.intellij.lang.Commenter;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.util.text.CharArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Api file commenter
 */
public class ApiCommenter implements Commenter, SelfManagingCommenter<CommenterDataHolder> {

    private final String LINE_COMMENT_PREFIX = "//";
    private final String BLOCK_COMMENT_PREFIX = "/*";
    private final String BLOCK_COMMENT_SUFFIX = "*/";

    @Nullable
    @Override
    public String getLineCommentPrefix() {
        return LINE_COMMENT_PREFIX;
    }

    @Nullable
    @Override
    public String getBlockCommentPrefix() {
        return BLOCK_COMMENT_PREFIX;
    }

    @Nullable
    @Override
    public String getBlockCommentSuffix() {
        return BLOCK_COMMENT_SUFFIX;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return BLOCK_COMMENT_PREFIX;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return BLOCK_COMMENT_SUFFIX;
    }

    @Nullable
    @Override
    public CommenterDataHolder createLineCommentingState(final int startLine, final int endLine, @NotNull final Document document, @NotNull final PsiFile file) {
        return null;
    }

    @Nullable
    @Override
    public CommenterDataHolder createBlockCommentingState(final int selectionStart, final int selectionEnd, @NotNull final Document document, @NotNull final PsiFile file) {
        return null;
    }

    @Override
    public void commentLine(final int line, final int offset, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        document.insertString(offset, LINE_COMMENT_PREFIX);
    }

    @Override
    public void uncommentLine(final int line, final int offset, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        if (CharArrayUtil.regionMatches(document.getCharsSequence(), offset, LINE_COMMENT_PREFIX)) {
            document.deleteString(offset, offset + LINE_COMMENT_PREFIX.length());
        }
    }

    @Override
    public boolean isLineCommented(final int line, final int offset, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        return CharArrayUtil.regionMatches(document.getCharsSequence(), offset, LINE_COMMENT_PREFIX);
    }

    @Nullable
    @Override
    public String getCommentPrefix(final int line, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        return LINE_COMMENT_PREFIX;
    }

    @Nullable
    public TextRange getBlockCommentRange(final int selectionStart, final int selectionEnd, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        return null;
    }

    @Nullable
    public String getBlockCommentPrefix(final int selectionStart, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        return this.getBlockCommentPrefix();
    }

    @Nullable
    public String getBlockCommentSuffix(final int selectionEnd, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        return this.getBlockCommentSuffix();
    }

    @Override
    public void uncommentBlockComment(final int startOffset, final int endOffset, final Document document, final CommenterDataHolder data) {
        if (CharArrayUtil.regionMatches(document.getCharsSequence(), startOffset, BLOCK_COMMENT_PREFIX) && CharArrayUtil.regionMatches(document.getCharsSequence(), endOffset - BLOCK_COMMENT_SUFFIX.length(), BLOCK_COMMENT_SUFFIX)) {
            document.deleteString(startOffset, BLOCK_COMMENT_PREFIX.length());
            document.deleteString(endOffset - BLOCK_COMMENT_SUFFIX.length(), endOffset);
        }
    }

    @NotNull
    public TextRange insertBlockComment(final int startOffset, final int endOffset, final Document document, final CommenterDataHolder data) {
        final TextRange textRange = new TextRange(0, 0);
        return textRange;
    }
}
