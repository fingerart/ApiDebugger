package com.thoughtworks.pli.pub_editor.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import java.util.*;

%%

%class _PubSpecLexer
%implements FlexLexer
%unicode
%type IElementType
%function advance
//%debug
%{
IndentationStack indentationStack = new IndentationStack();
public int currentIndentation() { return indentationStack.current(); }
public List<Integer> allIndentations() { return indentationStack.all(); }
%}

Comment = "#" .*
LineSeparator = \r\n | \r | \n
WhiteSpace = [\ ]
NonWhiteSpace = [^\ ]
BlankChar = [\ \t\f]
NonBlankChar = [^\ \r\n\t\f]
InlineKey = {NonBlankChar}+ ":" {BlankChar}+
ChildrenKey = {NonBlankChar}+ ":" {BlankChar}* {LineSeparator}
MultiLineStringKey = {NonBlankChar}+ ":" {BlankChar}* ">" {LineSeparator}
Indentation = {WhiteSpace}+
InlineValue = .+

%state $newLine
%state $value
%state $multiLineString
%state $multiLineStringAfterIndentation
%state $inlineValue
%state $multiChildren

%%
<YYINITIAL> {
    {Indentation}    { indentationStack.push(yytext().length()); return PubTokenTypes.Indentation(); }
    {Comment}        { return PubTokenTypes.Comment(); }
    {ChildrenKey}    { yybegin(YYINITIAL);return PubTokenTypes.ChildrenKey(); }
    {InlineKey}      { yybegin($inlineValue); return PubTokenTypes.InlineKey(); }
    {MultiLineStringKey} { yybegin($multiLineString); return PubTokenTypes.MultiLineStringKey(); }
    {LineSeparator}  { return PubTokenTypes.LineSeparator(); }
}
<$inlineValue> {
    {InlineValue}    { yybegin(YYINITIAL); return PubTokenTypes.InlineValue(); }
}
<$multiLineString> {
    {Indentation}    {
                         int currentIndent = yytext().length();
                         if(currentIndent >= indentationStack.current()) {
                         yybegin($multiLineStringAfterIndentation);
                             return PubTokenTypes.Indentation();
                         } else {
                             yypushback(currentIndent);
                             yybegin(YYINITIAL);
                         }
                     }
    {NonWhiteSpace}+ { yypushback(yytext().length()); yybegin(YYINITIAL); }
    <$multiLineStringAfterIndentation> {
        {NonBlankChar}+ {  return PubTokenTypes.OneLineOfMultiLineString(); }
        {LineSeparator} {  yybegin($multiLineString); return PubTokenTypes.LineSeparator(); }
    }
}
.                    { return PubTokenTypes.BadCharacter(); }
