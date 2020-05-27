parser grammar Api;

options { tokenVocab=ApiLexer; }

file
    : api*
    ;

api
    : info request
    ;

info
    : title description? attribute*
    ;

title
    : '---' LineText? NL*
    ;

description
    : FlagDesOpen Text? FlagDesClose NL*
    ;

attribute
    : '-' key ':' value
    ;

key
    : Method
    ;

value
    : Method
    ;

request
    : requestLine
    ;

requestLine
    : Method
    ;