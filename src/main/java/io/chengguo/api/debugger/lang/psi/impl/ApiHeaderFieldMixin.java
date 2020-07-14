package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiHeaderFieldVal;
import org.jetbrains.annotations.NotNull;

public abstract class ApiHeaderFieldMixin extends ApiElementImpl implements ApiHeaderField {
    public ApiHeaderFieldMixin(@NotNull ASTNode node) {
        super(node);
    }

    public String getName() {
        return getHeaderFieldKey().getText();
    }

    public String getValue() {
        //TODO 解析变量
        ApiHeaderFieldVal headerField = getHeaderFieldVal();
        return headerField != null ? headerField.getText() : "";
    }
}
