// Generated from io/chengguo/api/debugger/lang/Api.g4 by ANTLR 4.8
package io.chengguo.api.debugger.lang;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Api}.
 */
public interface ApiListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Api#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(Api.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(Api.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#api}.
	 * @param ctx the parse tree
	 */
	void enterApi(Api.ApiContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#api}.
	 * @param ctx the parse tree
	 */
	void exitApi(Api.ApiContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#info}.
	 * @param ctx the parse tree
	 */
	void enterInfo(Api.InfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#info}.
	 * @param ctx the parse tree
	 */
	void exitInfo(Api.InfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(Api.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(Api.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(Api.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(Api.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(Api.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(Api.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(Api.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(Api.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(Api.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(Api.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#request}.
	 * @param ctx the parse tree
	 */
	void enterRequest(Api.RequestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#request}.
	 * @param ctx the parse tree
	 */
	void exitRequest(Api.RequestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#requestLine}.
	 * @param ctx the parse tree
	 */
	void enterRequestLine(Api.RequestLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#requestLine}.
	 * @param ctx the parse tree
	 */
	void exitRequestLine(Api.RequestLineContext ctx);
}