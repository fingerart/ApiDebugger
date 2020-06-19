package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.Nullable;

public interface ApiNamedElement extends ApiElement, PsiNameIdentifierOwner {
    @Nullable
    PsiElement getIdentifier();
}
