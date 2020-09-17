package io.chengguo.api.debugger.lang.psi;

import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import org.apache.http.entity.ContentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ApiMultipartFieldElement extends ApiElement {
    @Nullable
    ApiHeaderField getContentDispositionField();

    @Nullable
    ContentType getContentType(ApiVariableReplacer replacer);

    @NotNull
    List<ApiRequestMessageElement> getRequestMessages();
}
