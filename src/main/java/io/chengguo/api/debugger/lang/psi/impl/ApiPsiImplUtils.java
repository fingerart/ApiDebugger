package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.psi.*;
import org.jetbrains.annotations.NotNull;

public class ApiPsiImplUtils {

    @NotNull
    public static String getName(@NotNull ApiVariableName element) {
        ASTNode keyNode = element.getNode().findChildByType(ApiTypes.Api_IDENTIFIER);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static PsiElement setName(ApiVariableName element,@NotNull String newName) {
        if (element != null) {
            ApiVariableName newVariableName = new ApiElementGenerator(element.getProject()).createVariableName(newName);
            element.replace(newVariableName);
        }
        return element;
    }

    @NotNull
    public static String getValue(@NotNull ApiVariableName element) {
        return "我是变量的值";
    }
}