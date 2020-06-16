package io.chengguo.api.debugger.lang;

import com.intellij.lang.ASTFactory;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import io.chengguo.api.debugger.lang.lexer.ApiTokenTypes;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;

public class ApiASTFactory extends ASTFactory {
    @NotNull
    @Override
    public CompositeElement createComposite(@NotNull IElementType type) {
        return ApiTypes.Factory.createElement(type);
    }
}