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

COMMENT="//".*
IDENTIFIER=[^0-9][a-zA-Z0-9\-_]*

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return WHITE_SPACE; }

  "GET"              { return GET; }
  "POST"             { return POST; }
  "PUT"              { return PUT; }
  "PATCH"            { return PATCH; }
  "DELETE"           { return DELETE; }

  {COMMENT}          { return COMMENT; }
  {IDENTIFIER}       { return IDENTIFIER; }

}

[^] { return BAD_CHARACTER; }
