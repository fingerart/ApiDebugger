// This is a generated file. Not intended for manual editing.
package io.chengguo.api.debugger.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;
import static io.chengguo.api.debugger.lang.parser.ApiParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ApiParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return Root(b, l + 1);
  }

  /* ********************************************************** */
  // Method RAW_STRING
  static boolean Api(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Api")) return false;
    if (!nextTokenIs(b, "", GET, POST)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Method(b, l + 1);
    r = r && consumeToken(b, RAW_STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Title Api
  static boolean ApiBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApiBlock")) return false;
    if (!nextTokenIs(b, FLAG_TITLE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Title(b, l + 1);
    r = r && Api(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // 'GET'  |
  // 'POST'
  static boolean Method(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Method")) return false;
    if (!nextTokenIs(b, "", GET, POST)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GET);
    if (!r) r = consumeToken(b, POST);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (ApiBlock|LINE_COMMENT)*
  static boolean Root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Root")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Root_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Root", c)) break;
    }
    return true;
  }

  // ApiBlock|LINE_COMMENT
  private static boolean Root_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Root_0")) return false;
    boolean r;
    r = ApiBlock(b, l + 1);
    if (!r) r = consumeToken(b, LINE_COMMENT);
    return r;
  }

  /* ********************************************************** */
  // '##' RAW_STRING
  public static boolean Title(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Title")) return false;
    if (!nextTokenIs(b, FLAG_TITLE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FLAG_TITLE, RAW_STRING);
    exit_section_(b, m, TITLE, r);
    return r;
  }

}
