package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.lexer.ApiTokenTypes;
import io.chengguo.api.debugger.lang.psi.ApiProperty;
import org.jetbrains.annotations.NotNull;

public class ApiPsiImplUtil {
    public static String getKey(@NotNull ApiProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(ApiTokenTypes.KEYWORD);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static String getValue(@NotNull ApiProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(ApiTokenTypes.VALUE);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }
}
