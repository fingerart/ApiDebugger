// Generated from /Users/fingerart/Workspace/Other/ApiDebugger/src/main/antlr/io/chengguo/api/debugger/lang/parser/ApiLexer.g4 by ANTLR 4.8
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
		GET=1, HEAD=2, POST=3, PUT=4, DELETE=5, CONNECT=6, OPTIONS=7, TRACE=8, 
		LCURL=9, RCURL=10, DOLLAR=11, Slash=12, DOT=13, ASSIGN=14, COLON=15, QUESTION=16, 
		BITAND=17, PROTCOL=18, WELL=19, HexUri=20, Digits=21, StringLiteral=22, 
		LF=23, WS=24, COMMENT=25, LINE_COMMENT=26, Identifier=27, LineStrExprStart=28;
	public static final int
		StringExpression=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "StringExpression"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GET", "HEAD", "POST", "PUT", "DELETE", "CONNECT", "OPTIONS", "TRACE", 
			"LCURL", "RCURL", "DOLLAR", "Slash", "DOT", "ASSIGN", "COLON", "QUESTION", 
			"BITAND", "PROTCOL", "WELL", "HexUri", "Digits", "StringLiteral", "LF", 
			"WS", "COMMENT", "LINE_COMMENT", "Identifier", "LineStrExprStart", "Digit", 
			"HexDigit", "EscapeSequence", "LetterOrDigit", "Letter", "StrExpr_RCURL"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'GET'", "'HEAD'", "'POST'", "'PUT'", "'DELETE'", "'CONNECT'", 
			"'OPTIONS'", "'TRACE'", "'{'", "'}'", "'$'", "'/'", "'.'", "'='", "':'", 
			"'?'", "'&'", "'://'", "'#'", null, null, null, null, null, null, null, 
			null, "'${'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "GET", "HEAD", "POST", "PUT", "DELETE", "CONNECT", "OPTIONS", "TRACE", 
			"LCURL", "RCURL", "DOLLAR", "Slash", "DOT", "ASSIGN", "COLON", "QUESTION", 
			"BITAND", "PROTCOL", "WELL", "HexUri", "Digits", "StringLiteral", "LF", 
			"WS", "COMMENT", "LINE_COMMENT", "Identifier", "LineStrExprStart"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\36\u0101\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4"+
		"\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4"+
		" \t \4!\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\6\25"+
		"\u0094\n\25\r\25\16\25\u0095\3\26\6\26\u0099\n\26\r\26\16\26\u009a\3\27"+
		"\3\27\3\27\7\27\u00a0\n\27\f\27\16\27\u00a3\13\27\3\27\3\27\3\30\6\30"+
		"\u00a8\n\30\r\30\16\30\u00a9\3\31\6\31\u00ad\n\31\r\31\16\31\u00ae\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\7\32\u00b7\n\32\f\32\16\32\u00ba\13\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\7\33\u00c5\n\33\f\33\16\33\u00c8"+
		"\13\33\3\33\3\33\3\34\3\34\7\34\u00ce\n\34\f\34\16\34\u00d1\13\34\3\35"+
		"\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3 \5 \u00e0\n \3 \5"+
		" \u00e3\n \3 \3 \3 \6 \u00e8\n \r \16 \u00e9\3 \3 \3 \3 \3 \5 \u00f1\n"+
		" \3!\3!\5!\u00f5\n!\3\"\3\"\3\"\3\"\5\"\u00fb\n\"\3#\3#\3#\3#\3#\3\u00b8"+
		"\2$\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36"+
		"\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64\33\66\348\35:\36<"+
		"\2>\2@\2B\2D\2F\2\4\2\3\16\6\2\f\f\17\17$$^^\4\2\f\f\17\17\5\2\13\f\16"+
		"\17\"\"\3\2\62;\5\2\62;CHch\n\2$$))^^ddhhppttvv\3\2\62\65\3\2\629\6\2"+
		"&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\2\u010b"+
		"\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2"+
		"\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2"+
		"\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2"+
		"\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2"+
		"\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\3F\3\2"+
		"\2\2\4H\3\2\2\2\6L\3\2\2\2\bQ\3\2\2\2\nV\3\2\2\2\fZ\3\2\2\2\16a\3\2\2"+
		"\2\20i\3\2\2\2\22q\3\2\2\2\24w\3\2\2\2\26y\3\2\2\2\30{\3\2\2\2\32}\3\2"+
		"\2\2\34\177\3\2\2\2\36\u0081\3\2\2\2 \u0083\3\2\2\2\"\u0085\3\2\2\2$\u0087"+
		"\3\2\2\2&\u0089\3\2\2\2(\u008d\3\2\2\2*\u0093\3\2\2\2,\u0098\3\2\2\2."+
		"\u009c\3\2\2\2\60\u00a7\3\2\2\2\62\u00ac\3\2\2\2\64\u00b2\3\2\2\2\66\u00c0"+
		"\3\2\2\28\u00cb\3\2\2\2:\u00d2\3\2\2\2<\u00d7\3\2\2\2>\u00d9\3\2\2\2@"+
		"\u00f0\3\2\2\2B\u00f4\3\2\2\2D\u00fa\3\2\2\2F\u00fc\3\2\2\2HI\7I\2\2I"+
		"J\7G\2\2JK\7V\2\2K\5\3\2\2\2LM\7J\2\2MN\7G\2\2NO\7C\2\2OP\7F\2\2P\7\3"+
		"\2\2\2QR\7R\2\2RS\7Q\2\2ST\7U\2\2TU\7V\2\2U\t\3\2\2\2VW\7R\2\2WX\7W\2"+
		"\2XY\7V\2\2Y\13\3\2\2\2Z[\7F\2\2[\\\7G\2\2\\]\7N\2\2]^\7G\2\2^_\7V\2\2"+
		"_`\7G\2\2`\r\3\2\2\2ab\7E\2\2bc\7Q\2\2cd\7P\2\2de\7P\2\2ef\7G\2\2fg\7"+
		"E\2\2gh\7V\2\2h\17\3\2\2\2ij\7Q\2\2jk\7R\2\2kl\7V\2\2lm\7K\2\2mn\7Q\2"+
		"\2no\7P\2\2op\7U\2\2p\21\3\2\2\2qr\7V\2\2rs\7T\2\2st\7C\2\2tu\7E\2\2u"+
		"v\7G\2\2v\23\3\2\2\2wx\7}\2\2x\25\3\2\2\2yz\7\177\2\2z\27\3\2\2\2{|\7"+
		"&\2\2|\31\3\2\2\2}~\7\61\2\2~\33\3\2\2\2\177\u0080\7\60\2\2\u0080\35\3"+
		"\2\2\2\u0081\u0082\7?\2\2\u0082\37\3\2\2\2\u0083\u0084\7<\2\2\u0084!\3"+
		"\2\2\2\u0085\u0086\7A\2\2\u0086#\3\2\2\2\u0087\u0088\7(\2\2\u0088%\3\2"+
		"\2\2\u0089\u008a\7<\2\2\u008a\u008b\7\61\2\2\u008b\u008c\7\61\2\2\u008c"+
		"\'\3\2\2\2\u008d\u008e\7%\2\2\u008e)\3\2\2\2\u008f\u0090\7\'\2\2\u0090"+
		"\u0091\5>\37\2\u0091\u0092\5>\37\2\u0092\u0094\3\2\2\2\u0093\u008f\3\2"+
		"\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"+\3\2\2\2\u0097\u0099\5<\36\2\u0098\u0097\3\2\2\2\u0099\u009a\3\2\2\2"+
		"\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b-\3\2\2\2\u009c\u00a1\7"+
		"$\2\2\u009d\u00a0\n\2\2\2\u009e\u00a0\5@ \2\u009f\u009d\3\2\2\2\u009f"+
		"\u009e\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2"+
		"\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a5\7$\2\2\u00a5"+
		"/\3\2\2\2\u00a6\u00a8\t\3\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2"+
		"\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\61\3\2\2\2\u00ab\u00ad"+
		"\t\4\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\b\31\2\2\u00b1\63\3\2\2"+
		"\2\u00b2\u00b3\7\61\2\2\u00b3\u00b4\7,\2\2\u00b4\u00b8\3\2\2\2\u00b5\u00b7"+
		"\13\2\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b9\3\2\2\2"+
		"\u00b8\u00b6\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00bc"+
		"\7,\2\2\u00bc\u00bd\7\61\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\b\32\2\2"+
		"\u00bf\65\3\2\2\2\u00c0\u00c1\7\61\2\2\u00c1\u00c2\7\61\2\2\u00c2\u00c6"+
		"\3\2\2\2\u00c3\u00c5\n\3\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6"+
		"\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c6\3\2"+
		"\2\2\u00c9\u00ca\b\33\2\2\u00ca\67\3\2\2\2\u00cb\u00cf\5D\"\2\u00cc\u00ce"+
		"\5B!\2\u00cd\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d09\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d3\7&\2\2\u00d3"+
		"\u00d4\7}\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\b\35\3\2\u00d6;\3\2\2\2"+
		"\u00d7\u00d8\t\5\2\2\u00d8=\3\2\2\2\u00d9\u00da\t\6\2\2\u00da?\3\2\2\2"+
		"\u00db\u00dc\7^\2\2\u00dc\u00f1\t\7\2\2\u00dd\u00e2\7^\2\2\u00de\u00e0"+
		"\t\b\2\2\u00df\u00de\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e3\t\t\2\2\u00e2\u00df\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\3\2"+
		"\2\2\u00e4\u00f1\t\t\2\2\u00e5\u00e7\7^\2\2\u00e6\u00e8\7w\2\2\u00e7\u00e6"+
		"\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\u00eb\3\2\2\2\u00eb\u00ec\5>\37\2\u00ec\u00ed\5>\37\2\u00ed\u00ee\5>"+
		"\37\2\u00ee\u00ef\5>\37\2\u00ef\u00f1\3\2\2\2\u00f0\u00db\3\2\2\2\u00f0"+
		"\u00dd\3\2\2\2\u00f0\u00e5\3\2\2\2\u00f1A\3\2\2\2\u00f2\u00f5\5D\"\2\u00f3"+
		"\u00f5\t\5\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f3\3\2\2\2\u00f5C\3\2\2\2"+
		"\u00f6\u00fb\t\n\2\2\u00f7\u00fb\n\13\2\2\u00f8\u00f9\t\f\2\2\u00f9\u00fb"+
		"\t\r\2\2\u00fa\u00f6\3\2\2\2\u00fa\u00f7\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb"+
		"E\3\2\2\2\u00fc\u00fd\5\26\13\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\b#\4\2"+
		"\u00ff\u0100\b#\5\2\u0100G\3\2\2\2\23\2\3\u0095\u009a\u009f\u00a1\u00a9"+
		"\u00ae\u00b8\u00c6\u00cf\u00df\u00e2\u00e9\u00f0\u00f4\u00fa\6\2\3\2\7"+
		"\3\2\6\2\2\t\f\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}