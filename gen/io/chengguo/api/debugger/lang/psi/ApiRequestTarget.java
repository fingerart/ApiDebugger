/*
 * Copyright 2010-present ApiDebugger
 */
package io.chengguo.api.debugger.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApiRequestTarget extends ApiElement {

  @NotNull
  ApiHost getHost();

  @NotNull
  ApiPathAbsolute getPathAbsolute();

  @Nullable
  ApiPort getPort();

  @NotNull
  ApiScheme getScheme();

}
