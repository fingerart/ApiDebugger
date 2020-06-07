package io.chengguo.api.debugger.lang.highlight

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.ImmutableSet
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import io.chengguo.api.debugger.lang.lexer.ApiLexerAdapter
import io.chengguo.api.debugger.lang.lexer.ApiTokenTypes

class ApiSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        private val emptyKeys = ImmutableSet.of<TextAttributesKey>()
        val METHOD_TYPE =
            TextAttributesKey.createTextAttributesKey("API_DEBUGGER_METHOD_TYPE", DefaultLanguageHighlighterColors.KEYWORD)
        val HEADER_FIELD_NAME = TextAttributesKey.createTextAttributesKey(
            "API_DEBUGGER_HEADER_FIELD_NAME",
            DefaultLanguageHighlighterColors.STATIC_FIELD
        )
        val HEADER_FIELD_VALUE =
            TextAttributesKey.createTextAttributesKey("API_DEBUGGER_HEADER_FIELD_VALUE", DefaultLanguageHighlighterColors.IDENTIFIER)
        val API_BLOCK_SEPARATOR = TextAttributesKey.createTextAttributesKey(
            "API_DEBUGGER_API_BLOCK_SEPARATOR",
            DefaultLanguageHighlighterColors.LINE_COMMENT
        )
        val QUERY_PARAMETER_KEY = TextAttributesKey.createTextAttributesKey("API_DEBUGGER_QUERY_PARAMETER_KEY")
        val QUERY_PARAMETER_VALUE = TextAttributesKey.createTextAttributesKey("API_DEBUGGER_QUERY_PARAMETER_VALUE")
        val REQUEST_BODY =
            TextAttributesKey.createTextAttributesKey("API_DEBUGGER_REQUEST_BODY", EditorColors.INJECTED_LANGUAGE_FRAGMENT)
        val VARIABLE_NAME =
            TextAttributesKey.createTextAttributesKey("API_DEBUGGER_VARIABLE_NAME", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
        val LINE_COMMENT =
            TextAttributesKey.createTextAttributesKey("API_DEBUGGER_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
            "API_DEBUGGER_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT
        )
        private val attributesToTokenMap: ImmutableMultimap<TextAttributesKey, IElementType> =
            ImmutableMultimap.builder<TextAttributesKey, IElementType>()
                .putAll(METHOD_TYPE, *ApiTokenTypes.METHOD_TYPE.types)
                .putAll(HEADER_FIELD_NAME, *ApiTokenTypes.HEADER_FIELD_NAME.types)
                .putAll(HEADER_FIELD_VALUE, *ApiTokenTypes.HEADER_FIELD_VALUE.types)
                .putAll(API_BLOCK_SEPARATOR, *ApiTokenTypes.API_BLOCK_SEPARATOR.types)
                .putAll(QUERY_PARAMETER_KEY, *ApiTokenTypes.QUERY_PARAMETER_KEY.types)
                .putAll(QUERY_PARAMETER_VALUE, *ApiTokenTypes.QUERY_PARAMETER_VALUE.types)
                .putAll(REQUEST_BODY, *ApiTokenTypes.REQUEST_BODY.types)
                .putAll(VARIABLE_NAME, *ApiTokenTypes.VARIABLE_NAME.types)
                .putAll(LINE_COMMENT, *ApiTokenTypes.COMMENTS.types)
                .putAll(BLOCK_COMMENT, *ApiTokenTypes.BLOCK_COMMENT.types)
                .build()

        private val tokenToAttributesMap = attributesToTokenMap.inverse().asMap()
    }

    override fun getHighlightingLexer(): Lexer {
        return ApiLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return tokenToAttributesMap.getOrDefault(tokenType, emptyKeys).toTypedArray()
    }
}