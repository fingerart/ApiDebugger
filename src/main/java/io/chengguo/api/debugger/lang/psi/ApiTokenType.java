package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.tree.IElementType;
import io.chengguo.api.debugger.lang.ApiLanguage;
import org.jetbrains.annotations.NotNull;

public class ApiTokenType extends IElementType {

    private final String mDebugName;

    public ApiTokenType(@NotNull String debugName) {
        super(debugName, ApiLanguage.INSTANCE);
        mDebugName = debugName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ApiTokenType && mDebugName.equals(((ApiTokenType) other).mDebugName))
                || (other instanceof String && mDebugName.equals(other))
                || toString().equals(other.toString());
    }
}