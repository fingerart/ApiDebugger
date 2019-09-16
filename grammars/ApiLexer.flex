package io.chengguo.api.debugger.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import com.intellij.psi.TokenType;
import io.chengguo.api.debugger.lang.psi.ApiTypes;

%%

%{
  public _ApiLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _ApiLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

NL=\R
WS=[\ \t\f]
FIRST_VALUE_CHARACTER=[^# \n\f\\]
VALUE_CHARACTER=[^\n\f\\]
LINE_COMMENT="//" [^\r\n]*
//KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "

%state WAITING_VALUE

%%
<YYINITIAL> {
    ({NL}|{WS})+                               { return TokenType.WHITE_SPACE; }

    "##"                                                {  return ApiTypes.FLAG_TITLE; }
    "GET"                                                {  return ApiTypes.GET; }
    "POST"                                                {  return ApiTypes.POST; }

    {LINE_COMMENT}                                      { return ApiTypes.LINE_COMMENT; }
    {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*           { return ApiTypes.RAW_STRING; }
}

<WAITING_VALUE> {
    {WS}+                                      { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
//    {NL}({NL}|{WHITE_SPACE})+                           { return TokenType.WHITE_SPACE; }
}

    [^]                                                 { return TokenType.BAD_CHARACTER; }