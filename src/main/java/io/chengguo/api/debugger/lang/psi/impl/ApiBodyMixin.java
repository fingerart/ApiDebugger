package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.ElementManipulators;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import io.chengguo.api.debugger.lang.psi.ApiRequestMessageElement;
import io.chengguo.api.debugger.lang.psi.ApiRequestMessageGroup;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class ApiBodyMixin extends ApiElementImpl implements PsiLanguageInjectionHost, ApiRequestMessageGroup {

    public ApiBodyMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public boolean isValidHost() {
        return true;
    }

    @Override
    public PsiLanguageInjectionHost updateText(@NotNull String text) {
        return ElementManipulators.handleContentChange(this, text);
    }

    @NotNull
    @Override
    public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
        return LiteralTextEscaper.createSimple(this);
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }

    @NotNull
    @Override
    public List<ApiRequestMessageElement> getRequestMessageList() {
        return ApiPsiImplUtil.getRequestMessages(this);
    }
}
