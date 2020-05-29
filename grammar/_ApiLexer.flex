package io.chengguo.api.debugger.lang.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static io.chengguo.api.debugger.lang.psi.BnfTypes.*;

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

SPACE=[ \t\n\x0B\f\r]+
ID=[a-zA-Z_0-9]+
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\\"|\\'|\\)*\")
NUMBER=[0-9]+
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*(.|\n)*\*"/"

%%
<YYINITIAL> {
  {WHITE_SPACE}        { return WHITE_SPACE; }

  "="                  { return BNF_OP_EQ; }
  "::="                { return BNF_OP_IS; }
  "|"                  { return BNF_OP_OR; }
  "?"                  { return BNF_OP_OPT; }
  "+"                  { return BNF_OP_ONEMORE; }
  "*"                  { return BNF_OP_ZEROMORE; }
  "&"                  { return BNF_OP_AND; }
  "!"                  { return BNF_OP_NOT; }
  ";"                  { return BNF_SEMICOLON; }
  "{"                  { return BNF_LEFT_BRACE; }
  "}"                  { return BNF_RIGHT_BRACE; }
  "["                  { return BNF_LEFT_BRACKET; }
  "]"                  { return BNF_RIGHT_BRACKET; }
  "("                  { return BNF_LEFT_PAREN; }
  ")"                  { return BNF_RIGHT_PAREN; }
  "<<"                 { return BNF_EXTERNAL_START; }
  ">>"                 { return BNF_EXTERNAL_END; }

  {SPACE}              { return BNF_SPACE; }
  {ID}                 { return BNF_ID; }
  {STRING}             { return BNF_STRING; }
  {NUMBER}             { return BNF_NUMBER; }
  {LINE_COMMENT}       { return BNF_LINE_COMMENT; }
  {BLOCK_COMMENT}      { return BNF_BLOCK_COMMENT; }

}

[^] { return BAD_CHARACTER; }
