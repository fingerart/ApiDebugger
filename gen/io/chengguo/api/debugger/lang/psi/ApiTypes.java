/*
 * Copyright 2010-present ApiDebugger
 */
package io.chengguo.api.debugger.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.chengguo.api.debugger.lang.psi.impl.*;

public interface ApiTypes {

  IElementType Api_API_BLOCK = new ApiElementType("Api_API_BLOCK");
  IElementType Api_DESCRIPTION = new ApiElementType("Api_DESCRIPTION");
  IElementType Api_FILE_PATH = new ApiElementType("Api_FILE_PATH");
  IElementType Api_HEADER_FIELD = new ApiElementType("Api_HEADER_FIELD");
  IElementType Api_HEADER_FIELD_VALUE_ITEM = new ApiElementType("Api_HEADER_FIELD_VALUE_ITEM");
  IElementType Api_HEADER_KEY = new ApiElementType("Api_HEADER_KEY");
  IElementType Api_HEADER_VALUE = new ApiElementType("Api_HEADER_VALUE");
  IElementType Api_HOST = new ApiElementType("Api_HOST");
  IElementType Api_INPUT_FILE = new ApiElementType("Api_INPUT_FILE");
  IElementType Api_MESSAGE_BODY = new ApiElementType("Api_MESSAGE_BODY");
  IElementType Api_METHOD = new ApiElementType("Api_METHOD");
  IElementType Api_MULTIPART_FIELD = new ApiElementType("Api_MULTIPART_FIELD");
  IElementType Api_MULTIPART_MESSAGE = new ApiElementType("Api_MULTIPART_MESSAGE");
  IElementType Api_PORT = new ApiElementType("Api_PORT");
  IElementType Api_QUERY = new ApiElementType("Api_QUERY");
  IElementType Api_QUERY_PARAMETER = new ApiElementType("Api_QUERY_PARAMETER");
  IElementType Api_QUERY_PARAMETER_KEY = new ApiElementType("Api_QUERY_PARAMETER_KEY");
  IElementType Api_QUERY_PARAMETER_VALUE = new ApiElementType("Api_QUERY_PARAMETER_VALUE");
  IElementType Api_REQUEST = new ApiElementType("Api_REQUEST");
  IElementType Api_REQUEST_LINE = new ApiElementType("Api_REQUEST_LINE");
  IElementType Api_REQUEST_MESSAGE_GROUP = new ApiElementType("Api_REQUEST_MESSAGE_GROUP");
  IElementType Api_REQUEST_TARGET = new ApiElementType("Api_REQUEST_TARGET");
  IElementType Api_SCHEME = new ApiElementType("Api_SCHEME");
  IElementType Api_SEGMENT_BLOCK = new ApiElementType("Api_SEGMENT_BLOCK");
  IElementType Api_VARIABLE = new ApiElementType("Api_VARIABLE");

  IElementType Api_AMPERSAND = new ApiTokenType("&");
  IElementType Api_COLON = new ApiTokenType(":");
  IElementType Api_CONNECT = new ApiTokenType("CONNECT");
  IElementType Api_DELETE = new ApiTokenType("DELETE");
  IElementType Api_DESCRIPTION_KEY = new ApiTokenType("DESCRIPTION_KEY");
  IElementType Api_EQUALS = new ApiTokenType("=");
  IElementType Api_GET = new ApiTokenType("GET");
  IElementType Api_HEAD = new ApiTokenType("HEAD");
  IElementType Api_HEADER_FIELD_NAME = new ApiTokenType("HEADER_FIELD_NAME");
  IElementType Api_HEADER_FIELD_VALUE = new ApiTokenType("HEADER_FIELD_VALUE");
  IElementType Api_HOST_VALUE = new ApiTokenType("HOST_VALUE");
  IElementType Api_HTTP = new ApiTokenType("http");
  IElementType Api_HTTPS = new ApiTokenType("https");
  IElementType Api_HYPHEN = new ApiTokenType("-");
  IElementType Api_IDENTIFIER = new ApiTokenType("IDENTIFIER");
  IElementType Api_INPUT_SIGNAL = new ApiTokenType("< ");
  IElementType Api_LBRACES = new ApiTokenType("{{");
  IElementType Api_LINE_COMMENT = new ApiTokenType("LINE_COMMENT");
  IElementType Api_LINE_TEXT = new ApiTokenType("LINE_TEXT");
  IElementType Api_MESSAGE_BOUNDARY = new ApiTokenType("MESSAGE_BOUNDARY");
  IElementType Api_MESSAGE_BOUNDARY_END = new ApiTokenType("MESSAGE_BOUNDARY_END");
  IElementType Api_MESSAGE_TEXT = new ApiTokenType("MESSAGE_TEXT");
  IElementType Api_MULTILINE_COMMENT = new ApiTokenType("MULTILINE_COMMENT");
  IElementType Api_OPTIONS = new ApiTokenType("OPTIONS");
  IElementType Api_PATCH = new ApiTokenType("PATCH");
  IElementType Api_PORT_SEGMENT = new ApiTokenType("PORT_SEGMENT");
  IElementType Api_POST = new ApiTokenType("POST");
  IElementType Api_PUT = new ApiTokenType("PUT");
  IElementType Api_QUERY_NAME = new ApiTokenType("QUERY_NAME");
  IElementType Api_QUERY_VALUE = new ApiTokenType("QUERY_VALUE");
  IElementType Api_QUESTION_MARK = new ApiTokenType("?");
  IElementType Api_RBRACES = new ApiTokenType("}}");
  IElementType Api_RELATIVE_FILE_PATH = new ApiTokenType("RELATIVE_FILE_PATH");
  IElementType Api_SCHEME_SEPARATOR = new ApiTokenType("://");
  IElementType Api_SEGMENT = new ApiTokenType("SEGMENT");
  IElementType Api_SEMICOLON = new ApiTokenType(";");
  IElementType Api_SEPARATOR = new ApiTokenType("SEPARATOR");
  IElementType Api_SLASH = new ApiTokenType("/");
  IElementType Api_TRACE = new ApiTokenType("TRACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == Api_API_BLOCK) {
        return new ApiApiBlockImpl(node);
      }
      else if (type == Api_DESCRIPTION) {
        return new ApiDescriptionImpl(node);
      }
      else if (type == Api_FILE_PATH) {
        return new ApiFilePathImpl(node);
      }
      else if (type == Api_HEADER_FIELD) {
        return new ApiHeaderFieldImpl(node);
      }
      else if (type == Api_HEADER_FIELD_VALUE_ITEM) {
        return new ApiHeaderFieldValueItemImpl(node);
      }
      else if (type == Api_HEADER_KEY) {
        return new ApiHeaderKeyImpl(node);
      }
      else if (type == Api_HEADER_VALUE) {
        return new ApiHeaderValueImpl(node);
      }
      else if (type == Api_HOST) {
        return new ApiHostImpl(node);
      }
      else if (type == Api_INPUT_FILE) {
        return new ApiInputFileImpl(node);
      }
      else if (type == Api_MESSAGE_BODY) {
        return new ApiMessageBodyImpl(node);
      }
      else if (type == Api_METHOD) {
        return new ApiMethodImpl(node);
      }
      else if (type == Api_MULTIPART_FIELD) {
        return new ApiMultipartFieldImpl(node);
      }
      else if (type == Api_MULTIPART_MESSAGE) {
        return new ApiMultipartMessageImpl(node);
      }
      else if (type == Api_PORT) {
        return new ApiPortImpl(node);
      }
      else if (type == Api_QUERY) {
        return new ApiQueryImpl(node);
      }
      else if (type == Api_QUERY_PARAMETER) {
        return new ApiQueryParameterImpl(node);
      }
      else if (type == Api_QUERY_PARAMETER_KEY) {
        return new ApiQueryParameterKeyImpl(node);
      }
      else if (type == Api_QUERY_PARAMETER_VALUE) {
        return new ApiQueryParameterValueImpl(node);
      }
      else if (type == Api_REQUEST) {
        return new ApiRequestImpl(node);
      }
      else if (type == Api_REQUEST_LINE) {
        return new ApiRequestLineImpl(node);
      }
      else if (type == Api_REQUEST_MESSAGE_GROUP) {
        return new ApiRequestMessageGroupImpl(node);
      }
      else if (type == Api_REQUEST_TARGET) {
        return new ApiRequestTargetImpl(node);
      }
      else if (type == Api_SCHEME) {
        return new ApiSchemeImpl(node);
      }
      else if (type == Api_SEGMENT_BLOCK) {
        return new ApiSegmentBlockImpl(node);
      }
      else if (type == Api_VARIABLE) {
        return new ApiVariableImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
