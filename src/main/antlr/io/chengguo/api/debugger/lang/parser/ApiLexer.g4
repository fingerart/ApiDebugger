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

LCURL:             '{';
RCURL:             '}';
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
//SP:                 ' ';

HexUri:            ('%' HexDigit HexDigit)+;
Digits:             Digit+;
StringLiteral:     '"' (~["\\\r\n] | EscapeSequence)* '"';

LF:                 [\r\n]+;

WS : [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

Identifier:         Letter LetterOrDigit*;

LineStrExprStart
    : '${' -> pushMode(StringExpression)
    ;

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

mode StringExpression;

StrExpr_RCURL: RCURL -> popMode, type(RCURL) ;