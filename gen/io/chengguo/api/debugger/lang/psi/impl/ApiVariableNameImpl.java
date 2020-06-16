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

public class ApiVariableNameImpl extends ApiVariableNameMixin implements ApiVariableName {

  public ApiVariableNameImpl(IElementType type) {
    super(type);
  }

  public ApiVariableNameImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitVariableName(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public String getName() {
    return ApiPsiImplUtils.getName(this);
  }

  @Override
  public PsiElement setName(@NotNull String newName) {
    return ApiPsiImplUtils.setName(this, newName);
  }

  @Override
  @NotNull
  public String getValue() {
    return ApiPsiImplUtils.getValue(this);
  }

}
