/*
 * Copyright 2010-present ApiDebugger
 */
package io.chengguo.api.debugger.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class ApiVisitor extends PsiElementVisitor {

  public void visitApiBlock(@NotNull ApiApiBlock o) {
    visitBlockElment(o);
  }

  public void visitDescription(@NotNull ApiDescription o) {
    visitElement(o);
  }

  public void visitDescriptionContent(@NotNull ApiDescriptionContent o) {
    visitElement(o);
  }

  public void visitDescriptionItem(@NotNull ApiDescriptionItem o) {
    visitKeyValueElement(o);
  }

  public void visitDescriptionTitle(@NotNull ApiDescriptionTitle o) {
    visitElement(o);
  }

  public void visitHeaderField(@NotNull ApiHeaderField o) {
    visitKeyValueElement(o);
  }

  public void visitHeaderFieldKey(@NotNull ApiHeaderFieldKey o) {
    visitElement(o);
  }

  public void visitHeaderFieldVal(@NotNull ApiHeaderFieldVal o) {
    visitElement(o);
  }

  public void visitHost(@NotNull ApiHost o) {
    visitElement(o);
  }

  public void visitMethod(@NotNull ApiMethod o) {
    visitElement(o);
  }

  public void visitPort(@NotNull ApiPort o) {
    visitElement(o);
  }

  public void visitQuery(@NotNull ApiQuery o) {
    visitElement(o);
  }

  public void visitQueryParameter(@NotNull ApiQueryParameter o) {
    visitElement(o);
  }

  public void visitQueryParameterKey(@NotNull ApiQueryParameterKey o) {
    visitElement(o);
  }

  public void visitQueryParameterValue(@NotNull ApiQueryParameterValue o) {
    visitElement(o);
  }

  public void visitRequest(@NotNull ApiRequest o) {
    visitElement(o);
  }

  public void visitRequestLine(@NotNull ApiRequestLine o) {
    visitElement(o);
  }

  public void visitRequestMessageGroup(@NotNull ApiRequestMessageGroup o) {
    visitElement(o);
  }

  public void visitRequestTarget(@NotNull ApiRequestTarget o) {
    visitRequestTargetElement(o);
  }

  public void visitScheme(@NotNull ApiScheme o) {
    visitElement(o);
  }

  public void visitSegmentBlock(@NotNull ApiSegmentBlock o) {
    visitElement(o);
  }

  public void visitVariable(@NotNull ApiVariable o) {
    visitNamedElement(o);
  }

  public void visitBlockElment(@NotNull ApiBlockElment o) {
    visitElement(o);
  }

  public void visitKeyValueElement(@NotNull ApiKeyValueElement o) {
    visitElement(o);
  }

  public void visitNamedElement(@NotNull ApiNamedElement o) {
    visitElement(o);
  }

  public void visitRequestTargetElement(@NotNull ApiRequestTargetElement o) {
    visitElement(o);
  }

  public void visitElement(@NotNull ApiElement o) {
    super.visitElement(o);
  }

}
