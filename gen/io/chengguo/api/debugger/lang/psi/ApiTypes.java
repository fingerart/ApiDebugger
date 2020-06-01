/*
 * Copyright 2010-present ApiDebugger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.impl.*;

public interface ApiTypes {

  IElementType Api_PROPERTY = new ApiElementType("Api_PROPERTY");

  IElementType Api_COMMENT = new ApiTokenType("COMMENT");
  IElementType Api_CRLF = new ApiTokenType("CRLF");
  IElementType Api_KEY = new ApiTokenType("KEY");
  IElementType Api_SEPARATOR = new ApiTokenType("SEPARATOR");
  IElementType Api_VALUE = new ApiTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == Api_PROPERTY) {
        return new ApiPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
