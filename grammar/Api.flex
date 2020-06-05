package io.chengguo.api.debugger.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import java.util.LinkedList;

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
    /**
     * 切换状态，会记录切换前的状态
     *
     * @param newState
     */
    public void pushState(int newState) {
        yypush();
        yybegin(newState);
    }

    /**
     * 切换至上一个状态
     */
    public void popState() {
        yypop();
    }

    /**
     * 当路径匹配完成，切换至下一个状态
     */
    private void onPathFinish() {
        if (yylength() == 1) {
            yypushback(yylength());
            pushState(IN_HEADER);
        } else {
            yypushback(yylength());
            pushState(IN_MESSAGE_BODY);
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
LBRACES = "{{"
RBRACES = "}}"
ID = ({LETTER} | "_") ({LETTER} | {DIGIT} | "_")*

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
%state IN_DESCRIPTION
%state IN_DESCRIPTION_KEY
%state IN_DESCRIPTION_VALUE

%%
<YYINITIAL> {
    ({WS} | {NL})+                              { return TokenType.WHITE_SPACE; }
    {END_OF_LINE_COMMENT}                       { return Api_LINE_COMMENT; }
    {MULTILINE_COMMENT}                         { return Api_MULTILINE_COMMENT; }
    "--" "-" [^\r\n]*                           { return Api_SEPARATOR; }
    {METHOD}                                    { yypushback(yylength()); pushState(IN_HTTP_REQUEST); }
    "-"                                         { yypushback(yylength()); pushState(IN_DESCRIPTION); }
}

<IN_DESCRIPTION> {
    {WS}+                                       { return TokenType.WHITE_SPACE; }
    "-"                                         { pushState(IN_DESCRIPTION_KEY); return Api_HYPHEN; }
    ":"                                         { pushState(IN_DESCRIPTION_VALUE); return Api_COLON; }
    {NL}                                        { popState(); return TokenType.WHITE_SPACE; }
}

<IN_DESCRIPTION_KEY> {
    [^\r\n:]+                                   { popState(); return Api_DESCRIPTION_KEY; }
}

<IN_DESCRIPTION_VALUE> {
    [^\r\n]+                                    { return Api_LINE_TEXT; }
    {NL}                                        { popState(); return TokenType.WHITE_SPACE;}
}

<IN_VARIABLE> {
    {ID}                                        { return Api_IDENTIFIER; }
    {RBRACES}                                   { popState(); return Api_RBRACES;}
}

<IN_HTTP_REQUEST> {
    ({WS} | {NL})+                              { return TokenType.WHITE_SPACE; }
    {METHOD}                                    { pushState(IN_HTTP_PATH); return Api_METHOD; }
}

<IN_HTTP_PATH> {
    {WS}+                                       { return TokenType.WHITE_SPACE; }
    "https"                                     { return Api_HTTPS; }
    "http"                                      { return Api_HTTP; }
    "://"                                       { pushState(IN_HTTP_REQUEST_HOST); return Api_SCHEME_SEPARATOR; }
    ":"                                         { pushState(IN_HTTP_REQUEST_PORT); return Api_COLON; }
    "/"                                         { pushState(IN_HTTP_PATH_SEGMENT); return Api_SLASH; }
    "?"                                         { pushState(IN_HTTP_QUERY); return Api_QUESTION_MARK; }
    {NL}+                                       { onPathFinish(); }
}

<IN_HTTP_REQUEST_HOST> {
    {LBRACES}                                   { pushState(IN_VARIABLE); return Api_LBRACES; }
    [^\r\n:/?#"{{"]+                            { return Api_HOST_VALUE; }
    [:/?#]                                      { yypushback(yylength()); popState(); }
    {NL}+                                       { onPathFinish(); }
}

<IN_HTTP_REQUEST_PORT> {
    {LBRACES}                                   { pushState(IN_VARIABLE); return Api_LBRACES; }
    {DIGIT}+                                    { return Api_PORT_SEGMENT; }
    [\r\n/?#]                                   { yypushback(yylength()); popState(); } //Default 80
}

<IN_HTTP_PATH_SEGMENT> {
    [^\r\n/?#]+                                 { return Api_SEGMENT; }
    [\r\n/?#]                                   { yypushback(yylength()); popState(); } //Segment can be empty
}

<IN_HTTP_QUERY> {
    {LBRACES}                                   { pushState(IN_VARIABLE); return Api_LBRACES; }
    [^\r\n="{{"]+                               { return Api_QUERY_NAME; }// Key
    "="                                         { pushState(IN_HTTP_QUERY_VALUE); return Api_EQUALS; }
    {NL}+                                       { onPathFinish(); }
}

<IN_HTTP_QUERY_VALUE> {
    {LBRACES}                                   { pushState(IN_VARIABLE); return Api_LBRACES; }
    [^\r\n&"{{"]+                                   { return Api_QUERY_VALUE; }
    "&"                                         { popState(); return Api_AMPERSAND;}
    {NL}+                                       { onPathFinish(); }
}

<IN_HEADER> {
    {LBRACES}                                   { pushState(IN_VARIABLE); return Api_LBRACES; }
    {WS}+                                       { return TokenType.WHITE_SPACE; }
    {NL}                                        { return TokenType.WHITE_SPACE; }
    [^\ \n\t\f:"{{"]+                           { return Api_HEADER_FIELD_NAME; }
    ":"                                         { pushState(IN_HEADER_VALUE); return Api_COLON; }
    {NL} {NL}+                                  { pushState(IN_MESSAGE_BODY); return TokenType.WHITE_SPACE; }
}

<IN_HEADER_VALUE> {
    {WS}+                                       { return TokenType.WHITE_SPACE; }
    {LBRACES}                                   { pushState(IN_VARIABLE); return Api_LBRACES; }
    [^\r\n"{{"]+                                { return Api_HEADER_FIELD_VALUE; }
    {NL}                                        { popState(); return TokenType.WHITE_SPACE; }
    {NL} {NL}+                                  { pushState(IN_MESSAGE_BODY);return TokenType.WHITE_SPACE; }
}

<IN_MESSAGE_BODY> {
    ({NL}|{WS})+                                { return TokenType.WHITE_SPACE; }
    [^\ \r\n\t\f]+                              { pushState(YYINITIAL); return Api_MESSAGE_TEXT; }
}

//BEFORE_MESSAGE_BODY

({NL}|{WS})+                                    { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
[^]                                             { return TokenType.BAD_CHARACTER; }
