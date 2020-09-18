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

public class ApiMultipartFieldImpl extends ApiMultipartFieldMixin implements ApiMultipartField {

  public ApiMultipartFieldImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitMultipartField(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ApiHeaderField> getHeaderFieldList() {
    return ApiPsiTreeUtil.getChildrenOfTypeAsList(this, ApiHeaderField.class);
  }

  @Override
  @Nullable
  public ApiRequestMessageGroup getRequestMessageGroup() {
    return findChildByClass(ApiRequestMessageGroup.class);
  }

}
