// This is a generated file. Not intended for manual editing.
package io.chengguo.api.debugger.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
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
    if (t instanceof IFileElementType) {
      r = parse_root_(t, b, 0);
    }
    else {
      r = false;
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return api(b, l + 1);
  }

  /* ********************************************************** */
  // FALG_DESCRIPTION STRING
  public static boolean DescriptionDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DescriptionDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DESCRIPTION_DECLARATION, "<description declaration>");
    r = consumeTokens(b, 1, FALG_DESCRIPTION, STRING);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, not_request_parser_);
    return r || p;
  }

  /* ********************************************************** */
  // GET
  //   | POST
  //   | PUT
  //   | PATCH
  //   | DELETE
  public static boolean Methods(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Methods")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METHODS, "<methods>");
    r = consumeToken(b, GET);
    if (!r) r = consumeToken(b, POST);
    if (!r) r = consumeToken(b, PUT);
    if (!r) r = consumeToken(b, PATCH);
    if (!r) r = consumeToken(b, DELETE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Methods IDENTIFIER
  public static boolean RequestLine(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RequestLine")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REQUEST_LINE, "<request line>");
    r = Methods(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // FALG_TITLE STRING
  public static boolean TitleDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TitleDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TITLE_DECLARATION, "<title declaration>");
    r = consumeTokens(b, 1, FALG_TITLE, STRING);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, not_description_or_request_parser_);
    return r || p;
  }

  /* ********************************************************** */
  // TitleDeclaration DescriptionDeclaration RequestLine
  static boolean api(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "api")) return false;
    if (!nextTokenIs(b, FALG_TITLE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TitleDeclaration(b, l + 1);
    r = r && DescriptionDeclaration(b, l + 1);
    r = r && RequestLine(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !( FALG_DESCRIPTION | Methods )
  static boolean not_description_or_request(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_description_or_request")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_description_or_request_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FALG_DESCRIPTION | Methods
  private static boolean not_description_or_request_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_description_or_request_0")) return false;
    boolean r;
    r = consumeToken(b, FALG_DESCRIPTION);
    if (!r) r = Methods(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // !( Methods )
  static boolean not_request(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_request")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_request_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( Methods )
  private static boolean not_request_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_request_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Methods(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  static final Parser not_description_or_request_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return not_description_or_request(b, l + 1);
    }
  };
  static final Parser not_request_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return not_request(b, l + 1);
    }
  };
}
