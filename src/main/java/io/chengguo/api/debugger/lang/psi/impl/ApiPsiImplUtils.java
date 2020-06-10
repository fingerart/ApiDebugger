package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import io.chengguo.api.debugger.lang.psi.ApiVariableName;

public class ApiPsiImplUtils {
    public static String getName(ApiVariable element) {
        ASTNode keyNode = element.getNode().findChildByType(ApiTypes.Api_HEADER_FIELD_NAME);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static String setName(ApiVariable element) {
        ASTNode keyNode = element.getNode().findChildByType(ApiTypes.Api_HEADER_FIELD_VALUE);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }
    public static String getName(ApiVariableName element) {
        ASTNode keyNode = element.getNode().findChildByType(ApiTypes.Api_HEADER_FIELD_NAME);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static String getValue(ApiVariableName element) {
        ASTNode keyNode = element.getNode().findChildByType(ApiTypes.Api_HEADER_FIELD_VALUE);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }


}
