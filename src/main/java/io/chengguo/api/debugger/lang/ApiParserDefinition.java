package io.chengguo.api.debugger.lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import io.chengguo.api.debugger.lang.ApiLexer;
import io.chengguo.api.debugger.lang.Api;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;

public class ApiParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = PSIElementTypeFactory.createTokenSet(
            ApiLanguage.INSTANCE,
            ApiLexer.WS);

    public static final TokenSet COMMENTS =
            PSIElementTypeFactory.createTokenSet(
                    ApiLanguage.INSTANCE,
                    ApiLexer.COMMENT,
                    ApiLexer.LINE_COMMENT);

    public static final IFileElementType FILE = new IFileElementType(ApiLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        ApiLexer lexer = new ApiLexer(null);
        return new ANTLRLexerAdaptor(ApiLanguage.INSTANCE, lexer);
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        Api apiParser = new Api(null);
        return new ANTLRParserAdaptor(ApiLanguage.INSTANCE, apiParser) {
            @Override
            protected ParseTree parse(Parser parser, IElementType root) {
                if (root instanceof IFileElementType) {
                    return ((Api) parser).api();
                }
                return ((Api) parser).variable();
            }
        };
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new ApiPsiFile(viewProvider);
    }

    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        IElementType elType = node.getElementType();
        if (elType instanceof TokenIElementType) {
            return new ANTLRPsiNode(node);
        }
        if (!(elType instanceof RuleIElementType)) {
            return new ANTLRPsiNode(node);
        }
        RuleIElementType ruleElType = (RuleIElementType) elType;
        switch (ruleElType.getRuleIndex()) {
            case Api.RULE_api:
            case Api.RULE_info:
            case Api.RULE_http:
            case Api.RULE_method:
            case Api.RULE_uri:
            case Api.RULE_variable:
            default:
                return new ANTLRPsiNode(node);
        }
    }
}
