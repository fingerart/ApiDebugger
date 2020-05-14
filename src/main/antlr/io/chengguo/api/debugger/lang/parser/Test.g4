grammar Test;



test:STRING_LITERAL;

STRING_LITERAL:     '"' (~["\\\r\n] | EscapeSequence)* '"';

fragment HexDigit
    : [0-9a-fA-F]
    ;

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;


LF
    : [\r\n]+
    ;

WS : [ \t\n\r]+ -> channel(HIDDEN) ;