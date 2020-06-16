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

public class ApiHeaderFieldImpl extends ApiElementImpl implements ApiHeaderField {

  public ApiHeaderFieldImpl(IElementType type) {
    super(type);
  }

  public ApiHeaderFieldImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitHeaderField(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ApiHeaderFieldKey getHeaderFieldKey() {
    return PsiTreeUtil.getChildOfType(this, ApiHeaderFieldKey.class);
  }

  @Override
  @Nullable
  public ApiHeaderFieldVal getHeaderFieldVal() {
    return PsiTreeUtil.getChildOfType(this, ApiHeaderFieldVal.class);
  }

}
