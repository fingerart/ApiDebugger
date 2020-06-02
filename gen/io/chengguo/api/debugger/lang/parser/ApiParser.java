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
  // api_*
  static boolean apiFile(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "apiFile")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!api_(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "apiFile", pos)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // item|LINE_COMMENT|MULTILINE_COMMENT|CRLF
  static boolean api_(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "api_")) return false;
    boolean result;
    result = item(builder, level + 1);
    if (!result) result = consumeToken(builder, Api_LINE_COMMENT);
    if (!result) result = consumeToken(builder, Api_MULTILINE_COMMENT);
    if (!result) result = consumeToken(builder, Api_CRLF);
    return result;
  }

  /* ********************************************************** */
  // TITLE
  public static boolean description(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "description")) return false;
    if (!nextTokenIs(builder, Api_TITLE)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_TITLE);
    exit_section_(builder, marker, Api_DESCRIPTION, result);
    return result;
  }

  /* ********************************************************** */
  // (HEADER_FIELD_NAME ':' HEADER_FIELD_VALUE)*
  public static boolean header_field(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field")) return false;
    Marker marker = enter_section_(builder, level, _NONE_, Api_HEADER_FIELD, "<header field>");
    while (true) {
      int pos = current_position_(builder);
      if (!header_field_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "header_field", pos)) break;
    }
    exit_section_(builder, level, marker, true, false, null);
    return true;
  }

  // HEADER_FIELD_NAME ':' HEADER_FIELD_VALUE
  private static boolean header_field_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeTokens(builder, 0, Api_HEADER_FIELD_NAME, Api_COLON, Api_HEADER_FIELD_VALUE);
    exit_section_(builder, marker, null, result);
    return result;
  }

  /* ********************************************************** */
  // HOST_VALUE
  public static boolean host(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "host")) return false;
    if (!nextTokenIs(builder, Api_HOST_VALUE)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_HOST_VALUE);
    exit_section_(builder, marker, Api_HOST, result);
    return result;
  }

  /* ********************************************************** */
  // description request
  public static boolean item(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "item")) return false;
    if (!nextTokenIs(builder, Api_TITLE)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = description(builder, level + 1);
    result = result && request(builder, level + 1);
    exit_section_(builder, marker, Api_ITEM, result);
    return result;
  }

  /* ********************************************************** */
  // ('/' SEGMENT)*
  public static boolean path_absolute(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "path_absolute")) return false;
    Marker marker = enter_section_(builder, level, _NONE_, Api_PATH_ABSOLUTE, "<path absolute>");
    while (true) {
      int pos = current_position_(builder);
      if (!path_absolute_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "path_absolute", pos)) break;
    }
    exit_section_(builder, level, marker, true, false, null);
    return true;
  }

  // '/' SEGMENT
  private static boolean path_absolute_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "path_absolute_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, "/");
    result = result && consumeToken(builder, Api_SEGMENT);
    exit_section_(builder, marker, null, result);
    return result;
  }

  /* ********************************************************** */
  // PORT_SEGMENT
  public static boolean port(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "port")) return false;
    if (!nextTokenIs(builder, Api_PORT_SEGMENT)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_PORT_SEGMENT);
    exit_section_(builder, marker, Api_PORT, result);
    return result;
  }

  /* ********************************************************** */
  // request_line header_field SEPARATOR
  public static boolean request(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request")) return false;
    if (!nextTokenIs(builder, Api_METHOD)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = request_line(builder, level + 1);
    result = result && header_field(builder, level + 1);
    result = result && consumeToken(builder, Api_SEPARATOR);
    exit_section_(builder, marker, Api_REQUEST, result);
    return result;
  }

  /* ********************************************************** */
  // METHOD
  public static boolean request_line(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_line")) return false;
    if (!nextTokenIs(builder, Api_METHOD)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_METHOD);
    exit_section_(builder, marker, Api_REQUEST_LINE, result);
    return result;
  }

  /* ********************************************************** */
  // scheme '://' host ':' port path_absolute
  public static boolean request_target(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_target")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_REQUEST_TARGET, "<request target>");
    result = scheme(builder, level + 1);
    result = result && consumeToken(builder, Api_SCHEME_SEPARATOR);
    result = result && host(builder, level + 1);
    result = result && consumeToken(builder, Api_COLON);
    result = result && port(builder, level + 1);
    result = result && path_absolute(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  /* ********************************************************** */
  // 'http' 's'?
  public static boolean scheme(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "scheme")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_SCHEME, "<scheme>");
    result = consumeToken(builder, "http");
    result = result && scheme_1(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // 's'?
  private static boolean scheme_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "scheme_1")) return false;
    consumeToken(builder, "s");
    return true;
  }

}
