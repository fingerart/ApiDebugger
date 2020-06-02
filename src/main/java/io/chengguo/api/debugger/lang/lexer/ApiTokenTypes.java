package io.chengguo.api.debugger.lang.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;

public class ApiTokenTypes {
    public static final TokenSet WHITE_SPACE = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(Api_LINE_COMMENT);
    public static final TokenSet BLOCK_COMMENT = TokenSet.create(Api_MULTILINE_COMMENT);
    public static final TokenSet KEYWORD = TokenSet.create(Api_HEADER_FIELD_NAME, Api_METHOD);
    public static final TokenSet STRING = TokenSet.create(Api_HEADER_FIELD_VALUE);
    public static final TokenSet SEPARATOR = TokenSet.create(Api_COLON, Api_SEPARATOR);
}
