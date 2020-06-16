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

public class ApiRequestImpl extends ApiElementImpl implements ApiRequest {

  public ApiRequestImpl(IElementType type) {
    super(type);
  }

  public ApiRequestImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitRequest(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ApiHeaderField> getHeaderFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ApiHeaderField.class);
  }

  @Override
  @NotNull
  public ApiRequestLine getRequestLine() {
    return PsiTreeUtil.getChildOfType(this, ApiRequestLine.class);
  }

  @Override
  @NotNull
  public ApiRequestMessageGroup getRequestMessageGroup() {
    return PsiTreeUtil.getChildOfType(this, ApiRequestMessageGroup.class);
  }

}
