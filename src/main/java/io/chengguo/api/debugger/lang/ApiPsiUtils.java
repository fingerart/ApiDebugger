package io.chengguo.api.debugger.lang;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class ApiPsiUtils {

    public static boolean isOfTypes(@NotNull final PsiElement element, @NotNull final TokenSet types) {
        final ASTNode node = element.getNode();
        return node != null && types.contains(node.getElementType());
    }
}
