package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import io.chengguo.api.debugger.lang.psi.ApiVariableName;
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

    @NotNull
    public static String getValue(@NotNull ApiVariableName element) {
        return "我是变量的值";
    }
}
