package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.ApiHeaderFieldValueItem;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiHeaderValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ApiHeaderFieldMixin extends ApiElementImpl implements ApiHeaderField {
    public ApiHeaderFieldMixin(@NotNull ASTNode node) {
        super(node);
    }

    public String getKey() {
        return getHeaderKey().getText();
    }

    public String getValue() {
        ApiHeaderValue headerField = getHeaderValue();
        return headerField != null ? headerField.getText() : "";
    }

    @NotNull
    @Override
    public String getKey(@NotNull ApiVariableReplacer replacer) {
        return replacer.getValue(getHeaderKey());
    }

    @NotNull
    @Override
    public String getValue(ApiVariableReplacer replacer) {
        return replacer.getValue(getHeaderValue(), true);
    }

    @NotNull
    @Override
    public List<ApiHeaderFieldValueItem> getHeaderValueItems() {
        return ApiPsiImplUtil.getHeaderValueItems(this);
    }

    @Nullable
    @Override
    public String getHeaderValueItem(String itemName, ApiVariableReplacer replacer) {
        return ApiPsiImplUtil.getHeaderValueItem(this, itemName, replacer);
    }
}
