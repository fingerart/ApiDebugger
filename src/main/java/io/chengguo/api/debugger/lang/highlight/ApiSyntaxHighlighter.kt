package io.chengguo.api.debugger.lang.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import io.chengguo.api.debugger.lang.ApiLanguage
import io.chengguo.api.debugger.lang.ApiLexer
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.TokenIElementType

class ApiSyntaxHighlighter : SyntaxHighlighterBase() {

    private val emptyKeys = arrayOf<TextAttributesKey>()
    private val id = TextAttributesKey.createTextAttributesKey("SAMPLE_ID", DefaultLanguageHighlighterColors.IDENTIFIER)

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        if (tokenType !is TokenIElementType) {
            return emptyKeys
        }
        val attrKey = when (tokenType.antlrTokenType) {
            ApiLexer.Identifier -> id
            else -> return emptyKeys
        }
        return arrayOf(attrKey)
    }

    override fun getHighlightingLexer(): Lexer {
        return ANTLRLexerAdaptor(ApiLanguage.INSTANCE, ApiLexer(null))
    }
}