// This is a generated file. Not intended for manual editing.
package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.impl.*;

public interface ApiTypes {

  IElementType API_BLOCK = new ApiElementType("API_BLOCK");

  IElementType FLAG_TITLE = new ApiTokenType("##");
  IElementType LINE_COMMENT = new ApiTokenType("LINE_COMMENT");
  IElementType TITLE_RAW_STRING = new ApiTokenType("TITLE_RAW_STRING");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == API_BLOCK) {
        return new ApiApiBlockImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
