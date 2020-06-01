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
package io.chengguo.api.debugger.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;
import static io.chengguo.api.debugger.lang.parser.ApiParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ApiParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType type, PsiBuilder builder) {
    parseLight(type, builder);
    return builder.getTreeBuilt();
  }

  public void parseLight(IElementType type, PsiBuilder builder) {
    boolean result;
    builder = adapt_builder_(type, builder, this, null);
    Marker marker = enter_section_(builder, 0, _COLLAPSE_, null);
    if (type instanceof IFileElementType) {
      result = parse_root_(type, builder, 0);
    }
    else {
      result = false;
    }
    exit_section_(builder, 0, marker, type, result, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType type, PsiBuilder builder, int level) {
    return apiFile(builder, level + 1);
  }

  /* ********************************************************** */
  // item_*
  static boolean apiFile(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "apiFile")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!item_(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "apiFile", pos)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // property|COMMENT|CRLF
  static boolean item_(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "item_")) return false;
    boolean result;
    result = property(builder, level + 1);
    if (!result) result = consumeToken(builder, Api_COMMENT);
    if (!result) result = consumeToken(builder, Api_CRLF);
    return result;
  }

  /* ********************************************************** */
  // (KEY? SEPARATOR VALUE?) | KEY
  public static boolean property(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "property")) return false;
    if (!nextTokenIs(builder, "<property>", Api_KEY, Api_SEPARATOR)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_PROPERTY, "<property>");
    result = property_0(builder, level + 1);
    if (!result) result = consumeToken(builder, Api_KEY);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // KEY? SEPARATOR VALUE?
  private static boolean property_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "property_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = property_0_0(builder, level + 1);
    result = result && consumeToken(builder, Api_SEPARATOR);
    result = result && property_0_2(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  // KEY?
  private static boolean property_0_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "property_0_0")) return false;
    consumeToken(builder, Api_KEY);
    return true;
  }

  // VALUE?
  private static boolean property_0_2(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "property_0_2")) return false;
    consumeToken(builder, Api_VALUE);
    return true;
  }

}
