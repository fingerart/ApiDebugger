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

LineTerminator=\r|\n|\r\n
SINGLE_LINE_STRING=[^\n\r]*
FLAG_TITLE="#"
FALG_DESCRIPTION="##"
METHOD=(POST|GET)

TITLE={FLAG_TITLE}\ {SINGLE_LINE_STRING}
DESCRIPTION={FALG_DESCRIPTION}\ {SINGLE_LINE_STRING}
REQUEST_LINE=\ {SINGLE_LINE_STRING}

%state $title

%%
<YYINITIAL> {
  {WHITE_SPACE}           { return WHITE_SPACE; }

  "KEY"                   { return KEY; }
  "HEADER_SEPARATOR"      { return HEADER_SEPARATOR; }
  "VALUE"                 { return VALUE; }
  "FLAG_DESCRIPTION"      { return FLAG_DESCRIPTION; }
  "SINGLE_LINE_STRING"    { return SINGLE_LINE_STRING; }
  "FLAG_TITLE"            { return FLAG_TITLE; }
  "METHOD"                { return METHOD; }
  "URI"                   { return URI; }
}

[^] { return BAD_CHARACTER; }
