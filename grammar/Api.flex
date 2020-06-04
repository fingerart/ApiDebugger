package io.chengguo.api.debugger.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import com.intellij.psi.TokenType;
import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;

%%

%class ApiLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

%{
    private int mPrevState = YYINITIAL;

    /**
     * 切换状态，会记录切换前的状态
     * @param nextState
     */
    public void switchState(final int nextState) {
        this.mPrevState = this.yystate();
        this.yybegin(nextState);
    }

    /**
     * 切换至上一个状态
     */
    public void switchPrevState() {
        switchState(mPrevState);
    }

    /**
     * 重制状态到 {@code ApiLexer#YYINITIAL}
     */
    public void reset() {
        switchState(YYINITIAL);
    }

    /**
     * 当路径匹配完成，切换至下一个状态
     */
    private void onPathFinish() {
        if(yylength()==1){
            yypushback(yylength()); switchState(IN_HEADER);
        }else {
            switchState(IN_MESSAGE_BODY);
        }
    }
%}

NL=\R
WS=[\ \t\f]
LETTER = [a-zA-Z]
DIGIT =  [0-9]
END_OF_LINE_COMMENT=("//")[^\r\n]*
MULTILINE_COMMENT = "/*" ( ([^"*"]|[\r\n])* ("*"+ [^"*""/"] )? )* ("*" | "*"+"/")?
METHOD = "OPTIONS" | "GET" | "HEAD" | "POST" | "PUT" | "DELETE" | "TRACE" | "CONNECT"

KEY_CHARACTER=[^:\ \n\t\f\\] | "\\ "
FIRST_VALUE_CHARACTER=[^ \r\n\f\\] | "\\"{NL} | "\\".
VALUE_CHARACTER=[^\n\f\\] | "\\"{NL} | "\\".

%state IN_HTTP_REQUEST
%state IN_HTTP_PATH
%state IN_HTTP_REQUEST_HOST
%state IN_HTTP_REQUEST_PORT
%state IN_HTTP_PATH_SEGMENT
%state IN_HTTP_QUERY
%state IN_HTTP_QUERY_VALUE
%state IN_HEADER
%state IN_HEADER_VALUE
%state IN_MESSAGE_BODY
%state IN_VARIABLE

%%
<YYINITIAL> {
    ({WS} | {NL})+                              { return TokenType.WHITE_SPACE; }
    {END_OF_LINE_COMMENT}                       { return Api_LINE_COMMENT; }
    {MULTILINE_COMMENT}                         { return Api_MULTILINE_COMMENT; }
    "--" "-" [^\r\n]*                                       { return Api_SEPARATOR; }
    {LETTER}+                                   { switchState(IN_HTTP_REQUEST); return Api_TITLE; }
    "{{"                                        { switchState(IN_VARIABLE); return Api_LBRACES; }
}

<IN_VARIABLE> {
    ({LETTER} | '_') ({LETTER} | {DIGIT} | '_')* { return Api_IDENTIFIER; }
    "}}"                                        { switchPrevState(); return Api_RBRACES;}
}

<IN_HTTP_REQUEST> {
    ({WS} | {NL})+                              { return TokenType.WHITE_SPACE; }
    {METHOD}                                    { switchState(IN_HTTP_PATH); return Api_METHOD; }
}

<IN_HTTP_PATH> {
    {WS}+                                       { return TokenType.WHITE_SPACE; }
    "https"                                     { return Api_HTTPS; }
    "http"                                      { return Api_HTTP; }
    "://"                                       { switchState(IN_HTTP_REQUEST_HOST); return Api_SCHEME_SEPARATOR; }
    ":"                                         { switchState(IN_HTTP_REQUEST_PORT); return Api_COLON; }
    "/"                                         { yypushback(yylength()); switchState(IN_HTTP_PATH_SEGMENT); }
    "?"                                         { switchState(IN_HTTP_QUERY); return Api_QUESTION_MARK; }
    {NL}+                                       { onPathFinish(); return TokenType.WHITE_SPACE; }
}

<IN_HTTP_REQUEST_HOST> {
    [^\r\n:/?#]+                                { switchPrevState();return Api_HOST_VALUE; }
}

<IN_HTTP_REQUEST_PORT> {
    {DIGIT}+                                    { switchPrevState();return Api_PORT_SEGMENT; }
}

<IN_HTTP_PATH_SEGMENT> {
    "/"                                         { return Api_SLASH; }
    [^\r\n/?#]+                                 { switchPrevState(); return Api_SEGMENT; }
    {NL}+                                       { onPathFinish(); return TokenType.WHITE_SPACE; }
}

<IN_HTTP_QUERY> {
    [^\r\n=]+                                   { return Api_QUERY_NAME; }
    "="                                         { switchState(IN_HTTP_QUERY_VALUE); return Api_EQUALS; }
    {NL}+                                       { onPathFinish(); return TokenType.WHITE_SPACE; }
}

<IN_HTTP_QUERY_VALUE> {
    [^\r\n&]+                                   { return Api_QUERY_VALUE; }
    "&"                                         { switchPrevState(); return Api_AMPERSAND;}
    {NL}+                                       { onPathFinish(); return TokenType.WHITE_SPACE; }
}

<IN_HEADER> {
    {WS}+                                       { return TokenType.WHITE_SPACE; }
    {NL}                                        { return TokenType.WHITE_SPACE; }
    [^\ \n\t\f:]+                               { return Api_HEADER_FIELD_NAME; }
    ":"                                         { switchState(IN_HEADER_VALUE); return Api_COLON; }
    {NL} {NL}+                                  { switchState(IN_MESSAGE_BODY); return TokenType.WHITE_SPACE; }
}

<IN_HEADER_VALUE> {
    {WS}+                                       { return TokenType.WHITE_SPACE; }
    {NL}                                        { switchPrevState(); return TokenType.WHITE_SPACE; }
    [^\r\n]+                                    { switchPrevState(); return Api_HEADER_FIELD_VALUE; }
    {NL} {NL}+                                  { switchState(IN_MESSAGE_BODY);return TokenType.WHITE_SPACE; }
}

<IN_MESSAGE_BODY> {
    ({NL}|{WS})+                                { return TokenType.WHITE_SPACE; }
    [^\ \r\n\t\f]+                              { switchState(YYINITIAL); return Api_MESSAGE_TEXT; }
}

//BEFORE_MESSAGE_BODY

({NL}|{WS})+                                    { switchState(YYINITIAL); return TokenType.WHITE_SPACE; }
[^]                                             { return TokenType.BAD_CHARACTER; }
