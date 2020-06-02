package io.chengguo.api.debugger.lang.highlight

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.ImmutableSet
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import io.chengguo.api.debugger.lang.lexer.ApiLexerAdapter
import io.chengguo.api.debugger.lang.lexer.ApiTokenTypes

class ApiSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        private val emptyKeys = ImmutableSet.of<TextAttributesKey>()
        val SEPARATOR =
            TextAttributesKey.createTextAttributesKey("SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val KEY = TextAttributesKey.createTextAttributesKey("ID", DefaultLanguageHighlighterColors.KEYWORD)
        val STRING = TextAttributesKey.createTextAttributesKey("STRING", DefaultLanguageHighlighterColors.STRING)
        val BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("STRING", HighlighterColors.BAD_CHARACTER)
        val LINE_COMMENT =
            TextAttributesKey.createTextAttributesKey("LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
            "BLOCK_COMMENT",
            DefaultLanguageHighlighterColors.BLOCK_COMMENT
        )
        private val attributesToTokenMap: ImmutableMultimap<TextAttributesKey, IElementType> =
            ImmutableMultimap.builder<TextAttributesKey, IElementType>()
                .putAll(KEY, *ApiTokenTypes.KEYWORD.types)
                .putAll(STRING, *ApiTokenTypes.STRING.types)
                .putAll(SEPARATOR, *ApiTokenTypes.SEPARATOR.types)
                .putAll(LINE_COMMENT, *ApiTokenTypes.COMMENTS.types)
                .putAll(BLOCK_COMMENT, *ApiTokenTypes.BLOCK_COMMENT.types)
                .putAll(BAD_CHARACTER, TokenType.BAD_CHARACTER)
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