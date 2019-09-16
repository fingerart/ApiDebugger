// This is a generated file. Not intended for manual editing.
package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.impl.*;

public interface ApiTypes {

  IElementType TITLE = new ApiElementType("TITLE");

  IElementType FLAG_DES = new ApiTokenType("+++");
  IElementType FLAG_TITLE = new ApiTokenType("##");
  IElementType GET = new ApiTokenType("GET");
  IElementType LINE_COMMENT = new ApiTokenType("LINE_COMMENT");
  IElementType POST = new ApiTokenType("POST");
  IElementType RAW_STRING = new ApiTokenType("RAW_STRING");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == TITLE) {
        return new ApiTitleImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
