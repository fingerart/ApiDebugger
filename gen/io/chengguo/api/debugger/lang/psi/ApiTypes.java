// This is a generated file. Not intended for manual editing.
package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.impl.*;

public interface ApiTypes {

  IElementType API = new ApiElementType("API");
  IElementType INFO = new ApiElementType("INFO");
  IElementType TITLE = new ApiElementType("TITLE");

  IElementType FLAG_TITLE = new ApiTokenType("##");
  IElementType LINE_COMMENT = new ApiTokenType("LINE_COMMENT");
  IElementType RAW_STRING = new ApiTokenType("raw_string");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == API) {
        return new ApiApiImpl(node);
      }
      else if (type == INFO) {
        return new ApiInfoImpl(node);
      }
      else if (type == TITLE) {
        return new ApiTitleImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
