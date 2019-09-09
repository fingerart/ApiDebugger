package io.chengguo.api.debugger.lang;

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
RAW_STRING=[a-zA-Z0-9]*

%%
<YYINITIAL> {
  {WHITE_SPACE}       { return WHITE_SPACE; }

  "##"                { return FLAG_TITLE; }

  {LINE_COMMENT}      { return LINE_COMMENT; }
  {RAW_STRING}        { return RAW_STRING; }

}

[^] { return BAD_CHARACTER; }
