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

public class ApiHeaderFieldImpl extends ApiElementImpl implements ApiHeaderField {

  public ApiHeaderFieldImpl(ASTNode node) {
    super(node);
  }

  public <R> R accept(@NotNull ApiVisitor<R> visitor) {
    return visitor.visitHeaderField(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ApiHeaderFieldKey getHeaderFieldKey() {
    return findNotNullChildByClass(ApiHeaderFieldKey.class);
  }

  @Override
  @Nullable
  public ApiHeaderFieldVal getHeaderFieldVal() {
    return findChildByClass(ApiHeaderFieldVal.class);
  }

}
