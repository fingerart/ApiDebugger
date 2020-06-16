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

public class ApiApiBlockImpl extends ApiElementImpl implements ApiApiBlock {

  public ApiApiBlockImpl(IElementType type) {
    super(type);
  }

  public ApiApiBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitApiBlock(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApiDescription getDescription() {
    return PsiTreeUtil.getChildOfType(this, ApiDescription.class);
  }

  @Override
  @Nullable
  public ApiRequest getRequest() {
    return PsiTreeUtil.getChildOfType(this, ApiRequest.class);
  }

}
