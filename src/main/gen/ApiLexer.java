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
		BITAND=17, PROTCOL=18, WELL=19, AT=20, HexUri=21, Digits=22, OWS=23, HTAB=24, 
		SP=25, NL=26, WS=27, COMMENT=28, LINE_COMMENT=29, Identifier=30, LineStrExprStart=31, 
		InfoStart=32, StringLiteral=33, StringContent=34, INFO_CLOSE=35, TITLE_CONTENT=36;
	public static final int
		InfoMode=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "InfoMode"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GET", "HEAD", "POST", "PUT", "DELETE", "CONNECT", "OPTIONS", "TRACE", 
			"LCURL", "RCURL", "DOLLAR", "Slash", "DOT", "ASSIGN", "COLON", "QUESTION", 
			"BITAND", "PROTCOL", "WELL", "AT", "HexUri", "Digits", "OWS", "HTAB", 
			"SP", "NL", "WS", "COMMENT", "LINE_COMMENT", "Identifier", "LineStrExprStart", 
			"InfoStart", "StringLiteral", "StringContent", "Digit", "HexDigit", "EscapeSequence", 
			"LetterOrDigit", "Letter", "INFO_CLOSE", "TITLE_CONTENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'GET'", "'HEAD'", "'POST'", "'PUT'", "'DELETE'", "'CONNECT'", 
			"'OPTIONS'", "'TRACE'", "'{'", "'}'", "'$'", "'/'", "'.'", "'='", "':'", 
			"'?'", "'&'", "'://'", "'#'", "'@'", null, null, null, "'\t'", "' '", 
			null, null, null, null, null, "'${'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "GET", "HEAD", "POST", "PUT", "DELETE", "CONNECT", "OPTIONS", "TRACE", 
			"LCURL", "RCURL", "DOLLAR", "Slash", "DOT", "ASSIGN", "COLON", "QUESTION", 
			"BITAND", "PROTCOL", "WELL", "AT", "HexUri", "Digits", "OWS", "HTAB", 
			"SP", "NL", "WS", "COMMENT", "LINE_COMMENT", "Identifier", "LineStrExprStart", 
			"InfoStart", "StringLiteral", "StringContent", "INFO_CLOSE", "TITLE_CONTENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u0125\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\6\26\u00a4\n\26\r\26"+
		"\16\26\u00a5\3\27\6\27\u00a9\n\27\r\27\16\27\u00aa\3\30\3\30\5\30\u00af"+
		"\n\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\5\33\u00b8\n\33\3\34\6\34\u00bb"+
		"\n\34\r\34\16\34\u00bc\3\34\3\34\3\35\3\35\3\35\3\35\7\35\u00c5\n\35\f"+
		"\35\16\35\u00c8\13\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\7\36"+
		"\u00d3\n\36\f\36\16\36\u00d6\13\36\3\36\3\36\3\37\3\37\7\37\u00dc\n\37"+
		"\f\37\16\37\u00df\13\37\3 \3 \3 \3!\3!\3!\3!\3\"\3\"\7\"\u00ea\n\"\f\""+
		"\16\"\u00ed\13\"\3\"\3\"\3#\3#\5#\u00f3\n#\3$\3$\3%\3%\3&\3&\3&\3&\5&"+
		"\u00fd\n&\3&\5&\u0100\n&\3&\3&\3&\6&\u0105\n&\r&\16&\u0106\3&\3&\3&\3"+
		"&\3&\5&\u010e\n&\3\'\3\'\5\'\u0112\n\'\3(\3(\3(\3(\5(\u0118\n(\3)\3)\3"+
		")\3)\3)\3)\3*\3*\6*\u0122\n*\r*\16*\u0123\3\u00c6\2+\4\3\6\4\b\5\n\6\f"+
		"\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36\20 \21\"\22$\23&\24("+
		"\25*\26,\27.\30\60\31\62\32\64\33\66\348\35:\36<\37> @!B\"D#F$H\2J\2L"+
		"\2N\2P\2R%T&\4\2\3\17\4\2\f\f\16\17\4\2\f\f\17\17\6\2\f\f\17\17$$^^\3"+
		"\2\62;\5\2\62;CHch\n\2$$))^^ddhhppttvv\3\2\62\65\3\2\629\6\2&&C\\aac|"+
		"\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\3\2%%\2\u0132"+
		"\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2"+
		"\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2"+
		"\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2"+
		"\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2"+
		"\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2"+
		"\2\2\2>\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\3R\3\2\2\2"+
		"\3T\3\2\2\2\4V\3\2\2\2\6Z\3\2\2\2\b_\3\2\2\2\nd\3\2\2\2\fh\3\2\2\2\16"+
		"o\3\2\2\2\20w\3\2\2\2\22\177\3\2\2\2\24\u0085\3\2\2\2\26\u0087\3\2\2\2"+
		"\30\u0089\3\2\2\2\32\u008b\3\2\2\2\34\u008d\3\2\2\2\36\u008f\3\2\2\2 "+
		"\u0091\3\2\2\2\"\u0093\3\2\2\2$\u0095\3\2\2\2&\u0097\3\2\2\2(\u009b\3"+
		"\2\2\2*\u009d\3\2\2\2,\u00a3\3\2\2\2.\u00a8\3\2\2\2\60\u00ae\3\2\2\2\62"+
		"\u00b0\3\2\2\2\64\u00b2\3\2\2\2\66\u00b7\3\2\2\28\u00ba\3\2\2\2:\u00c0"+
		"\3\2\2\2<\u00ce\3\2\2\2>\u00d9\3\2\2\2@\u00e0\3\2\2\2B\u00e3\3\2\2\2D"+
		"\u00e7\3\2\2\2F\u00f2\3\2\2\2H\u00f4\3\2\2\2J\u00f6\3\2\2\2L\u010d\3\2"+
		"\2\2N\u0111\3\2\2\2P\u0117\3\2\2\2R\u0119\3\2\2\2T\u0121\3\2\2\2VW\7I"+
		"\2\2WX\7G\2\2XY\7V\2\2Y\5\3\2\2\2Z[\7J\2\2[\\\7G\2\2\\]\7C\2\2]^\7F\2"+
		"\2^\7\3\2\2\2_`\7R\2\2`a\7Q\2\2ab\7U\2\2bc\7V\2\2c\t\3\2\2\2de\7R\2\2"+
		"ef\7W\2\2fg\7V\2\2g\13\3\2\2\2hi\7F\2\2ij\7G\2\2jk\7N\2\2kl\7G\2\2lm\7"+
		"V\2\2mn\7G\2\2n\r\3\2\2\2op\7E\2\2pq\7Q\2\2qr\7P\2\2rs\7P\2\2st\7G\2\2"+
		"tu\7E\2\2uv\7V\2\2v\17\3\2\2\2wx\7Q\2\2xy\7R\2\2yz\7V\2\2z{\7K\2\2{|\7"+
		"Q\2\2|}\7P\2\2}~\7U\2\2~\21\3\2\2\2\177\u0080\7V\2\2\u0080\u0081\7T\2"+
		"\2\u0081\u0082\7C\2\2\u0082\u0083\7E\2\2\u0083\u0084\7G\2\2\u0084\23\3"+
		"\2\2\2\u0085\u0086\7}\2\2\u0086\25\3\2\2\2\u0087\u0088\7\177\2\2\u0088"+
		"\27\3\2\2\2\u0089\u008a\7&\2\2\u008a\31\3\2\2\2\u008b\u008c\7\61\2\2\u008c"+
		"\33\3\2\2\2\u008d\u008e\7\60\2\2\u008e\35\3\2\2\2\u008f\u0090\7?\2\2\u0090"+
		"\37\3\2\2\2\u0091\u0092\7<\2\2\u0092!\3\2\2\2\u0093\u0094\7A\2\2\u0094"+
		"#\3\2\2\2\u0095\u0096\7(\2\2\u0096%\3\2\2\2\u0097\u0098\7<\2\2\u0098\u0099"+
		"\7\61\2\2\u0099\u009a\7\61\2\2\u009a\'\3\2\2\2\u009b\u009c\7%\2\2\u009c"+
		")\3\2\2\2\u009d\u009e\7B\2\2\u009e+\3\2\2\2\u009f\u00a0\7\'\2\2\u00a0"+
		"\u00a1\5J%\2\u00a1\u00a2\5J%\2\u00a2\u00a4\3\2\2\2\u00a3\u009f\3\2\2\2"+
		"\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6-\3"+
		"\2\2\2\u00a7\u00a9\5H$\2\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab/\3\2\2\2\u00ac\u00af\5\64\32"+
		"\2\u00ad\u00af\5\62\31\2\u00ae\u00ac\3\2\2\2\u00ae\u00ad\3\2\2\2\u00af"+
		"\61\3\2\2\2\u00b0\u00b1\7\13\2\2\u00b1\63\3\2\2\2\u00b2\u00b3\7\"\2\2"+
		"\u00b3\65\3\2\2\2\u00b4\u00b8\7\f\2\2\u00b5\u00b6\7\17\2\2\u00b6\u00b8"+
		"\7\f\2\2\u00b7\u00b4\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\67\3\2\2\2\u00b9"+
		"\u00bb\t\2\2\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2"+
		"\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\b\34\2\2\u00bf"+
		"9\3\2\2\2\u00c0\u00c1\7\61\2\2\u00c1\u00c2\7,\2\2\u00c2\u00c6\3\2\2\2"+
		"\u00c3\u00c5\13\2\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c7"+
		"\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9"+
		"\u00ca\7,\2\2\u00ca\u00cb\7\61\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\b\35"+
		"\2\2\u00cd;\3\2\2\2\u00ce\u00cf\7\61\2\2\u00cf\u00d0\7\61\2\2\u00d0\u00d4"+
		"\3\2\2\2\u00d1\u00d3\n\3\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4"+
		"\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d4\3\2"+
		"\2\2\u00d7\u00d8\b\36\2\2\u00d8=\3\2\2\2\u00d9\u00dd\5P(\2\u00da\u00dc"+
		"\5N\'\2\u00db\u00da\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de?\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e1\7&\2\2\u00e1"+
		"\u00e2\7}\2\2\u00e2A\3\2\2\2\u00e3\u00e4\7%\2\2\u00e4\u00e5\7%\2\2\u00e5"+
		"\u00e6\7%\2\2\u00e6C\3\2\2\2\u00e7\u00eb\7$\2\2\u00e8\u00ea\5F#\2\u00e9"+
		"\u00e8\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2"+
		"\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00ef\7$\2\2\u00ef"+
		"E\3\2\2\2\u00f0\u00f3\n\4\2\2\u00f1\u00f3\5L&\2\u00f2\u00f0\3\2\2\2\u00f2"+
		"\u00f1\3\2\2\2\u00f3G\3\2\2\2\u00f4\u00f5\t\5\2\2\u00f5I\3\2\2\2\u00f6"+
		"\u00f7\t\6\2\2\u00f7K\3\2\2\2\u00f8\u00f9\7^\2\2\u00f9\u010e\t\7\2\2\u00fa"+
		"\u00ff\7^\2\2\u00fb\u00fd\t\b\2\2\u00fc\u00fb\3\2\2\2\u00fc\u00fd\3\2"+
		"\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\t\t\2\2\u00ff\u00fc\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u010e\t\t\2\2\u0102\u0104\7^"+
		"\2\2\u0103\u0105\7w\2\2\u0104\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109\5J"+
		"%\2\u0109\u010a\5J%\2\u010a\u010b\5J%\2\u010b\u010c\5J%\2\u010c\u010e"+
		"\3\2\2\2\u010d\u00f8\3\2\2\2\u010d\u00fa\3\2\2\2\u010d\u0102\3\2\2\2\u010e"+
		"M\3\2\2\2\u010f\u0112\5P(\2\u0110\u0112\t\5\2\2\u0111\u010f\3\2\2\2\u0111"+
		"\u0110\3\2\2\2\u0112O\3\2\2\2\u0113\u0118\t\n\2\2\u0114\u0118\n\13\2\2"+
		"\u0115\u0116\t\f\2\2\u0116\u0118\t\r\2\2\u0117\u0113\3\2\2\2\u0117\u0114"+
		"\3\2\2\2\u0117\u0115\3\2\2\2\u0118Q\3\2\2\2\u0119\u011a\7%\2\2\u011a\u011b"+
		"\7%\2\2\u011b\u011c\7%\2\2\u011c\u011d\3\2\2\2\u011d\u011e\b)\3\2\u011e"+
		"S\3\2\2\2\u011f\u0122\n\16\2\2\u0120\u0122\5F#\2\u0121\u011f\3\2\2\2\u0121"+
		"\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2"+
		"\2\2\u0124U\3\2\2\2\26\2\3\u00a5\u00aa\u00ae\u00b7\u00bc\u00c6\u00d4\u00dd"+
		"\u00eb\u00f2\u00fc\u00ff\u0106\u010d\u0111\u0117\u0121\u0123\4\2\3\2\6"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}