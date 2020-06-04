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

  IElementType Api_API_BLOCK = new ApiElementType("Api_API_BLOCK");
  IElementType Api_DESCRIPTION = new ApiElementType("Api_DESCRIPTION");
  IElementType Api_HEADER_FIELD = new ApiElementType("Api_HEADER_FIELD");
  IElementType Api_HOST = new ApiElementType("Api_HOST");
  IElementType Api_PATH_ABSOLUTE = new ApiElementType("Api_PATH_ABSOLUTE");
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
  IElementType Api_VARIABLE = new ApiElementType("Api_VARIABLE");
  IElementType Api_VARIABLE_NAME = new ApiElementType("Api_VARIABLE_NAME");

  IElementType Api_AMPERSAND = new ApiTokenType("&");
  IElementType Api_COLON = new ApiTokenType(":");
  IElementType Api_EQUALS = new ApiTokenType("=");
  IElementType Api_HEADER_FIELD_NAME = new ApiTokenType("HEADER_FIELD_NAME");
  IElementType Api_HEADER_FIELD_VALUE = new ApiTokenType("HEADER_FIELD_VALUE");
  IElementType Api_HOST_VALUE = new ApiTokenType("HOST_VALUE");
  IElementType Api_HTTP = new ApiTokenType("http");
  IElementType Api_HTTPS = new ApiTokenType("https");
  IElementType Api_IDENTIFIER = new ApiTokenType("IDENTIFIER");
  IElementType Api_LBRACES = new ApiTokenType("{{");
  IElementType Api_LINE_COMMENT = new ApiTokenType("LINE_COMMENT");
  IElementType Api_MESSAGE_TEXT = new ApiTokenType("MESSAGE_TEXT");
  IElementType Api_METHOD = new ApiTokenType("METHOD");
  IElementType Api_MULTILINE_COMMENT = new ApiTokenType("MULTILINE_COMMENT");
  IElementType Api_PORT_SEGMENT = new ApiTokenType("PORT_SEGMENT");
  IElementType Api_QUERY_NAME = new ApiTokenType("QUERY_NAME");
  IElementType Api_QUERY_VALUE = new ApiTokenType("QUERY_VALUE");
  IElementType Api_QUESTION_MARK = new ApiTokenType("?");
  IElementType Api_RBRACES = new ApiTokenType("}}");
  IElementType Api_SCHEME_SEPARATOR = new ApiTokenType("://");
  IElementType Api_SEGMENT = new ApiTokenType("SEGMENT");
  IElementType Api_SEPARATOR = new ApiTokenType("SEPARATOR");
  IElementType Api_SLASH = new ApiTokenType("/");
  IElementType Api_TITLE = new ApiTokenType("TITLE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == Api_API_BLOCK) {
        return new ApiApiBlockImpl(node);
      }
      else if (type == Api_DESCRIPTION) {
        return new ApiDescriptionImpl(node);
      }
      else if (type == Api_HEADER_FIELD) {
        return new ApiHeaderFieldImpl(node);
      }
      else if (type == Api_HOST) {
        return new ApiHostImpl(node);
      }
      else if (type == Api_PATH_ABSOLUTE) {
        return new ApiPathAbsoluteImpl(node);
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
      else if (type == Api_VARIABLE) {
        return new ApiVariableImpl(node);
      }
      else if (type == Api_VARIABLE_NAME) {
        return new ApiVariableNameImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
