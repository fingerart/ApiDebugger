package io.chengguo.api.debugger.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import io.chengguo.api.debugger.lang.ApiLanguage;
import io.chengguo.api.debugger.lang.ApiPsiFile;
import io.chengguo.api.debugger.lang.lexer.ApiLexerAdapter;
import io.chengguo.api.debugger.lang.lexer.ApiTokenTypes;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;

public class ApiParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(ApiLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new ApiLexerAdapter();
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new ApiParser();
    }

    /**
     * "Tokens of those types are automatically skipped by PsiBuilder."
     */
    @NotNull
    public TokenSet getWhitespaceTokens() {
        return ApiTokenTypes.WHITE_SPACE;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return ApiTokenTypes.COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    /**
     * What is the IFileElementType of the root parse tree node? It
     * is called from {@link #createFile(FileViewProvider)} at least.
     */
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new ApiPsiFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return ApiTypes.Factory.createElement(node);
    }
}
