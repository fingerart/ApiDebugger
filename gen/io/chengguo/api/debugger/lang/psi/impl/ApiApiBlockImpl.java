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

public class ApiApiBlockImpl extends ApiBlockMixin implements ApiApiBlock {

  public ApiApiBlockImpl(ASTNode node) {
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
  @NotNull
  public ApiDescription getDescription() {
    return findNotNullChildByClass(ApiDescription.class);
  }

  @Override
  @Nullable
  public ApiRequest getRequest() {
    return findChildByClass(ApiRequest.class);
  }

}
