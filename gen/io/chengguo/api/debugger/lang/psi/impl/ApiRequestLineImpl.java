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

public class ApiRequestLineImpl extends ApiElementImpl implements ApiRequestLine {

  public ApiRequestLineImpl(IElementType type) {
    super(type);
  }

  public ApiRequestLineImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitRequestLine(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ApiMethod getMethod() {
    return PsiTreeUtil.getChildOfType(this, ApiMethod.class);
  }

  @Override
  @Nullable
  public ApiRequestTarget getRequestTarget() {
    return PsiTreeUtil.getChildOfType(this, ApiRequestTarget.class);
  }

}
