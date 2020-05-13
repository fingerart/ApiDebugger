grammar Api;

api
    : info http
    ;

info
    : TITLE DESCRIPTION?
    ;

http
    : method uri
    ;

variable
    : '$' '{' ID '}'
    ;

method:
	'GET'
	| 'HEAD'
	| 'POST'
	| 'PUT'
	| 'DELETE'
	| 'CONNECT'
	| 'OPTIONS'
	| 'TRACE'
	| string
	;

uri
    : scheme '://' host (':' port)? path? query? LF?
    ;

scheme
    : 'http'
    | 'https'
    | string
    ;

host
    : '/'? (hostnumber | hostname)
    ;

hostnumber
    : DIGITS'.'DIGITS'.'DIGITS'.'DIGITS
    ;

hostname
    : string ('.' string)*
    ;

port
    : DIGITS
    ;

path
    : ('/' string)*
    ;

query
    : '?' search
    ;

search
    : searchparameter ('&' searchparameter)*
    ;

searchparameter
    : string ('=' (string|DIGITS|HEX))?
    ;

string
    : STRING
    ;

GET : 'GET' ;
HEAD : 'HEAD' ;
POST : 'POST' ;
PUT : 'PUT' ;
DELETE : 'DELETE' ;
CONNECT : 'CONNECT' ;
OPTIONS : 'OPTIONS' ;
TRACE : 'TRACE' ;
HTTP : 'http' ;
HTTPS : 'https' ;
DESCRIPTION : 'DESCRIPTION' ;
LBRACE : '{' ;
RBRACE : '}' ;
DOT : '.' ;
DOLLAR : '$' ;

TITLE : '#' ~[#\r\n]+ '\r'? '\n' ;

DIGITS : [0-9]+ ;
ID : [$a-zA-Z_] [$a-zA-Z0-9_]* ;
STRING : ([a-zA-Z~0-9] | HEX) ([a-zA-Z0-9.-] | HEX)* ;
HEX : ('%' [0-9a-fA-F] [0-9a-fA-F])+ ;

LINE_COMMENT : '//' .*? ('\n'|EOF)	-> channel(HIDDEN) ;
COMMENT      : '/*' .*? '*/'    	-> channel(HIDDEN) ;

LF
    : [\r\n]+
    ;

WS : [ \t\n\r]+ -> channel(HIDDEN) ;
//WS
//    : [ \t\r\n]+ -> skip
//    ;