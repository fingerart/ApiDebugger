package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiDescription;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ApiBlockMixin extends ApiElementImpl implements ApiApiBlock {
    public ApiBlockMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public ApiDescription getDescriptionByKey(String key) {
        return ApiPsiImplUtil.getDescriptionByKey(getDescriptionList(), key);
    }

    @Override
    public ItemPresentation getPresentation() {
        return ApiPsiElementPresentationFactory.getItemPresentation(this);
    }
}
