// Generated from /Users/fingerart/Workspace/Other/ApiDebugger/src/main/antlr/io/chengguo/api/debugger/lang/ApiLexer.g4 by ANTLR 4.8
package io.chengguo.api.debugger.lang;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ApiLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FlagTitle=1, FlagDesOpen=2, Sub=3, Colon=4, Method=5, Description=6, NL=7, 
		WS=8, COMMENT=9, LINE_COMMENT=10, TitleContent=11;
	public static final int
		ModeTitle=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "ModeTitle"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"FlagTitle", "FlagDesOpen", "Sub", "Colon", "Method", "Description", 
			"NL", "WS", "COMMENT", "LINE_COMMENT", "ModeTitleClose", "TitleContent", 
			"Identifier", "EscapeSequence", "HexDigits", "HexDigit", "DIGIT", "LETTER", 
			"POST", "GET"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'---'", "'\"\"\"'", "'-'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FlagTitle", "FlagDesOpen", "Sub", "Colon", "Method", "Description", 
			"NL", "WS", "COMMENT", "LINE_COMMENT", "TitleContent"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ApiLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ApiLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\r\u00b5\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\6\6>\n\6\r\6\16\6?\5\6B\n\6\3\7\3\7"+
		"\3\7\7\7G\n\7\f\7\16\7J\13\7\3\7\3\7\3\b\5\bO\n\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\7\n[\n\n\f\n\16\n^\13\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\7\13i\n\13\f\13\16\13l\13\13\3\13\3\13\3\f\6\fq\n\f\r"+
		"\f\16\fr\3\f\3\f\3\f\3\r\6\ry\n\r\r\r\16\rz\3\16\3\16\3\16\7\16\u0080"+
		"\n\16\f\16\16\16\u0083\13\16\3\17\3\17\3\17\3\17\5\17\u0089\n\17\3\17"+
		"\5\17\u008c\n\17\3\17\3\17\3\17\6\17\u0091\n\17\r\17\16\17\u0092\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u009a\n\17\3\20\3\20\3\20\7\20\u009f\n\20\f"+
		"\20\16\20\u00a2\13\20\3\20\5\20\u00a5\n\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\\\2\26\4\3\6\4\b\5"+
		"\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\2\32\r\34\2\36\2 \2\"\2$\2&\2(\2"+
		"*\2\4\2\3\21\6\2\n\13\16\16$$^^\5\2\13\f\17\17\"\"\4\2\f\f\17\17\n\2$"+
		"$))^^ddhhppttvv\3\2\62\65\3\2\629\5\2\62;CHch\3\2\62;\4\2C\\c|\4\2RRr"+
		"r\4\2QQqq\4\2UUuu\4\2VVvv\4\2IIii\4\2GGgg\2\u00bf\2\4\3\2\2\2\2\6\3\2"+
		"\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22"+
		"\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\3\30\3\2\2\2\3\32\3\2\2\2\4,\3\2\2"+
		"\2\6\62\3\2\2\2\b\66\3\2\2\2\n8\3\2\2\2\fA\3\2\2\2\16C\3\2\2\2\20N\3\2"+
		"\2\2\22R\3\2\2\2\24V\3\2\2\2\26d\3\2\2\2\30p\3\2\2\2\32x\3\2\2\2\34|\3"+
		"\2\2\2\36\u0099\3\2\2\2 \u009b\3\2\2\2\"\u00a6\3\2\2\2$\u00a8\3\2\2\2"+
		"&\u00aa\3\2\2\2(\u00ac\3\2\2\2*\u00b1\3\2\2\2,-\7/\2\2-.\7/\2\2./\7/\2"+
		"\2/\60\3\2\2\2\60\61\b\2\2\2\61\5\3\2\2\2\62\63\7$\2\2\63\64\7$\2\2\64"+
		"\65\7$\2\2\65\7\3\2\2\2\66\67\7/\2\2\67\t\3\2\2\289\7<\2\29\13\3\2\2\2"+
		":B\5(\24\2;B\5*\25\2<>\5&\23\2=<\3\2\2\2>?\3\2\2\2?=\3\2\2\2?@\3\2\2\2"+
		"@B\3\2\2\2A:\3\2\2\2A;\3\2\2\2A=\3\2\2\2B\r\3\2\2\2CH\5\6\3\2DG\n\2\2"+
		"\2EG\5\36\17\2FD\3\2\2\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2"+
		"\2\2JH\3\2\2\2KL\5\6\3\2L\17\3\2\2\2MO\7\17\2\2NM\3\2\2\2NO\3\2\2\2OP"+
		"\3\2\2\2PQ\7\f\2\2Q\21\3\2\2\2RS\t\3\2\2ST\3\2\2\2TU\b\t\3\2U\23\3\2\2"+
		"\2VW\7\61\2\2WX\7,\2\2X\\\3\2\2\2Y[\13\2\2\2ZY\3\2\2\2[^\3\2\2\2\\]\3"+
		"\2\2\2\\Z\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_`\7,\2\2`a\7\61\2\2ab\3\2\2\2b"+
		"c\b\n\3\2c\25\3\2\2\2de\7\61\2\2ef\7\61\2\2fj\3\2\2\2gi\n\4\2\2hg\3\2"+
		"\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\b\13\3\2n\27"+
		"\3\2\2\2oq\t\4\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2st\3\2\2\2t"+
		"u\b\f\4\2uv\b\f\5\2v\31\3\2\2\2wy\n\4\2\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2"+
		"\2z{\3\2\2\2{\33\3\2\2\2|\u0081\5&\23\2}\u0080\5&\23\2~\u0080\5$\22\2"+
		"\177}\3\2\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\35\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\7^\2\2"+
		"\u0085\u009a\t\5\2\2\u0086\u008b\7^\2\2\u0087\u0089\t\6\2\2\u0088\u0087"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\t\7\2\2\u008b"+
		"\u0088\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u009a\t\7"+
		"\2\2\u008e\u0090\7^\2\2\u008f\u0091\7w\2\2\u0090\u008f\3\2\2\2\u0091\u0092"+
		"\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0095\5\"\21\2\u0095\u0096\5\"\21\2\u0096\u0097\5\"\21\2\u0097\u0098"+
		"\5\"\21\2\u0098\u009a\3\2\2\2\u0099\u0084\3\2\2\2\u0099\u0086\3\2\2\2"+
		"\u0099\u008e\3\2\2\2\u009a\37\3\2\2\2\u009b\u00a4\5\"\21\2\u009c\u009f"+
		"\5\"\21\2\u009d\u009f\7a\2\2\u009e\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a5\5\"\21\2\u00a4\u00a0\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5!\3\2\2\2\u00a6\u00a7\t\b\2\2\u00a7#\3\2\2\2\u00a8"+
		"\u00a9\t\t\2\2\u00a9%\3\2\2\2\u00aa\u00ab\t\n\2\2\u00ab\'\3\2\2\2\u00ac"+
		"\u00ad\t\13\2\2\u00ad\u00ae\t\f\2\2\u00ae\u00af\t\r\2\2\u00af\u00b0\t"+
		"\16\2\2\u00b0)\3\2\2\2\u00b1\u00b2\t\17\2\2\u00b2\u00b3\t\20\2\2\u00b3"+
		"\u00b4\t\16\2\2\u00b4+\3\2\2\2\26\2\3?AFHN\\jrz\177\u0081\u0088\u008b"+
		"\u0092\u0099\u009e\u00a0\u00a4\6\7\3\2\2\3\2\6\2\2\t\t\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}