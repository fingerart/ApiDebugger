package io.chengguo.api.debugger.lang.psi;

import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ApiHeaderFieldElement extends ApiElement {

    @NotNull
    List<ApiHeaderFieldValueItem> getHeaderValueItems();

    @Nullable
    String getHeaderValueItem(String itemName, ApiVariableReplacer replacer);
}
