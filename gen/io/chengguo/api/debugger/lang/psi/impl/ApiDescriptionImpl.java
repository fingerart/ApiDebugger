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

public class ApiDescriptionImpl extends ApiElementImpl implements ApiDescription {

  public ApiDescriptionImpl(IElementType type) {
    super(type);
  }

  public ApiDescriptionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitDescription(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ApiDescriptionContent getDescriptionContent() {
    return PsiTreeUtil.getChildOfType(this, ApiDescriptionContent.class);
  }

  @Override
  @NotNull
  public ApiDescriptionTitle getDescriptionTitle() {
    return PsiTreeUtil.getChildOfType(this, ApiDescriptionTitle.class);
  }

}
