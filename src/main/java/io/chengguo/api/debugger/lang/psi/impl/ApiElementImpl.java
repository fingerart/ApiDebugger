package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.impl.source.tree.CompositePsiElement;
import com.intellij.psi.tree.IElementType;
import io.chengguo.api.debugger.lang.psi.ApiElement;
import org.jetbrains.annotations.NotNull;

public class ApiElementImpl extends CompositePsiElement implements ApiElement {

    public ApiElementImpl(IElementType type) {
        super(type);
    }

    public ApiElementImpl(@NotNull ASTNode node) {
        this(node.getElementType());
    }

    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        return StringUtil.trimEnd(className, "Impl");
    }
}
