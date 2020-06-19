package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import io.chengguo.api.debugger.lang.psi.ApiHost;
import io.chengguo.api.debugger.lang.psi.ApiRequestLine;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.lang.psi.ApiVariable;

public class ApiPsiImplUtils {

    public static PsiElement getIdentifier(ApiVariable element) {
        ASTNode node = element.getNode().findChildByType(ApiTypes.Api_IDENTIFIER);
        return node == null ? null : node.getPsi();
    }

    public static String getIdText(PsiElement element) {
        return element == null ? "" : element.getText();
    }
}