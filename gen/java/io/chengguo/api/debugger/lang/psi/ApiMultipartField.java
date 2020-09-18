/*
 * Copyright 2010-present ApiDebugger
 */
package io.chengguo.api.debugger.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApiMultipartField extends ApiMultipartFieldElement {

  @NotNull
  List<ApiHeaderField> getHeaderFieldList();

  @Nullable
  ApiRequestMessageGroup getRequestMessageGroup();

}
