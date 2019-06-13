package io.chengguo.api.debugger.lang.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;

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

EOL=\R
WHITE_SPACE=\s+

LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*([^*]|\*+[^*/])*(\*+"/")?
IDENTIFIER=[^0-9][a-zA-Z0-9\-_]*
STRING=\"([^\\\"\r\n]|\\[^\r\n])*\"?

%%
<YYINITIAL> {
  {WHITE_SPACE}        { return WHITE_SPACE; }

  "#"                  { return FALG_TITLE; }
  "###"                { return FALG_DESCRIPTION; }
  "GET"                { return GET; }
  "POST"               { return POST; }
  "PUT"                { return PUT; }
  "PATCH"              { return PATCH; }
  "DELETE"             { return DELETE; }

  {LINE_COMMENT}       { return LINE_COMMENT; }
  {BLOCK_COMMENT}      { return BLOCK_COMMENT; }
  {IDENTIFIER}         { return IDENTIFIER; }
  {STRING}             { return STRING; }

}

[^] { return BAD_CHARACTER; }
