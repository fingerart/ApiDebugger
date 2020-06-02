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

%state WAITING_REQUEST
%state WAITING_HEADER
%state WAITING_HEADER_VALUE

%%
<YYINITIAL> {
    ({WS} | {NL})+                              { return TokenType.WHITE_SPACE; }
    {END_OF_LINE_COMMENT}                       { return Api_LINE_COMMENT; }
    {MULTILINE_COMMENT}                         { return Api_MULTILINE_COMMENT; }
    "---"                                       { return Api_SEPARATOR; }
    {LETTER}+                                   { yybegin(WAITING_REQUEST); return Api_TITLE; }
}

<WAITING_REQUEST> {
    ({WS} | {NL})+                              { return TokenType.WHITE_SPACE; }
    ("GET" | "POST")                            { yybegin(WAITING_HEADER); return Api_METHOD; }
    "://"                                       { return Api_SCHEME_SEPARATOR;}

}

<WAITING_HEADER> {
    {WS}                                        {return TokenType.WHITE_SPACE; }
    {NL}                                        {return TokenType.WHITE_SPACE; }
    {KEY_CHARACTER}+                            { return Api_HEADER_FIELD_NAME; }
    ":"                                         { yybegin(WAITING_HEADER_VALUE); return Api_COLON; }
    {NL} {NL}                                   { yybegin(YYINITIAL);return TokenType.WHITE_SPACE; }
}

<WAITING_HEADER_VALUE> {
    ({WS} | {NL})+                              {return TokenType.WHITE_SPACE; }
    {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(WAITING_HEADER); return Api_HEADER_FIELD_VALUE; }
}

({NL}|{WS})+                                    { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
[^]                                             { return TokenType.BAD_CHARACTER; }
