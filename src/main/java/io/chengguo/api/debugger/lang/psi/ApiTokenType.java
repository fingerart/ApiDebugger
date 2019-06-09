package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.tree.IElementType;
import io.chengguo.api.debugger.lang.ApiLanguage;
import org.jetbrains.annotations.NotNull;

public class ApiTokenType extends IElementType {
    public ApiTokenType(@NotNull String debugName) {
        super(debugName, ApiLanguage.INSTANCE);
    }
}
