parser grammar Api;

options { tokenVocab=ApiLexer; }

api
    : (NL* info? NL* http)*
    ;

info
    : InfoStart (Identifier | (NL* Identifier))* NL* InfoStart
    ;

http
    : method OWS* uri NL (headerField NL)* body
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
    : (scheme '://')? (login? host (':' port)?)? path? query?
    ;

scheme
    : Identifier
    | variable
    ;

host
    : '/'? (hostnumber | hostname | variable)
    ;

hostnumber
    : Digits'.'Digits'.'Digits'.'Digits
    ;

hostname
    : (Identifier | variable) ('.' (Identifier | variable))*
    ;

port
    : Digits
    | variable
    ;

path
    : ('/' (Identifier | variable))*
    ;

user
   : Identifier
   ;

login
   : user (':' password)? '@'
   ;

password
   : Identifier
   ;

query
    : '?' search
    ;

search
    : searchparameter ('&' searchparameter)*
    ;

searchparameter
    : Identifier ('=' (Identifier | Digits | HexUri))?
    ;

headerField
    : Identifier ':' OWS* Identifier
    ;

body
    : Identifier
    ;

variable
    : LineStrExprStart Identifier? RCURL
    ;