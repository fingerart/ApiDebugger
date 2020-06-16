package io.chengguo.api.debugger.lang;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import org.jetbrains.annotations.NotNull;

public class ApiPsiUtils {
    public static final ApiApiBlock[] EMPTY = new ApiApiBlock[]{};

    public static boolean isOfTypes(@NotNull PsiElement element, @NotNull final TokenSet types) {
        ASTNode node = element.getNode();
        return node != null && types.contains(node.getElementType());
    }

    public static ApiApiBlock getFirstApiBlock(PsiFile psiFile) {
        ApiApiBlock[] apiBlocks = getApiBlocks(psiFile);
        return apiBlocks.length == 0 ? null : apiBlocks[0];
    }

    public static ApiApiBlock[] getApiBlocks(PsiFile psiFile) {
        return PsiTreeUtil.findChildrenOfType(psiFile, ApiApiBlock.class).toArray(EMPTY);
    }
}
