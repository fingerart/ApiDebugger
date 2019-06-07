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
 * TODO 暂时无效
 * Api file commenter
 */
public class ApiDebuggerCommenter implements Commenter, SelfManagingCommenter<CommenterDataHolder> {

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
        if (document == null) {
            $$$reportNull$$$0(0);
        }
        if (file == null) {
            $$$reportNull$$$0(1);
        }
        return null;
    }

    @Nullable
    @Override
    public CommenterDataHolder createBlockCommentingState(final int selectionStart, final int selectionEnd, @NotNull final Document document, @NotNull final PsiFile file) {
        if (document == null) {
            $$$reportNull$$$0(2);
        }
        if (file == null) {
            $$$reportNull$$$0(3);
        }
        return null;
    }

    @Override
    public void commentLine(final int line, final int offset, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        if (document == null) {
            $$$reportNull$$$0(4);
        }
        if (data == null) {
            $$$reportNull$$$0(5);
        }
        document.insertString(offset, LINE_COMMENT_PREFIX);
    }

    @Override
    public void uncommentLine(final int line, final int offset, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        if (document == null) {
            $$$reportNull$$$0(6);
        }
        if (data == null) {
            $$$reportNull$$$0(7);
        }
        if (CharArrayUtil.regionMatches(document.getCharsSequence(), offset, LINE_COMMENT_PREFIX)) {
            document.deleteString(offset, offset + LINE_COMMENT_PREFIX.length());
        }
    }

    @Override
    public boolean isLineCommented(final int line, final int offset, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        if (document == null) {
            $$$reportNull$$$0(8);
        }
        if (data == null) {
            $$$reportNull$$$0(9);
        }
        return CharArrayUtil.regionMatches(document.getCharsSequence(), offset, LINE_COMMENT_PREFIX);
    }

    @Nullable
    @Override
    public String getCommentPrefix(final int line, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        if (document == null) {
            $$$reportNull$$$0(10);
        }
        if (data == null) {
            $$$reportNull$$$0(11);
        }
        return LINE_COMMENT_PREFIX;
    }

    @Nullable
    public TextRange getBlockCommentRange(final int selectionStart, final int selectionEnd, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        if (document == null) {
            $$$reportNull$$$0(12);
        }
        if (data == null) {
            $$$reportNull$$$0(13);
        }
        return null;
    }

    @Nullable
    public String getBlockCommentPrefix(final int selectionStart, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        if (document == null) {
            $$$reportNull$$$0(14);
        }
        if (data == null) {
            $$$reportNull$$$0(15);
        }
        return this.getBlockCommentPrefix();
    }

    @Nullable
    public String getBlockCommentSuffix(final int selectionEnd, @NotNull final Document document, @NotNull final CommenterDataHolder data) {
        if (document == null) {
            $$$reportNull$$$0(16);
        }
        if (data == null) {
            $$$reportNull$$$0(17);
        }
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
        if (textRange == null) {
            $$$reportNull$$$0(18);
        }
        return textRange;
    }

    private static void $$$reportNull$$$0(final int n) {
        String s;
        switch (n) {
            default: {
                s = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            }
            case 18: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        int n2 = 0;
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 18: {
                n2 = 2;
                break;
            }
        }
        final Object[] array = new Object[n2];
        switch (n) {
            default: {
                array[0] = "document";
                break;
            }
            case 1:
            case 3: {
                array[0] = "file";
                break;
            }
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            case 15:
            case 17: {
                array[0] = "data";
                break;
            }
            case 18: {
                array[0] = "com/intellij/ws/http/request/HttpRequestCommenter";
                break;
            }
        }
        switch (n) {
            default: {
                array[1] = "com/intellij/ws/http/request/HttpRequestCommenter";
                break;
            }
            case 18: {
                array[1] = "insertBlockComment";
                break;
            }
        }
        switch (n) {
            default: {
                array[2] = "createLineCommentingState";
                break;
            }
            case 2:
            case 3: {
                array[2] = "createBlockCommentingState";
                break;
            }
            case 4:
            case 5: {
                array[2] = "commentLine";
                break;
            }
            case 6:
            case 7: {
                array[2] = "uncommentLine";
                break;
            }
            case 8:
            case 9: {
                array[2] = "isLineCommented";
                break;
            }
            case 10:
            case 11: {
                array[2] = "getCommentPrefix";
                break;
            }
            case 12:
            case 13: {
                array[2] = "getBlockCommentRange";
                break;
            }
            case 14:
            case 15: {
                array[2] = "getBlockCommentPrefix";
                break;
            }
            case 16:
            case 17: {
                array[2] = "getBlockCommentSuffix";
                break;
            }
            case 18: {
                break;
            }
        }
        final String format = String.format(s, array);
        RuntimeException ex = null;
        switch (n) {
            default: {
                ex = new IllegalArgumentException(format);
                break;
            }
            case 18: {
                ex = new IllegalStateException(format);
                break;
            }
        }
        throw ex;
    }
}
