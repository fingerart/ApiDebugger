package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class ApiRequestMixin extends ApiElementImpl implements ApiRequest {
    public ApiRequestMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public String getMimeType() {
        ApiHeaderField contentTypeField = getHeaderField("Content-Type");
        if (contentTypeField != null) {
            String mimeType = StringUtil.toLowerCase(contentTypeField.getValue());
            if (isValidMimeType(mimeType)) {
                return mimeType;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public ApiHeaderField getFirstHeader() {
        return ContainerUtil.getFirstItem(getHeaderFieldList());
    }

    @Nullable
    @Override
    public ApiHeaderField getHeaderField(String key) {
        for (ApiHeaderField headerField : getHeaderFieldList()) {
            if (StringUtil.equalsIgnoreCase(key, headerField.getKey())) {
                return headerField;
            }
        }
        return null;
    }

    @NotNull
    @Override
    public List<Pair<String, String>> getHeaders(ApiVariableReplacer replacer) {
        List<Pair<String, String>> result = new ArrayList<>();
        for (ApiHeaderField headerField : getHeaderFieldList()) {
            result.add(Pair.create(headerField.getKey(replacer), headerField.getValue(replacer)));
        }
        return result;
    }

    private static boolean isValidMimeType(@Nullable String value) {
        return StringUtil.isNotEmpty(value) && !StringUtil.containsAnyChar(value, "\";,");
    }
}
