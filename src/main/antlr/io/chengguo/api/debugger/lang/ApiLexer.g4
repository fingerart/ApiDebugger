lexer grammar ApiLexer;

FlagTitle:      '---' -> pushMode(ModeTitle);
FlagDesOpen:    '"""' -> pushMode(ModeDescription);
FlagDesClose:   '"""';
Sub:            '-';
Colon:          ':';

Method
    : POST
    | GET
    ;

NL:                 '\r'? '\n';
WS:                 [ \t\r\n] -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

mode ModeTitle;

ModeTitleClose: [\r\n]+ -> popMode, type(NL);
LineText:   ~[\r\n]+;

mode ModeDescription;

ModeDescriptionClose: '"""' -> popMode, type(FlagDesClose);
Text
    : (~[\\\b\f\t"] | EscapeSequence)+
    ;

fragment Identifier
    : LETTER (LETTER  | DIGIT)*
    ;

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;

fragment HexDigits
    : HexDigit ((HexDigit | '_')* HexDigit)?
    ;

fragment HexDigit
    : [0-9a-fA-F]
    ;

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [a-zA-Z]
    ;

// methods

fragment POST
    : [pP] [oO] [sS] [tT]
    ;

fragment GET
    : [gG] [eE] [tT]
    ;