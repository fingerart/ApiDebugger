// Generated from /Users/fingerart/Workspace/Other/ApiDebugger/src/main/antlr/io/chengguo/api/debugger/lang/parser/Api.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Api}.
 */
public interface ApiListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link Api#http}.
	 * @param ctx the parse tree
	 */
	void enterHttp(Api.HttpContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#http}.
	 * @param ctx the parse tree
	 */
	void exitHttp(Api.HttpContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(Api.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(Api.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(Api.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(Api.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#uri}.
	 * @param ctx the parse tree
	 */
	void enterUri(Api.UriContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#uri}.
	 * @param ctx the parse tree
	 */
	void exitUri(Api.UriContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#scheme}.
	 * @param ctx the parse tree
	 */
	void enterScheme(Api.SchemeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#scheme}.
	 * @param ctx the parse tree
	 */
	void exitScheme(Api.SchemeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#host}.
	 * @param ctx the parse tree
	 */
	void enterHost(Api.HostContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#host}.
	 * @param ctx the parse tree
	 */
	void exitHost(Api.HostContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#hostnumber}.
	 * @param ctx the parse tree
	 */
	void enterHostnumber(Api.HostnumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#hostnumber}.
	 * @param ctx the parse tree
	 */
	void exitHostnumber(Api.HostnumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#hostname}.
	 * @param ctx the parse tree
	 */
	void enterHostname(Api.HostnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#hostname}.
	 * @param ctx the parse tree
	 */
	void exitHostname(Api.HostnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#port}.
	 * @param ctx the parse tree
	 */
	void enterPort(Api.PortContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#port}.
	 * @param ctx the parse tree
	 */
	void exitPort(Api.PortContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(Api.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(Api.PathContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(Api.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(Api.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#search}.
	 * @param ctx the parse tree
	 */
	void enterSearch(Api.SearchContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#search}.
	 * @param ctx the parse tree
	 */
	void exitSearch(Api.SearchContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#searchparameter}.
	 * @param ctx the parse tree
	 */
	void enterSearchparameter(Api.SearchparameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#searchparameter}.
	 * @param ctx the parse tree
	 */
	void exitSearchparameter(Api.SearchparameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Api#string}.
	 * @param ctx the parse tree
	 */
	void enterString(Api.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link Api#string}.
	 * @param ctx the parse tree
	 */
	void exitString(Api.StringContext ctx);
}