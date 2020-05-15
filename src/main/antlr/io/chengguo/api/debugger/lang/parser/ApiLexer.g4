lexer grammar ApiLexer;

// Methods

GET:                'GET';
HEAD:               'HEAD';
POST:               'POST';
PUT:                'PUT';
DELETE:             'DELETE';
CONNECT:            'CONNECT';
OPTIONS:            'OPTIONS';
TRACE:              'TRACE';

// Separators

LCURL:              '{';
RCURL:              '}';
DOLLAR:             '$';
Slash:              '/';
DOT:                '.';

// Operators

ASSIGN:             '=';
COLON:              ':';
QUESTION:           '?';
BITAND:             '&';
PROTCOL:            '://';
WELL:               '#';
AT:                 '@';

HexUri:             ('%' HexDigit HexDigit)+;
Digits:             Digit+;

OWS:                SP | HTAB;
HTAB:               '\t';
SP:                 ' ';
NL:                 '\u000A' | '\u000D' '\u000A';// \r\n

WS :                [\r\n\u000C]+ -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

Identifier:         Letter LetterOrDigit*;

LineStrExprStart
    : '${'
    ;

InfoStart
    : '###'
    ;

StringLiteral:      '"' StringContent* '"';
StringContent:      ~["\\\r\n] | EscapeSequence;

fragment Digit
    : [0-9]
    ;

fragment HexDigit
    : [0-9a-fA-F]
    ;

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;

fragment LetterOrDigit
    : Letter
    | [0-9]
    ;

fragment Letter
    : [a-zA-Z$_] // these are the "java letters" below 0x7F
    | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;

mode InfoMode ;

INFO_CLOSE
    : '###' -> popMode
    ;

TITLE_CONTENT
    : (~'#' | StringContent)+
    ;