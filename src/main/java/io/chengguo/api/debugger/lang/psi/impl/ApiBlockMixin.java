package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public abstract class ApiBlockMixin extends ApiElementImpl implements ApiApiBlock {
    public ApiBlockMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    public ApiHeaderField getHeaderField(String key) {
        ApiRequest apiRequest = getRequest();
        List<ApiHeaderField> headerFields = apiRequest != null ? apiRequest.getHeaderFieldList() : Collections.emptyList();
        for (ApiHeaderField headerField : headerFields) {
            if (StringUtil.equalsIgnoreCase(key, headerField.getName())) {
                return headerField;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getMimeType() {
        ApiHeaderField contentTypeField = getHeaderField("Content-Type");
        if (contentTypeField != null) {
            ApiHeaderFieldVal fieldVal = contentTypeField.getHeaderFieldVal();
             PsiElement contentType = (fieldVal != null) ? ApiPsiUtils.getNextSiblingByType(fieldVal.getFirstChild(), ApiTypes.Api_HEADER_FIELD_VALUE, false) : null;
             String mimeType = (contentType != null) ? StringUtil.toLowerCase(contentType.getText()) : null;
            if (isValidMimeType(mimeType)) {
                return mimeType;
            }
        }
        return null;
    }

    private static boolean isValidMimeType(@Nullable String value) {
        return StringUtil.isNotEmpty(value) && !StringUtil.containsAnyChar(value, "\";,");
    }
}
