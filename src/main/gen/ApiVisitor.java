// Generated from /Users/fingerart/Workspace/Other/ApiDebugger/src/main/antlr/io/chengguo/api/debugger/lang/parser/Api.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Api}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ApiVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Api#api}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApi(Api.ApiContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#info}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfo(Api.InfoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#http}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHttp(Api.HttpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(Api.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(Api.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#uri}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUri(Api.UriContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#scheme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScheme(Api.SchemeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#host}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHost(Api.HostContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#hostnumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHostnumber(Api.HostnumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#hostname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHostname(Api.HostnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#port}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPort(Api.PortContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPath(Api.PathContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(Api.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#search}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearch(Api.SearchContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#searchparameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearchparameter(Api.SearchparameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link Api#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(Api.StringContext ctx);
}