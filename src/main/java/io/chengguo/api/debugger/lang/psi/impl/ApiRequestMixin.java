package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.psi.ApiBodyMessageElement;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiPsiTreeUtil;
import io.chengguo.api.debugger.lang.psi.ApiRequest;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static io.chengguo.api.debugger.constants.HeaderFields.CONTENT_TYPE;

public abstract class ApiRequestMixin extends ApiElementImpl implements ApiRequest {
    public ApiRequestMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public String getMimeType() {
        return ApiPsiImplUtil.getMimeType(this);
    }

    @Nullable
    @Override
    public ApiHeaderField getFirstHeader() {
        return ContainerUtil.getFirstItem(getHeaderFieldList());
    }

    @Nullable
    @Override
    public ApiHeaderField getHeaderField(String key) {
        return ApiPsiImplUtil.getHeaderField(getHeaderFieldList(), key);
    }

    @NotNull
    @Override
    public List<Pair<String, String>> getHeaders(ApiVariableReplacer replacer) {
        return ApiPsiImplUtil.getHeaders(this, replacer);
    }

    @Nullable
    @Override
    public ApiBodyMessageElement getBody() {
        return ApiPsiTreeUtil.getChildOfType(this, ApiBodyMessageElement.class);
    }
}
