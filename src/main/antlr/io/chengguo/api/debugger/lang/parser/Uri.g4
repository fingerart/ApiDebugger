/**
* scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
*/
grammar Uri;

url
   : uri EOF
   ;

uri
   : scheme '://' login? host (':' port)? ('/' path?)? query? frag? WS?
   ;

scheme
   : string
   ;

host
   : '/'? hostname
   ;

hostname
   : string ('.' string)*   #DomainNameOrIPv4Host
   | '[' v6host ']'         #IPv6Host
   ;

v6host
   : '::'? (string | DIGITS) ((':'|'::') (string | DIGITS))*
   ;

port
   : DIGITS
   ;

path
   : string ('/' string)* '/'?
   ;

user
   : string
   ;

login
   : user (':' password)? '@'
   ;

password
   : string
   ;

frag
   : '#' (string | DIGITS)
   ;

query
   : '?' search
   ;

search
   : searchparameter ('&' searchparameter)*
   ;

searchparameter
   : string ('=' (string | DIGITS | HEX))?
   ;

string
   : STRING
   ;


DIGITS
   : [0-9] +
   ;

HEX
   : ('%' [a-fA-F0-9] [a-fA-F0-9]) +
   ;


STRING
   : ([a-zA-Z~0-9] | HEX) ([a-zA-Z0-9.+-] | HEX)*
   ;


WS
   : [\r\n] +
   ;