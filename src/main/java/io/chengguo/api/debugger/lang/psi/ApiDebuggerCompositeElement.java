package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.tree.IElementType;

public interface ApiDebuggerCompositeElement extends NavigatablePsiElement {
    IElementType getTokenType();
}
