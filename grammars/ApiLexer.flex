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

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
FIRST_VALUE_CHARACTER=[^# \n\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
LINE_COMMENT="//" [^\r\n]*
//KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "

%state WAITING_VALUE

%%
<YYINITIAL> {
  "##"                                                       { return ApiTypes.FLAG_TITLE; }
  {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*                  { return ApiTypes.TITLE_RAW_STRING; }
  {LINE_COMMENT}                                             { return ApiTypes.LINE_COMMENT; }

  <WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
  <WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }

  ({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
}