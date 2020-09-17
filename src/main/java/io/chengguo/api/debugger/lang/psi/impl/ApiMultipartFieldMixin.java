package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.jediterm.terminal.emulator.charset.CharacterSets;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiMultipartField;
import io.chengguo.api.debugger.lang.psi.ApiRequestMessageElement;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import org.apache.http.entity.ContentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static io.chengguo.api.debugger.constants.HeaderFields.ACCEPT_CHARSET;

public abstract class ApiMultipartFieldMixin extends ApiElementImpl implements ApiMultipartField {
    public ApiMultipartFieldMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public ApiHeaderField getContentDispositionField() {
        return ApiPsiImplUtil.getContentDispositionField(this);
    }

    @Nullable
    @Override
    public ContentType getContentType(ApiVariableReplacer replacer) {
        ApiHeaderField contentTypeField = ApiPsiImplUtil.getContentTypeField(this);
        ContentType contentType = ContentType.parse("*/*");
        if (contentTypeField != null) {
            try {
                String value = contentTypeField.getValue(replacer);
                contentType = ContentType.parse(StringUtil.notNullize(StringUtil.toLowerCase(value), "*/*"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (contentType.getCharset() == null) {
            ApiHeaderField charset = ApiPsiImplUtil.getHeaderField(getHeaderFieldList(), ACCEPT_CHARSET);
            contentType = contentType.withCharset(StringUtil.notNullize((charset != null) ? charset.getValue(replacer) : null, StandardCharsets.UTF_8.name()));
        }
        return contentType;
    }

    @NotNull
    @Override
    public List<ApiRequestMessageElement> getRequestMessages() {
        return ApiPsiImplUtil.getRequestMessages(this);
    }
}
