package io.chengguo.api.debugger.lang.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;
import io.chengguo.api.debugger.lang.psi.ApiTypes;

public class ApiTokenTypes {
    public static final TokenSet WHITE_SPACE = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(ApiTypes.Api_COMMENT);
    public static final TokenSet KEYWORD = TokenSet.create(ApiTypes.Api_KEY);
    public static final TokenSet VALUE = TokenSet.create(ApiTypes.Api_VALUE);
    public static final TokenSet SEPARATOR = TokenSet.create(ApiTypes.Api_PROPERTY);
}
