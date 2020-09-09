package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        List<ApiHeaderField> apiHeaderFields = getHeaderFieldList();
        return !apiHeaderFields.isEmpty() ? apiHeaderFields.get(0) : null;
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

    private static boolean isValidMimeType(@Nullable String value) {
        return StringUtil.isNotEmpty(value) && !StringUtil.containsAnyChar(value, "\";,");
    }
}
