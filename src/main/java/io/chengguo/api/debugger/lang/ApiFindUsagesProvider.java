package io.chengguo.api.debugger.lang;

import com.google.common.base.Strings;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import io.chengguo.api.debugger.lang.lexer.ApiLexerAdapter;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.lang.psi.ApiVariableName;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 查找使用（⌥F7）
 */
public class ApiFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(
                new ApiLexerAdapter(),
                TokenSet.create(ApiTypes.Api_IDENTIFIER),
                TokenSet.create(ApiTypes.Api_MULTILINE_COMMENT, ApiTypes.Api_LINE_COMMENT),
                TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof ApiVariableName;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @Nls
    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof ApiVariableName) {
            return "Variable";
        }
        return "";
    }

    @Nls
    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof ApiVariableName) {
            return Strings.nullToEmpty(((ApiVariableName) element).getName());
        }
        return "";
    }

    @Nls
    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        return element.getText();
    }
}
