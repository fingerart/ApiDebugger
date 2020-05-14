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
import io.chengguo.api.debugger.lang.parser.ApiLexer;
import io.chengguo.api.debugger.lang.parser.ApiParser;
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
        ApiParser apiParser = new ApiParser(null);
        return new ANTLRParserAdaptor(ApiLanguage.INSTANCE, apiParser) {
            @Override
            protected ParseTree parse(Parser parser, IElementType root) {
                if (root instanceof IFileElementType) {
                    return ((ApiParser) parser).api();
                }
                return ((ApiParser) parser).variable();
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
            case ApiParser.RULE_api:
            case ApiParser.RULE_info:
            case ApiParser.RULE_http:
            case ApiParser.RULE_method:
            case ApiParser.RULE_uri:
            case ApiParser.RULE_variable:
            default:
                return new ANTLRPsiNode(node);
        }
    }
}
