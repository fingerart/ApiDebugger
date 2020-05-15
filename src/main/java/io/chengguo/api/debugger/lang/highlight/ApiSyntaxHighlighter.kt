package io.chengguo.api.debugger.lang.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class ApiSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHighlightingLexer(): Lexer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}