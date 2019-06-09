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
    return ApiFile(b, l + 1);
  }

  /* ********************************************************** */
  // TitleDeclaration? DescriptionDeclaration? RequestLine Header*
  static boolean ApiFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApiFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ApiFile_0(b, l + 1);
    r = r && ApiFile_1(b, l + 1);
    r = r && RequestLine(b, l + 1);
    r = r && ApiFile_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TitleDeclaration?
  private static boolean ApiFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApiFile_0")) return false;
    TitleDeclaration(b, l + 1);
    return true;
  }

  // DescriptionDeclaration?
  private static boolean ApiFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApiFile_1")) return false;
    DescriptionDeclaration(b, l + 1);
    return true;
  }

  // Header*
  private static boolean ApiFile_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApiFile_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Header(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ApiFile_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '###' IDENTIFIER
  public static boolean DescriptionDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DescriptionDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DESCRIPTION_DECLARATION, "<description declaration>");
    r = consumeToken(b, "###");
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ':' IDENTIFIER
  public static boolean Header(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Header")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, ":");
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, HEADER, r);
    return r;
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
  // '#' IDENTIFIER
  public static boolean TitleDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TitleDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TITLE_DECLARATION, "<title declaration>");
    r = consumeToken(b, "#");
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
