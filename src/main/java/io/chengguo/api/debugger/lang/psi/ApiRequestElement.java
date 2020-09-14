package io.chengguo.api.debugger.lang.psi;

import com.intellij.openapi.util.Pair;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ApiRequestElement extends ApiElement {

    @Nullable
    String getMimeType();

    @Nullable
    public ApiHeaderField getFirstHeader();

    @Nullable
    public ApiHeaderField getHeaderField(String key);

    @NotNull
    public List<Pair<String, String>> getHeaders(ApiVariableReplacer replacer);
}
