package io.chengguo.api.debugger.lang;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import io.chengguo.api.debugger.lang.lexer.ApiLexerAdapter;
import io.chengguo.api.debugger.lang.psi.ApiNamedElement;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
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
        return psiElement instanceof ApiNamedElement;
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
//        if (element instanceof ApiVariable) {
//            return "Variable";
//        }
        return "";
    }

    @Nls
    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
//        if (element instanceof ApiVariable) {
//            return Strings.nullToEmpty(((ApiVariable) element).getName());
//        }
        return "";
    }

    @Nls
    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
//        if (element instanceof ApiVariable) {
//            return ((ApiVariable) element).getName() + " ➞ " + "value";
//        }
        return element.getText();
    }
}
