/*
 * Copyright 2010-present ApiDebugger
 */
package io.chengguo.api.debugger.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class ApiVisitor<R> extends PsiElementVisitor {

  public R visitApiBlock(@NotNull ApiApiBlock o) {
    return visitElement(o);
  }

  public R visitDescription(@NotNull ApiDescription o) {
    return visitElement(o);
  }

  public R visitDescriptionContent(@NotNull ApiDescriptionContent o) {
    return visitElement(o);
  }

  public R visitDescriptionTitle(@NotNull ApiDescriptionTitle o) {
    return visitElement(o);
  }

  public R visitHeaderField(@NotNull ApiHeaderField o) {
    return visitElement(o);
  }

  public R visitHeaderFieldKey(@NotNull ApiHeaderFieldKey o) {
    return visitElement(o);
  }

  public R visitHeaderFieldVal(@NotNull ApiHeaderFieldVal o) {
    return visitElement(o);
  }

  public R visitHost(@NotNull ApiHost o) {
    return visitElement(o);
  }

  public R visitPathAbsolute(@NotNull ApiPathAbsolute o) {
    return visitElement(o);
  }

  public R visitPort(@NotNull ApiPort o) {
    return visitElement(o);
  }

  public R visitQuery(@NotNull ApiQuery o) {
    return visitElement(o);
  }

  public R visitQueryParameter(@NotNull ApiQueryParameter o) {
    return visitElement(o);
  }

  public R visitQueryParameterKey(@NotNull ApiQueryParameterKey o) {
    return visitElement(o);
  }

  public R visitQueryParameterValue(@NotNull ApiQueryParameterValue o) {
    return visitElement(o);
  }

  public R visitRequest(@NotNull ApiRequest o) {
    return visitElement(o);
  }

  public R visitRequestLine(@NotNull ApiRequestLine o) {
    return visitElement(o);
  }

  public R visitRequestMessageGroup(@NotNull ApiRequestMessageGroup o) {
    return visitElement(o);
  }

  public R visitRequestTarget(@NotNull ApiRequestTarget o) {
    return visitElement(o);
  }

  public R visitScheme(@NotNull ApiScheme o) {
    return visitElement(o);
  }

  public R visitVariable(@NotNull ApiVariable o) {
    return visitElement(o);
  }

  public R visitVariableName(@NotNull ApiVariableName o) {
    return visitElement(o);
  }

  public R visitElement(@NotNull ApiElement o) {
    super.visitElement(o);
    return null;
  }

}
