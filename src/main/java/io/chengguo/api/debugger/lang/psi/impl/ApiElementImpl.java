package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import io.chengguo.api.debugger.lang.psi.ApiElement;
import org.jetbrains.annotations.NotNull;

public class ApiElementImpl extends ASTWrapperPsiElement implements ApiElement {

  public ApiElementImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    final String className = getClass().getSimpleName();
    return StringUtil.trimEnd(className, "Impl");
  }
}
