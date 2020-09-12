/*
 * Copyright 2010-present ApiDebugger
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
  // api_block (SEPARATOR api_block?)*
  static boolean api_(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "api_")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = api_block(builder, level + 1);
    result = result && api__1(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  // (SEPARATOR api_block?)*
  private static boolean api__1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "api__1")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!api__1_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "api__1", pos)) break;
    }
    return true;
  }

  // SEPARATOR api_block?
  private static boolean api__1_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "api__1_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_SEPARATOR);
    result = result && api__1_0_1(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  // api_block?
  private static boolean api__1_0_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "api__1_0_1")) return false;
    api_block(builder, level + 1);
    return true;
  }

  /* ********************************************************** */
  // comment* description* comment* request
  public static boolean api_block(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "api_block")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_API_BLOCK, "<api block>");
    result = api_block_0(builder, level + 1);
    result = result && api_block_1(builder, level + 1);
    result = result && api_block_2(builder, level + 1);
    result = result && request(builder, level + 1);
    exit_section_(builder, level, marker, result, false, ApiParser::recover_api_block);
    return result;
  }

  // comment*
  private static boolean api_block_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "api_block_0")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!comment(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "api_block_0", pos)) break;
    }
    return true;
  }

  // description*
  private static boolean api_block_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "api_block_1")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!description(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "api_block_1", pos)) break;
    }
    return true;
  }

  // comment*
  private static boolean api_block_2(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "api_block_2")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!comment(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "api_block_2", pos)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LINE_COMMENT | MULTILINE_COMMENT
  static boolean comment(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "comment")) return false;
    if (!nextTokenIs(builder, "", Api_LINE_COMMENT, Api_MULTILINE_COMMENT)) return false;
    boolean result;
    result = consumeToken(builder, Api_LINE_COMMENT);
    if (!result) result = consumeToken(builder, Api_MULTILINE_COMMENT);
    return result;
  }

  /* ********************************************************** */
  // '-' DESCRIPTION_KEY ':' LINE_TEXT?
  public static boolean description(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "description")) return false;
    if (!nextTokenIs(builder, Api_HYPHEN)) return false;
    boolean result, pinned;
    Marker marker = enter_section_(builder, level, _NONE_, Api_DESCRIPTION, null);
    result = consumeTokens(builder, 1, Api_HYPHEN, Api_DESCRIPTION_KEY, Api_COLON);
    pinned = result; // pin = 1
    result = result && description_3(builder, level + 1);
    exit_section_(builder, level, marker, result, pinned, null);
    return result || pinned;
  }

  // LINE_TEXT?
  private static boolean description_3(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "description_3")) return false;
    consumeToken(builder, Api_LINE_TEXT);
    return true;
  }

  /* ********************************************************** */
  // ':' port
  static boolean full_port(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "full_port")) return false;
    if (!nextTokenIs(builder, Api_COLON)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_COLON);
    result = result && port(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  /* ********************************************************** */
  // scheme '://'
  static boolean full_scheme(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "full_scheme")) return false;
    if (!nextTokenIs(builder, "", Api_HTTP, Api_HTTPS)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = scheme(builder, level + 1);
    result = result && consumeToken(builder, Api_SCHEME_SEPARATOR);
    exit_section_(builder, marker, null, result);
    return result;
  }

  /* ********************************************************** */
  // header_field_key (':' header_field_val?)?
  public static boolean header_field(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field")) return false;
    if (!nextTokenIs(builder, "<header field>", Api_HEADER_FIELD_NAME, Api_LBRACES)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_HEADER_FIELD, "<header field>");
    result = header_field_key(builder, level + 1);
    result = result && header_field_1(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // (':' header_field_val?)?
  private static boolean header_field_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_1")) return false;
    header_field_1_0(builder, level + 1);
    return true;
  }

  // ':' header_field_val?
  private static boolean header_field_1_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_1_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_COLON);
    result = result && header_field_1_0_1(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  // header_field_val?
  private static boolean header_field_1_0_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_1_0_1")) return false;
    header_field_val(builder, level + 1);
    return true;
  }

  /* ********************************************************** */
  // (variable | HEADER_FIELD_NAME)+
  public static boolean header_field_key(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_key")) return false;
    if (!nextTokenIs(builder, "<header field key>", Api_HEADER_FIELD_NAME, Api_LBRACES)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_HEADER_FIELD_KEY, "<header field key>");
    result = header_field_key_0(builder, level + 1);
    while (result) {
      int pos = current_position_(builder);
      if (!header_field_key_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "header_field_key", pos)) break;
    }
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // variable | HEADER_FIELD_NAME
  private static boolean header_field_key_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_key_0")) return false;
    boolean result;
    result = variable(builder, level + 1);
    if (!result) result = consumeToken(builder, Api_HEADER_FIELD_NAME);
    return result;
  }

  /* ********************************************************** */
  // header_field_val_item (';' header_field_val_item?)*
  public static boolean header_field_val(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_val")) return false;
    if (!nextTokenIs(builder, "<header field val>", Api_HEADER_FIELD_VALUE, Api_LBRACES)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_HEADER_FIELD_VAL, "<header field val>");
    result = header_field_val_item(builder, level + 1);
    result = result && header_field_val_1(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // (';' header_field_val_item?)*
  private static boolean header_field_val_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_val_1")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!header_field_val_1_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "header_field_val_1", pos)) break;
    }
    return true;
  }

  // ';' header_field_val_item?
  private static boolean header_field_val_1_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_val_1_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_SEMICOLON);
    result = result && header_field_val_1_0_1(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  // header_field_val_item?
  private static boolean header_field_val_1_0_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_val_1_0_1")) return false;
    header_field_val_item(builder, level + 1);
    return true;
  }

  /* ********************************************************** */
  // (variable | HEADER_FIELD_VALUE)+
  static boolean header_field_val_item(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_val_item")) return false;
    if (!nextTokenIs(builder, "", Api_HEADER_FIELD_VALUE, Api_LBRACES)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = header_field_val_item_0(builder, level + 1);
    while (result) {
      int pos = current_position_(builder);
      if (!header_field_val_item_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "header_field_val_item", pos)) break;
    }
    exit_section_(builder, marker, null, result);
    return result;
  }

  // variable | HEADER_FIELD_VALUE
  private static boolean header_field_val_item_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "header_field_val_item_0")) return false;
    boolean result;
    result = variable(builder, level + 1);
    if (!result) result = consumeToken(builder, Api_HEADER_FIELD_VALUE);
    return result;
  }

  /* ********************************************************** */
  // (variable | HOST_VALUE)+
  public static boolean host(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "host")) return false;
    if (!nextTokenIs(builder, "<host>", Api_HOST_VALUE, Api_LBRACES)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_HOST, "<host>");
    result = host_0(builder, level + 1);
    while (result) {
      int pos = current_position_(builder);
      if (!host_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "host", pos)) break;
    }
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // variable | HOST_VALUE
  private static boolean host_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "host_0")) return false;
    boolean result;
    result = variable(builder, level + 1);
    if (!result) result = consumeToken(builder, Api_HOST_VALUE);
    return result;
  }

  /* ********************************************************** */
  // '< ' RELATIVE_FILE_PATH
  public static boolean input_file(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "input_file")) return false;
    if (!nextTokenIs(builder, Api_INPUT_SIGNAL)) return false;
    boolean result, pinned;
    Marker marker = enter_section_(builder, level, _NONE_, Api_INPUT_FILE, null);
    result = consumeTokens(builder, 1, Api_INPUT_SIGNAL, Api_RELATIVE_FILE_PATH);
    pinned = result; // pin = 1
    exit_section_(builder, level, marker, result, pinned, null);
    return result || pinned;
  }

  /* ********************************************************** */
  // request_message_group  | multipart_message
  static boolean message_body(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "message_body")) return false;
    boolean result;
    result = request_message_group(builder, level + 1);
    if (!result) result = multipart_message(builder, level + 1);
    return result;
  }

  /* ********************************************************** */
  // OPTIONS | GET | HEAD | POST | PUT | DELETE | TRACE | CONNECT | PATCH
  public static boolean method(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "method")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_METHOD, "<method>");
    result = consumeToken(builder, Api_OPTIONS);
    if (!result) result = consumeToken(builder, Api_GET);
    if (!result) result = consumeToken(builder, Api_HEAD);
    if (!result) result = consumeToken(builder, Api_POST);
    if (!result) result = consumeToken(builder, Api_PUT);
    if (!result) result = consumeToken(builder, Api_DELETE);
    if (!result) result = consumeToken(builder, Api_TRACE);
    if (!result) result = consumeToken(builder, Api_CONNECT);
    if (!result) result = consumeToken(builder, Api_PATCH);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  /* ********************************************************** */
  // header_field* request_message_group?
  public static boolean multipart_field(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "multipart_field")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_MULTIPART_FIELD, "<multipart field>");
    result = multipart_field_0(builder, level + 1);
    result = result && multipart_field_1(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // header_field*
  private static boolean multipart_field_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "multipart_field_0")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!header_field(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "multipart_field_0", pos)) break;
    }
    return true;
  }

  // request_message_group?
  private static boolean multipart_field_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "multipart_field_1")) return false;
    request_message_group(builder, level + 1);
    return true;
  }

  /* ********************************************************** */
  // MESSAGE_BOUNDARY (multipart_field MESSAGE_BOUNDARY)* (multipart_field MESSAGE_BOUNDARY_END)
  public static boolean multipart_message(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "multipart_message")) return false;
    if (!nextTokenIs(builder, Api_MESSAGE_BOUNDARY)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_MESSAGE_BOUNDARY);
    result = result && multipart_message_1(builder, level + 1);
    result = result && multipart_message_2(builder, level + 1);
    exit_section_(builder, marker, Api_MULTIPART_MESSAGE, result);
    return result;
  }

  // (multipart_field MESSAGE_BOUNDARY)*
  private static boolean multipart_message_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "multipart_message_1")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!multipart_message_1_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "multipart_message_1", pos)) break;
    }
    return true;
  }

  // multipart_field MESSAGE_BOUNDARY
  private static boolean multipart_message_1_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "multipart_message_1_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = multipart_field(builder, level + 1);
    result = result && consumeToken(builder, Api_MESSAGE_BOUNDARY);
    exit_section_(builder, marker, null, result);
    return result;
  }

  // multipart_field MESSAGE_BOUNDARY_END
  private static boolean multipart_message_2(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "multipart_message_2")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = multipart_field(builder, level + 1);
    result = result && consumeToken(builder, Api_MESSAGE_BOUNDARY_END);
    exit_section_(builder, marker, null, result);
    return result;
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
  // segment_block*
  static boolean path_segments(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "path_segments")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!segment_block(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "path_segments", pos)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (variable | PORT_SEGMENT)+
  public static boolean port(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "port")) return false;
    if (!nextTokenIs(builder, "<port>", Api_LBRACES, Api_PORT_SEGMENT)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_PORT, "<port>");
    result = port_0(builder, level + 1);
    while (result) {
      int pos = current_position_(builder);
      if (!port_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "port", pos)) break;
    }
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // variable | PORT_SEGMENT
  private static boolean port_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "port_0")) return false;
    boolean result;
    result = variable(builder, level + 1);
    if (!result) result = consumeToken(builder, Api_PORT_SEGMENT);
    return result;
  }

  /* ********************************************************** */
  // query_parameter ('&' query_parameter)*
  public static boolean query(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query")) return false;
    if (!nextTokenIs(builder, "<query>", Api_LBRACES, Api_QUERY_NAME)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_QUERY, "<query>");
    result = query_parameter(builder, level + 1);
    result = result && query_1(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
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
    if (!nextTokenIs(builder, "<query parameter>", Api_LBRACES, Api_QUERY_NAME)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_QUERY_PARAMETER, "<query parameter>");
    result = query_parameter_key(builder, level + 1);
    result = result && query_parameter_1(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
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
  // (variable | QUERY_NAME)+
  public static boolean query_parameter_key(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_parameter_key")) return false;
    if (!nextTokenIs(builder, "<query parameter key>", Api_LBRACES, Api_QUERY_NAME)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_QUERY_PARAMETER_KEY, "<query parameter key>");
    result = query_parameter_key_0(builder, level + 1);
    while (result) {
      int pos = current_position_(builder);
      if (!query_parameter_key_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "query_parameter_key", pos)) break;
    }
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // variable | QUERY_NAME
  private static boolean query_parameter_key_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_parameter_key_0")) return false;
    boolean result;
    result = variable(builder, level + 1);
    if (!result) result = consumeToken(builder, Api_QUERY_NAME);
    return result;
  }

  /* ********************************************************** */
  // (variable | QUERY_VALUE)+
  public static boolean query_parameter_value(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_parameter_value")) return false;
    if (!nextTokenIs(builder, "<query parameter value>", Api_LBRACES, Api_QUERY_VALUE)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_QUERY_PARAMETER_VALUE, "<query parameter value>");
    result = query_parameter_value_0(builder, level + 1);
    while (result) {
      int pos = current_position_(builder);
      if (!query_parameter_value_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "query_parameter_value", pos)) break;
    }
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // variable | QUERY_VALUE
  private static boolean query_parameter_value_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "query_parameter_value_0")) return false;
    boolean result;
    result = variable(builder, level + 1);
    if (!result) result = consumeToken(builder, Api_QUERY_VALUE);
    return result;
  }

  /* ********************************************************** */
  // !(SEPARATOR)
  static boolean recover_api_block(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "recover_api_block")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NOT_);
    result = !consumeToken(builder, Api_SEPARATOR);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  /* ********************************************************** */
  // request_line header_field* message_body?
  public static boolean request(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_REQUEST, "<request>");
    result = request_line(builder, level + 1);
    result = result && request_1(builder, level + 1);
    result = result && request_2(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // header_field*
  private static boolean request_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_1")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!header_field(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "request_1", pos)) break;
    }
    return true;
  }

  // message_body?
  private static boolean request_2(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_2")) return false;
    message_body(builder, level + 1);
    return true;
  }

  /* ********************************************************** */
  // method request_target
  public static boolean request_line(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_line")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_REQUEST_LINE, "<request line>");
    result = method(builder, level + 1);
    result = result && request_target(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  /* ********************************************************** */
  // (MESSAGE_TEXT | input_file)+
  public static boolean request_message_group(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_message_group")) return false;
    if (!nextTokenIs(builder, "<request message group>", Api_INPUT_SIGNAL, Api_MESSAGE_TEXT)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_REQUEST_MESSAGE_GROUP, "<request message group>");
    result = request_message_group_0(builder, level + 1);
    while (result) {
      int pos = current_position_(builder);
      if (!request_message_group_0(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "request_message_group", pos)) break;
    }
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // MESSAGE_TEXT | input_file
  private static boolean request_message_group_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_message_group_0")) return false;
    boolean result;
    result = consumeToken(builder, Api_MESSAGE_TEXT);
    if (!result) result = input_file(builder, level + 1);
    return result;
  }

  /* ********************************************************** */
  // full_scheme? host full_port? path_segments path_query?
  public static boolean request_target(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_target")) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, Api_REQUEST_TARGET, "<request target>");
    result = request_target_0(builder, level + 1);
    result = result && host(builder, level + 1);
    result = result && request_target_2(builder, level + 1);
    result = result && path_segments(builder, level + 1);
    result = result && request_target_4(builder, level + 1);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // full_scheme?
  private static boolean request_target_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_target_0")) return false;
    full_scheme(builder, level + 1);
    return true;
  }

  // full_port?
  private static boolean request_target_2(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_target_2")) return false;
    full_port(builder, level + 1);
    return true;
  }

  // path_query?
  private static boolean request_target_4(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "request_target_4")) return false;
    path_query(builder, level + 1);
    return true;
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

  /* ********************************************************** */
  // '/' SEGMENT?
  public static boolean segment_block(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "segment_block")) return false;
    if (!nextTokenIs(builder, Api_SLASH)) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = consumeToken(builder, Api_SLASH);
    result = result && segment_block_1(builder, level + 1);
    exit_section_(builder, marker, Api_SEGMENT_BLOCK, result);
    return result;
  }

  // SEGMENT?
  private static boolean segment_block_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "segment_block_1")) return false;
    consumeToken(builder, Api_SEGMENT);
    return true;
  }

  /* ********************************************************** */
  // '{{' IDENTIFIER? '}}'
  public static boolean variable(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "variable")) return false;
    if (!nextTokenIs(builder, Api_LBRACES)) return false;
    boolean result, pinned;
    Marker marker = enter_section_(builder, level, _NONE_, Api_VARIABLE, null);
    result = consumeToken(builder, Api_LBRACES);
    pinned = result; // pin = 1
    result = result && report_error_(builder, variable_1(builder, level + 1));
    result = pinned && consumeToken(builder, Api_RBRACES) && result;
    exit_section_(builder, level, marker, result, pinned, null);
    return result || pinned;
  }

  // IDENTIFIER?
  private static boolean variable_1(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "variable_1")) return false;
    consumeToken(builder, Api_IDENTIFIER);
    return true;
  }

}
