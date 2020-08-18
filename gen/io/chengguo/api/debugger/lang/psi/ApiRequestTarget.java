/*
 * Copyright 2010-present ApiDebugger
 */
package io.chengguo.api.debugger.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApiRequestTarget extends ApiRequestTargetElement {

  @NotNull
  ApiHost getHost();

  @Nullable
  ApiPort getPort();

  @Nullable
  ApiQuery getQuery();

  @Nullable
  ApiScheme getScheme();

}
