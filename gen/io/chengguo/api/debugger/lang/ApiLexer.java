// Generated from Api.g4 by ANTLR 4.8
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
		T__0=1, T__1=2, T__2=3, T__3=4, Keyword=5, WS=6, COMMENT=7, LINE_COMMENT=8;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "Keyword", "WS", "COMMENT", "LINE_COMMENT", 
			"EscapeSequence", "HexDigits", "HexDigit", "DIGIT", "LETTER"
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
			null, null, null, null, null, "Keyword", "WS", "COMMENT", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "Api.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\nt\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\6\6,\n\6\r\6\16\6-\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\7"+
		"\b8\n\b\f\b\16\b;\13\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\7\tF\n\t\f"+
		"\t\16\tI\13\t\3\t\3\t\3\n\3\n\3\n\3\n\5\nQ\n\n\3\n\5\nT\n\n\3\n\3\n\3"+
		"\n\6\nY\n\n\r\n\16\nZ\3\n\3\n\3\n\3\n\3\n\5\nb\n\n\3\13\3\13\3\13\7\13"+
		"g\n\13\f\13\16\13j\13\13\3\13\5\13m\n\13\3\f\3\f\3\r\3\r\3\16\3\16\39"+
		"\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\2\25\2\27\2\31\2\33\2\3\2"+
		"\13\t\2\n\f\16\17\"\"$$//<<^^\5\2\13\f\17\17\"\"\4\2\f\f\17\17\n\2$$)"+
		")^^ddhhppttvv\3\2\62\65\3\2\629\5\2\62;CHch\3\2\62;\4\2C\\c|\2z\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\3\35\3\2\2\2\5!\3\2\2\2\7%\3\2\2\2\t\'\3\2\2"+
		"\2\13+\3\2\2\2\r/\3\2\2\2\17\63\3\2\2\2\21A\3\2\2\2\23a\3\2\2\2\25c\3"+
		"\2\2\2\27n\3\2\2\2\31p\3\2\2\2\33r\3\2\2\2\35\36\7/\2\2\36\37\7/\2\2\37"+
		" \7/\2\2 \4\3\2\2\2!\"\7$\2\2\"#\7$\2\2#$\7$\2\2$\6\3\2\2\2%&\7/\2\2&"+
		"\b\3\2\2\2\'(\7<\2\2(\n\3\2\2\2),\n\2\2\2*,\5\23\n\2+)\3\2\2\2+*\3\2\2"+
		"\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\f\3\2\2\2/\60\t\3\2\2\60\61\3\2\2\2"+
		"\61\62\b\7\2\2\62\16\3\2\2\2\63\64\7\61\2\2\64\65\7,\2\2\659\3\2\2\2\66"+
		"8\13\2\2\2\67\66\3\2\2\28;\3\2\2\29:\3\2\2\29\67\3\2\2\2:<\3\2\2\2;9\3"+
		"\2\2\2<=\7,\2\2=>\7\61\2\2>?\3\2\2\2?@\b\b\2\2@\20\3\2\2\2AB\7\61\2\2"+
		"BC\7\61\2\2CG\3\2\2\2DF\n\4\2\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2"+
		"\2HJ\3\2\2\2IG\3\2\2\2JK\b\t\2\2K\22\3\2\2\2LM\7^\2\2Mb\t\5\2\2NS\7^\2"+
		"\2OQ\t\6\2\2PO\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RT\t\7\2\2SP\3\2\2\2ST\3\2\2"+
		"\2TU\3\2\2\2Ub\t\7\2\2VX\7^\2\2WY\7w\2\2XW\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2"+
		"Z[\3\2\2\2[\\\3\2\2\2\\]\5\27\f\2]^\5\27\f\2^_\5\27\f\2_`\5\27\f\2`b\3"+
		"\2\2\2aL\3\2\2\2aN\3\2\2\2aV\3\2\2\2b\24\3\2\2\2cl\5\27\f\2dg\5\27\f\2"+
		"eg\7a\2\2fd\3\2\2\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2"+
		"jh\3\2\2\2km\5\27\f\2lh\3\2\2\2lm\3\2\2\2m\26\3\2\2\2no\t\b\2\2o\30\3"+
		"\2\2\2pq\t\t\2\2q\32\3\2\2\2rs\t\n\2\2s\34\3\2\2\2\16\2+-9GPSZafhl\3\2"+
		"\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}