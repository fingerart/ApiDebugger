// Generated from /Users/fingerart/Workspace/Other/ApiDebugger/src/main/antlr/io/chengguo/api/debugger/lang/parser/Test.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TestParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TestVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TestParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(TestParser.TestContext ctx);
}