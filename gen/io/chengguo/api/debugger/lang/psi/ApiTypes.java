// This is a generated file. Not intended for manual editing.
package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.impl.*;

public interface ApiTypes {

  IElementType DESCRIPTION_DECLARATION = new ApiElementType("DESCRIPTION_DECLARATION");
  IElementType HEADER = new ApiElementType("HEADER");
  IElementType METHODS = new ApiElementType("METHODS");
  IElementType REQUEST_LINE = new ApiElementType("REQUEST_LINE");
  IElementType TITLE_DECLARATION = new ApiElementType("TITLE_DECLARATION");

  IElementType COMMENT = new ApiTokenType("COMMENT");
  IElementType DELETE = new ApiTokenType("DELETE");
  IElementType GET = new ApiTokenType("GET");
  IElementType IDENTIFIER = new ApiTokenType("IDENTIFIER");
  IElementType PATCH = new ApiTokenType("PATCH");
  IElementType POST = new ApiTokenType("POST");
  IElementType PUT = new ApiTokenType("PUT");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == DESCRIPTION_DECLARATION) {
        return new ApiDescriptionDeclarationImpl(node);
      }
      else if (type == HEADER) {
        return new ApiHeaderImpl(node);
      }
      else if (type == METHODS) {
        return new ApiMethodsImpl(node);
      }
      else if (type == REQUEST_LINE) {
        return new ApiRequestLineImpl(node);
      }
      else if (type == TITLE_DECLARATION) {
        return new ApiTitleDeclarationImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
