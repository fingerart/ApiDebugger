parser grammar ApiParser;

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
    : '---' TitleContent? NL*
    ;

description
    : Description NL*
    ;

attribute
    : '-' key ':' value NL*
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