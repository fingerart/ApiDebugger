// This is a generated file. Not intended for manual editing.
package io.chengguo.api.debugger.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.chengguo.api.debugger.lang.psi.*;

public class ApiApiImpl extends ASTWrapperPsiElement implements ApiApi {

  public ApiApiImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ApiVisitor visitor) {
    visitor.visitApi(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ApiVisitor) accept((ApiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ApiInfo getInfo() {
    return findNotNullChildByClass(ApiInfo.class);
  }

}
