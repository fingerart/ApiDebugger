/*
 * Copyright 2010-present ApiDebugger
 */
package io.chengguo.api.debugger.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import io.chengguo.api.debugger.lang.psi.ApiPsiTreeUtil;
import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;
import io.chengguo.api.debugger.lang.psi.*;

public class ApiRequestTargetImpl extends ApiRequestTargetMixin implements ApiRequestTarget {

  public ApiRequestTargetImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitRequestTarget(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ApiHost getHost() {
    return findNotNullChildByClass(ApiHost.class);
  }

  @Override
  @Nullable
  public ApiPort getPort() {
    return findChildByClass(ApiPort.class);
  }

  @Override
  @Nullable
  public ApiQuery getQuery() {
    return findChildByClass(ApiQuery.class);
  }

  @Override
  @Nullable
  public ApiScheme getScheme() {
    return findChildByClass(ApiScheme.class);
  }

}
