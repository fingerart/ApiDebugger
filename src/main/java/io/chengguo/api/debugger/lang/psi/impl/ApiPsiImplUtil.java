package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiTypes;

public class ApiPsiImplUtil {
    public static String getName(ApiHeaderField element) {
        ASTNode keyNode = element.getNode().findChildByType(ApiTypes.Api_HEADER_FIELD_NAME);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static String getValue(ApiHeaderField element) {
        ASTNode keyNode = element.getNode().findChildByType(ApiTypes.Api_HEADER_FIELD_VALUE);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }


}
