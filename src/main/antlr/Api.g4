grammar Api;

api
    : info*
    ;

info
    : title description? attribute*
    ;

title
    : '---' Keyword
    ;

description
    : '"""' Keyword? '"""'
    ;

attribute
    : '-' key ':' value
    ;

key
    : Keyword
    ;

value
    : Keyword
    ;

Keyword
    : (~[ \\\b\f\t\r\n\-:"] | EscapeSequence)+
    ;

WS:                 [ \t\r\n] -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

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