// Generated from Api.g4 by ANTLR 4.8
package io.chengguo.api.debugger.lang;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ApiParser}.
 */
public interface ApiListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ApiParser#api}.
	 * @param ctx the parse tree
	 */
	void enterApi(ApiParser.ApiContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#api}.
	 * @param ctx the parse tree
	 */
	void exitApi(ApiParser.ApiContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#info}.
	 * @param ctx the parse tree
	 */
	void enterInfo(ApiParser.InfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#info}.
	 * @param ctx the parse tree
	 */
	void exitInfo(ApiParser.InfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(ApiParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(ApiParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(ApiParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(ApiParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(ApiParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(ApiParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(ApiParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(ApiParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ApiParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ApiParser.ValueContext ctx);
}