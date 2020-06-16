/*
 * Copyright 2010-present ApiDebugger
 */
package io.chengguo.api.debugger.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;
import io.chengguo.api.debugger.lang.psi.*;
import com.intellij.psi.tree.IElementType;

public class ApiHeaderFieldKeyImpl extends ApiElementImpl implements ApiHeaderFieldKey {

  public ApiHeaderFieldKeyImpl(IElementType type) {
    super(type);
  }

  public ApiHeaderFieldKeyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitHeaderFieldKey(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ApiVariable> getVariableList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApiVariable.class);
  }

}
