package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import io.chengguo.api.debugger.lang.psi.*;

import java.lang.reflect.Method;

public class ApiPsiImplUtils {

    public static PsiElement getIdentifier(ApiVariable element) {
        ASTNode node = element.getNode().findChildByType(ApiTypes.Api_IDENTIFIER);
        return node == null ? null : node.getPsi();
    }

    public static String getIdText(PsiElement element) {
        return element == null ? "" : element.getText();
    }

    /**
     * 获取HTTP scheme，默认http
     *
     * @param element
     * @return
     */
    public static String getScheme(ApiScheme element) {
        return element == null ? ApiTypes.Api_HTTP.toString() : element.getText();
    }

    public static String getBaseUrl(ApiRequestTarget element) {
        return getScheme(element.getScheme()) + "://" + element.getHost().getText();
    }
}