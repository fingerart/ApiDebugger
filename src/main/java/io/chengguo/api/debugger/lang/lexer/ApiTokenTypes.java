package io.chengguo.api.debugger.lang.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;

public class ApiTokenTypes {
    public static final TokenSet WHITE_SPACE = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(Api_LINE_COMMENT);
    public static final TokenSet BLOCK_COMMENT = TokenSet.create(Api_MULTILINE_COMMENT);
    public static final TokenSet METHOD_TYPE = TokenSet.create(Api_GET, Api_POST, Api_DELETE, Api_HEAD, Api_PUT, Api_TRACE, Api_OPTIONS, Api_CONNECT);
    public static final TokenSet HEADER_FIELD_NAME = TokenSet.create(Api_HEADER_FIELD_NAME);
    public static final TokenSet HEADER_FIELD_VALUE = TokenSet.create(Api_HEADER_FIELD_VALUE);
    public static final TokenSet QUERY_PARAMETER_KEY = TokenSet.create(Api_QUERY_NAME);
    public static final TokenSet QUERY_PARAMETER_VALUE = TokenSet.create(Api_QUERY_VALUE);
    public static final TokenSet REQUEST_BODY = TokenSet.create(Api_REQUEST_MESSAGE_GROUP);
    public static final TokenSet VARIABLE_NAME = TokenSet.create(Api_IDENTIFIER);
    public static final TokenSet VARIABLE_NAME_BRACES = TokenSet.create(Api_LBRACES, Api_RBRACES);
    public static final TokenSet API_BLOCK_SEPARATOR = TokenSet.create(Api_SEPARATOR);
}
