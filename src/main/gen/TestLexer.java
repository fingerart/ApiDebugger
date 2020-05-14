// Generated from /Users/fingerart/Workspace/Other/ApiDebugger/src/main/antlr/io/chengguo/api/debugger/lang/parser/Test.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TestLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING_LITERAL=1, LF=2, WS=3;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"STRING_LITERAL", "HexDigit", "EscapeSequence", "LF", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "STRING_LITERAL", "LF", "WS"
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


	public TestLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Test.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\5<\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\7\2\21\n\2\f\2\16\2\24\13\2"+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\5\4\36\n\4\3\4\5\4!\n\4\3\4\3\4\3\4\6"+
		"\4&\n\4\r\4\16\4\'\3\4\3\4\3\4\3\4\3\4\5\4/\n\4\3\5\6\5\62\n\5\r\5\16"+
		"\5\63\3\6\6\6\67\n\6\r\6\16\68\3\6\3\6\2\2\7\3\3\5\2\7\2\t\4\13\5\3\2"+
		"\t\6\2\f\f\17\17$$^^\5\2\62;CHch\n\2$$))^^ddhhppttvv\3\2\62\65\3\2\62"+
		"9\4\2\f\f\17\17\5\2\13\f\17\17\"\"\2B\2\3\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\3\r\3\2\2\2\5\27\3\2\2\2\7.\3\2\2\2\t\61\3\2\2\2\13\66\3\2\2\2\r"+
		"\22\7$\2\2\16\21\n\2\2\2\17\21\5\7\4\2\20\16\3\2\2\2\20\17\3\2\2\2\21"+
		"\24\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\25\3\2\2\2\24\22\3\2\2\2\25"+
		"\26\7$\2\2\26\4\3\2\2\2\27\30\t\3\2\2\30\6\3\2\2\2\31\32\7^\2\2\32/\t"+
		"\4\2\2\33 \7^\2\2\34\36\t\5\2\2\35\34\3\2\2\2\35\36\3\2\2\2\36\37\3\2"+
		"\2\2\37!\t\6\2\2 \35\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"/\t\6\2\2#%\7^\2\2"+
		"$&\7w\2\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\5\5\3"+
		"\2*+\5\5\3\2+,\5\5\3\2,-\5\5\3\2-/\3\2\2\2.\31\3\2\2\2.\33\3\2\2\2.#\3"+
		"\2\2\2/\b\3\2\2\2\60\62\t\7\2\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2"+
		"\2\2\63\64\3\2\2\2\64\n\3\2\2\2\65\67\t\b\2\2\66\65\3\2\2\2\678\3\2\2"+
		"\28\66\3\2\2\289\3\2\2\29:\3\2\2\2:;\b\6\2\2;\f\3\2\2\2\13\2\20\22\35"+
		" \'.\638\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}