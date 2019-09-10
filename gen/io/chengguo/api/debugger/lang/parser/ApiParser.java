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
    return File(b, l + 1);
  }

  /* ********************************************************** */
  // Info
  public static boolean Api(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Api")) return false;
    if (!nextTokenIs(b, FLAG_TITLE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Info(b, l + 1);
    exit_section_(b, m, API, r);
    return r;
  }

  /* ********************************************************** */
  // Api ?
  static boolean File(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File")) return false;
    Api(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Title
  public static boolean Info(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Info")) return false;
    if (!nextTokenIs(b, FLAG_TITLE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Title(b, l + 1);
    exit_section_(b, m, INFO, r);
    return r;
  }

  /* ********************************************************** */
  // '##' raw_string
  public static boolean Title(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Title")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TITLE, "<title>");
    r = consumeTokens(b, 2, FLAG_TITLE, RAW_STRING);
    exit_section_(b, l, m, r, false, title_recover_parser_);
    return r;
  }

  /* ********************************************************** */
  // !( '##' raw_string )
  static boolean title_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "title_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !title_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '##' raw_string
  private static boolean title_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "title_recover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FLAG_TITLE, RAW_STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  static final Parser title_recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return title_recover(b, l + 1);
    }
  };
}
