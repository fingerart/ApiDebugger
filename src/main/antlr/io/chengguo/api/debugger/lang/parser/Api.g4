parser grammar Api;

options { tokenVocab=ApiLexer; }

api
    : info http
    ;

info
    : StringLiteral
    ;

http
    : method SP uri
    ;

variable
    : LineStrExprStart string RCURL
    ;

method
    : 'GET'
	| 'HEAD'
	| 'POST'
	| 'PUT'
	| 'DELETE'
	| 'CONNECT'
	| 'OPTIONS'
	| 'TRACE'
	;

uri
    : (scheme '://')? (host (':' port)?)? path? query?
    ;

scheme
    : string
    | variable
    ;

host
    : '/'? (hostnumber | hostname | variable)
    ;

hostnumber
    : Digits'.'Digits'.'Digits'.'Digits
    ;

hostname
    : (string | variable) ('.' (string | variable))*
    ;

port
    : Digits
    | variable
    ;

path
    : ('/' (string | variable))*
    ;

query
    : '?' search
    ;

search
    : searchparameter ('&' searchparameter)*
    ;

searchparameter
    : string ('=' (string | Digits | HexUri))?
    ;

string
    : Identifier
    | variable
    ;
