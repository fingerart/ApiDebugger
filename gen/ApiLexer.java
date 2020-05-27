// Generated from io/chengguo/api/debugger/lang/ApiLexer.g4 by ANTLR 4.8
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
		FlagTitle=1, FlagDes=2, Sub=3, Colon=4, FlagDes2=5, Method=6, NL=7, WS=8, 
		COMMENT=9, LINE_COMMENT=10, LineText=11, Text=12;
	public static final int
		ModeTitle=1, ModeDescription=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "ModeTitle", "ModeDescription"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"FlagTitle", "FlagDes", "Sub", "Colon", "FlagDes2", "Method", "NL", "WS", 
			"COMMENT", "LINE_COMMENT", "ModeTitleClose", "LineText", "ModeDescriptionClose", 
			"Text", "EscapeSequence", "HexDigits", "HexDigit", "DIGIT", "LETTER", 
			"POST", "GET"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'---'", null, "'-'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FlagTitle", "FlagDes", "Sub", "Colon", "FlagDes2", "Method", "NL", 
			"WS", "COMMENT", "LINE_COMMENT", "LineText", "Text"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16\u00b4\b\1\b\1"+
		"\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4"+
		"\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t"+
		"\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\5\7F\n\7\3\b\5\bI\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\nU"+
		"\n\n\f\n\16\nX\13\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13c\n\13"+
		"\f\13\16\13f\13\13\3\13\3\13\3\f\6\fk\n\f\r\f\16\fl\3\f\3\f\3\f\3\r\6"+
		"\rs\n\r\r\r\16\rt\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\6\17\u0080"+
		"\n\17\r\17\16\17\u0081\3\20\3\20\3\20\3\20\5\20\u0088\n\20\3\20\5\20\u008b"+
		"\n\20\3\20\3\20\3\20\6\20\u0090\n\20\r\20\16\20\u0091\3\20\3\20\3\20\3"+
		"\20\3\20\5\20\u0099\n\20\3\21\3\21\3\21\7\21\u009e\n\21\f\21\16\21\u00a1"+
		"\13\21\3\21\5\21\u00a4\n\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\25\3\25\3\25\3\26\3\26\3\26\3\26\3V\2\27\5\3\7\4\t\5\13\6\r\7\17\b\21"+
		"\t\23\n\25\13\27\f\31\2\33\r\35\2\37\16!\2#\2%\2\'\2)\2+\2-\2\5\2\3\4"+
		"\21\5\2\13\f\17\17\"\"\4\2\f\f\17\17\6\2\n\13\16\16$$^^\n\2$$))^^ddhh"+
		"ppttvv\3\2\62\65\3\2\629\5\2\62;CHch\3\2\62;\4\2C\\c|\4\2RRrr\4\2QQqq"+
		"\4\2UUuu\4\2VVvv\4\2IIii\4\2GGgg\2\u00ba\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\3\31\3\2\2\2\3\33\3\2\2\2\4\35\3\2\2\2\4\37"+
		"\3\2\2\2\5/\3\2\2\2\7\65\3\2\2\2\t;\3\2\2\2\13=\3\2\2\2\r?\3\2\2\2\17"+
		"E\3\2\2\2\21H\3\2\2\2\23L\3\2\2\2\25P\3\2\2\2\27^\3\2\2\2\31j\3\2\2\2"+
		"\33r\3\2\2\2\35v\3\2\2\2\37\177\3\2\2\2!\u0098\3\2\2\2#\u009a\3\2\2\2"+
		"%\u00a5\3\2\2\2\'\u00a7\3\2\2\2)\u00a9\3\2\2\2+\u00ab\3\2\2\2-\u00b0\3"+
		"\2\2\2/\60\7/\2\2\60\61\7/\2\2\61\62\7/\2\2\62\63\3\2\2\2\63\64\b\2\2"+
		"\2\64\6\3\2\2\2\65\66\7$\2\2\66\67\7$\2\2\678\7$\2\289\3\2\2\29:\b\3\3"+
		"\2:\b\3\2\2\2;<\7/\2\2<\n\3\2\2\2=>\7<\2\2>\f\3\2\2\2?@\7$\2\2@A\7$\2"+
		"\2AB\7$\2\2B\16\3\2\2\2CF\5+\25\2DF\5-\26\2EC\3\2\2\2ED\3\2\2\2F\20\3"+
		"\2\2\2GI\7\17\2\2HG\3\2\2\2HI\3\2\2\2IJ\3\2\2\2JK\7\f\2\2K\22\3\2\2\2"+
		"LM\t\2\2\2MN\3\2\2\2NO\b\t\4\2O\24\3\2\2\2PQ\7\61\2\2QR\7,\2\2RV\3\2\2"+
		"\2SU\13\2\2\2TS\3\2\2\2UX\3\2\2\2VW\3\2\2\2VT\3\2\2\2WY\3\2\2\2XV\3\2"+
		"\2\2YZ\7,\2\2Z[\7\61\2\2[\\\3\2\2\2\\]\b\n\4\2]\26\3\2\2\2^_\7\61\2\2"+
		"_`\7\61\2\2`d\3\2\2\2ac\n\3\2\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2"+
		"\2eg\3\2\2\2fd\3\2\2\2gh\b\13\4\2h\30\3\2\2\2ik\t\3\2\2ji\3\2\2\2kl\3"+
		"\2\2\2lj\3\2\2\2lm\3\2\2\2mn\3\2\2\2no\b\f\5\2op\b\f\6\2p\32\3\2\2\2q"+
		"s\n\3\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\34\3\2\2\2vw\7$\2\2"+
		"wx\7$\2\2xy\7$\2\2yz\3\2\2\2z{\b\16\5\2{|\b\16\7\2|\36\3\2\2\2}\u0080"+
		"\n\4\2\2~\u0080\5!\20\2\177}\3\2\2\2\177~\3\2\2\2\u0080\u0081\3\2\2\2"+
		"\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082 \3\2\2\2\u0083\u0084\7^\2"+
		"\2\u0084\u0099\t\5\2\2\u0085\u008a\7^\2\2\u0086\u0088\t\6\2\2\u0087\u0086"+
		"\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\t\7\2\2\u008a"+
		"\u0087\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u0099\t\7"+
		"\2\2\u008d\u008f\7^\2\2\u008e\u0090\7w\2\2\u008f\u008e\3\2\2\2\u0090\u0091"+
		"\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u0094\5%\22\2\u0094\u0095\5%\22\2\u0095\u0096\5%\22\2\u0096\u0097\5%"+
		"\22\2\u0097\u0099\3\2\2\2\u0098\u0083\3\2\2\2\u0098\u0085\3\2\2\2\u0098"+
		"\u008d\3\2\2\2\u0099\"\3\2\2\2\u009a\u00a3\5%\22\2\u009b\u009e\5%\22\2"+
		"\u009c\u009e\7a\2\2\u009d\u009b\3\2\2\2\u009d\u009c\3\2\2\2\u009e\u00a1"+
		"\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a2\u00a4\5%\22\2\u00a3\u009f\3\2\2\2\u00a3\u00a4\3\2"+
		"\2\2\u00a4$\3\2\2\2\u00a5\u00a6\t\b\2\2\u00a6&\3\2\2\2\u00a7\u00a8\t\t"+
		"\2\2\u00a8(\3\2\2\2\u00a9\u00aa\t\n\2\2\u00aa*\3\2\2\2\u00ab\u00ac\t\13"+
		"\2\2\u00ac\u00ad\t\f\2\2\u00ad\u00ae\t\r\2\2\u00ae\u00af\t\16\2\2\u00af"+
		",\3\2\2\2\u00b0\u00b1\t\17\2\2\u00b1\u00b2\t\20\2\2\u00b2\u00b3\t\16\2"+
		"\2\u00b3.\3\2\2\2\24\2\3\4EHVdlt\177\u0081\u0087\u008a\u0091\u0098\u009d"+
		"\u009f\u00a3\b\7\3\2\7\4\2\2\3\2\6\2\2\t\t\2\t\7\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}