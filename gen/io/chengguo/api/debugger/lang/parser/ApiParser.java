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
  // (HEADER_FIELD_NAME (':' HEADER_FIELD_VALUE?)?)*
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

  // HEADER_FIELD_NAME (':' HEADER_FIELD_VALUE?)?
  private static boolean header_field_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_HEADER_FIELD_NAME);
    result = result && header_field_0_1(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  // (':' HEADER_FIELD_VALUE?)?
  private static boolean header_field_0_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_0_1")) return false;
    header_field_0_1_0(builder, level + 1);
    return true;
  }

  // ':' HEADER_FIELD_VALUE?
  private static boolean header_field_0_1_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_0_1_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_COLON);
    result = result && header_field_0_1_0_1(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  // HEADER_FIELD_VALUE?
  private static boolean header_field_0_1_0_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_0_1_0_1")) return false;
    consumeToken(builder, Api_HEADER_FIELD_VALUE);
    return true;
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
  // request_message_group
  static boolean message_body(PsiBuilder builder, int level) {
    return request_message_group(builder, level + 1);
  }

  /* ********************************************************** */
  // path_segment* path_query?
  public static boolean path_absolute(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "path_absolute")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_PATH_ABSOLUTE, "<path absolute>");
    result = path_absolute_0(builder, level + 1);
    result = result && path_absolute_1(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // path_segment*
  private static boolean path_absolute_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "path_absolute_0")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!path_segment(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "path_absolute_0", pos)) break;
    }
    return true;
  }

  // path_query?
  private static boolean path_absolute_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "path_absolute_1")) return false;
    path_query(builder, level + 1);
    return true;
  }

  /* ********************************************************** */
  // '?' query
  static boolean path_query(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "path_query")) return false;
    if (!nextTokenIs(builder, Api_QUESTION_MARK)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_QUESTION_MARK);
    result = result && query(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  /* ********************************************************** */
  // '/' SEGMENT?
  static boolean path_segment(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "path_segment")) return false;
    if (!nextTokenIs(builder, Api_SLASH)) return false;
    boolean result, pinned;
    Marker marker = enter_section_(builder, level, _NONE_);
    result = consumeToken(builder, Api_SLASH);
    pinned = result; // pin = 1
    result = result && path_segment_1(builder, level + 1);
    exit_section_(builder, level, marker, result, pinned, null);
    return result || pinned;
  }

  // SEGMENT?
  private static boolean path_segment_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "path_segment_1")) return false;
    consumeToken(builder, Api_SEGMENT);
    return true;
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
  // query_parameter ('&' query_parameter)*
  public static boolean query(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query")) return false;
    if (!nextTokenIs(builder, Api_QUERY_NAME)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = query_parameter(builder, level + 1);
    result = result && query_1(builder, level + 1);
    exit_section_(builder, marker, Api_QUERY, result);
    return result;
  }

  // ('&' query_parameter)*
  private static boolean query_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_1")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!query_1_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "query_1", pos)) break;
    }
    return true;
  }

  // '&' query_parameter
  private static boolean query_1_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_1_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_AMPERSAND);
    result = result && query_parameter(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  /* ********************************************************** */
  // query_parameter_key ('=' query_parameter_value)?
  public static boolean query_parameter(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_parameter")) return false;
    if (!nextTokenIs(builder, Api_QUERY_NAME)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = query_parameter_key(builder, level + 1);
    result = result && query_parameter_1(builder, level + 1);
    exit_section_(builder, marker, Api_QUERY_PARAMETER, result);
    return result;
  }

  // ('=' query_parameter_value)?
  private static boolean query_parameter_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_parameter_1")) return false;
    query_parameter_1_0(builder, level + 1);
    return true;
  }

  // '=' query_parameter_value
  private static boolean query_parameter_1_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_parameter_1_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_EQUALS);
    result = result && query_parameter_value(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  /* ********************************************************** */
  // QUERY_NAME
  public static boolean query_parameter_key(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_parameter_key")) return false;
    if (!nextTokenIs(builder, Api_QUERY_NAME)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_QUERY_NAME);
    exit_section_(builder, marker, Api_QUERY_PARAMETER_KEY, result);
    return result;
  }

  /* ********************************************************** */
  // QUERY_VALUE
  public static boolean query_parameter_value(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_parameter_value")) return false;
    if (!nextTokenIs(builder, Api_QUERY_VALUE)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_QUERY_VALUE);
    exit_section_(builder, marker, Api_QUERY_PARAMETER_VALUE, result);
    return result;
  }

  /* ********************************************************** */
  // request_line header_field message_body SEPARATOR
  public static boolean request(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request")) return false;
    if (!nextTokenIs(builder, Api_METHOD)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = request_line(builder, level + 1);
    result = result && header_field(builder, level + 1);
    result = result && message_body(builder, level + 1);
    result = result && consumeToken(builder, Api_SEPARATOR);
    exit_section_(builder, marker, Api_REQUEST, result);
    return result;
  }

  /* ********************************************************** */
  // METHOD request_target
  public static boolean request_line(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_line")) return false;
    if (!nextTokenIs(builder, Api_METHOD)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_METHOD);
    result = result && request_target(builder, level + 1);
    exit_section_(builder, marker, Api_REQUEST_LINE, result);
    return result;
  }

  /* ********************************************************** */
  // MESSAGE_TEXT
  public static boolean request_message_group(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_message_group")) return false;
    if (!nextTokenIs(builder, Api_MESSAGE_TEXT)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_MESSAGE_TEXT);
    exit_section_(builder, marker, Api_REQUEST_MESSAGE_GROUP, result);
    return result;
  }

  /* ********************************************************** */
  // scheme '://' host ':' port path_absolute
  public static boolean request_target(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_target")) return false;
    if (!nextTokenIs(builder, "<request target>", Api_HTTP, Api_HTTPS)) return false;
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
  // 'http' | 'https'
  public static boolean scheme(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "scheme")) return false;
    if (!nextTokenIs(builder, "<scheme>", Api_HTTP, Api_HTTPS)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_SCHEME, "<scheme>");
    result = consumeToken(builder, Api_HTTP);
    if (!result) result = consumeToken(builder, Api_HTTPS);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

}
