// Generated from /Users/fingerart/Workspace/Other/ApiDebugger/src/main/antlr/io/chengguo/api/debugger/lang/parser/Api.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Api extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GET=1, HEAD=2, POST=3, PUT=4, DELETE=5, CONNECT=6, OPTIONS=7, TRACE=8, 
		LCURL=9, RCURL=10, DOLLAR=11, Slash=12, DOT=13, ASSIGN=14, COLON=15, QUESTION=16, 
		BITAND=17, PROTCOL=18, WELL=19, HEX_URI=20, DIGITS=21, STRING_LITERAL=22, 
		LF=23, WS=24, COMMENT=25, LINE_COMMENT=26, Identifier=27, LineStrExprStart=28, 
		SP=29, Digits=30, HexUri=31;
	public static final int
		RULE_api = 0, RULE_info = 1, RULE_http = 2, RULE_variable = 3, RULE_method = 4, 
		RULE_uri = 5, RULE_scheme = 6, RULE_host = 7, RULE_hostnumber = 8, RULE_hostname = 9, 
		RULE_port = 10, RULE_path = 11, RULE_query = 12, RULE_search = 13, RULE_searchparameter = 14, 
		RULE_string = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"api", "info", "http", "variable", "method", "uri", "scheme", "host", 
			"hostnumber", "hostname", "port", "path", "query", "search", "searchparameter", 
			"string"
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
			"BITAND", "PROTCOL", "WELL", "HEX_URI", "DIGITS", "STRING_LITERAL", "LF", 
			"WS", "COMMENT", "LINE_COMMENT", "Identifier", "LineStrExprStart", "SP", 
			"Digits", "HexUri"
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

	@Override
	public String getGrammarFileName() { return "Api.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Api(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ApiContext extends ParserRuleContext {
		public InfoContext info() {
			return getRuleContext(InfoContext.class,0);
		}
		public HttpContext http() {
			return getRuleContext(HttpContext.class,0);
		}
		public ApiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_api; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterApi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitApi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitApi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApiContext api() throws RecognitionException {
		ApiContext _localctx = new ApiContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_api);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			info();
			setState(33);
			http();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InfoContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(Api.STRING_LITERAL, 0); }
		public InfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_info; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitInfo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitInfo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoContext info() throws RecognitionException {
		InfoContext _localctx = new InfoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_info);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HttpContext extends ParserRuleContext {
		public MethodContext method() {
			return getRuleContext(MethodContext.class,0);
		}
		public TerminalNode SP() { return getToken(Api.SP, 0); }
		public UriContext uri() {
			return getRuleContext(UriContext.class,0);
		}
		public HttpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_http; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterHttp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitHttp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitHttp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HttpContext http() throws RecognitionException {
		HttpContext _localctx = new HttpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_http);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			method();
			setState(38);
			match(SP);
			setState(39);
			uri();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode DOLLAR() { return getToken(Api.DOLLAR, 0); }
		public TerminalNode LCURL() { return getToken(Api.LCURL, 0); }
		public TerminalNode RCURL() { return getToken(Api.RCURL, 0); }
		public TerminalNode Identifier() { return getToken(Api.Identifier, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(DOLLAR);
			setState(42);
			match(LCURL);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(43);
				match(Identifier);
				}
			}

			setState(46);
			match(RCURL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodContext extends ParserRuleContext {
		public TerminalNode GET() { return getToken(Api.GET, 0); }
		public TerminalNode HEAD() { return getToken(Api.HEAD, 0); }
		public TerminalNode POST() { return getToken(Api.POST, 0); }
		public TerminalNode PUT() { return getToken(Api.PUT, 0); }
		public TerminalNode DELETE() { return getToken(Api.DELETE, 0); }
		public TerminalNode CONNECT() { return getToken(Api.CONNECT, 0); }
		public TerminalNode OPTIONS() { return getToken(Api.OPTIONS, 0); }
		public TerminalNode TRACE() { return getToken(Api.TRACE, 0); }
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_method);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GET) | (1L << HEAD) | (1L << POST) | (1L << PUT) | (1L << DELETE) | (1L << CONNECT) | (1L << OPTIONS) | (1L << TRACE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UriContext extends ParserRuleContext {
		public SchemeContext scheme() {
			return getRuleContext(SchemeContext.class,0);
		}
		public TerminalNode PROTCOL() { return getToken(Api.PROTCOL, 0); }
		public HostContext host() {
			return getRuleContext(HostContext.class,0);
		}
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Api.COLON, 0); }
		public PortContext port() {
			return getRuleContext(PortContext.class,0);
		}
		public UriContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uri; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterUri(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitUri(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitUri(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UriContext uri() throws RecognitionException {
		UriContext _localctx = new UriContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_uri);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(50);
				scheme();
				setState(51);
				match(PROTCOL);
				}
				break;
			}
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(55);
				host();
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(56);
					match(COLON);
					setState(57);
					port();
					}
				}

				}
				break;
			}
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(62);
				path();
				}
				break;
			}
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION) {
				{
				setState(65);
				query();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SchemeContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public SchemeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scheme; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterScheme(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitScheme(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitScheme(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemeContext scheme() throws RecognitionException {
		SchemeContext _localctx = new SchemeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_scheme);
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				string();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				variable();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HostContext extends ParserRuleContext {
		public HostnumberContext hostnumber() {
			return getRuleContext(HostnumberContext.class,0);
		}
		public HostnameContext hostname() {
			return getRuleContext(HostnameContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode Slash() { return getToken(Api.Slash, 0); }
		public HostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_host; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterHost(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitHost(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitHost(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HostContext host() throws RecognitionException {
		HostContext _localctx = new HostContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_host);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Slash) {
				{
				setState(72);
				match(Slash);
				}
			}

			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(75);
				hostnumber();
				}
				break;
			case 2:
				{
				setState(76);
				hostname();
				}
				break;
			case 3:
				{
				setState(77);
				variable();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HostnumberContext extends ParserRuleContext {
		public List<TerminalNode> Digits() { return getTokens(Api.Digits); }
		public TerminalNode Digits(int i) {
			return getToken(Api.Digits, i);
		}
		public List<TerminalNode> DOT() { return getTokens(Api.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Api.DOT, i);
		}
		public HostnumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hostnumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterHostnumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitHostnumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitHostnumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HostnumberContext hostnumber() throws RecognitionException {
		HostnumberContext _localctx = new HostnumberContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_hostnumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(Digits);
			setState(81);
			match(DOT);
			setState(82);
			match(Digits);
			setState(83);
			match(DOT);
			setState(84);
			match(Digits);
			setState(85);
			match(DOT);
			setState(86);
			match(Digits);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HostnameContext extends ParserRuleContext {
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(Api.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Api.DOT, i);
		}
		public HostnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hostname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterHostname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitHostname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitHostname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HostnameContext hostname() throws RecognitionException {
		HostnameContext _localctx = new HostnameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_hostname);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(88);
				string();
				}
				break;
			case 2:
				{
				setState(89);
				variable();
				}
				break;
			}
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(92);
				match(DOT);
				setState(95);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(93);
					string();
					}
					break;
				case 2:
					{
					setState(94);
					variable();
					}
					break;
				}
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PortContext extends ParserRuleContext {
		public TerminalNode Digits() { return getToken(Api.Digits, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public PortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_port; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterPort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitPort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitPort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PortContext port() throws RecognitionException {
		PortContext _localctx = new PortContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_port);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Digits:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				match(Digits);
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathContext extends ParserRuleContext {
		public List<TerminalNode> Slash() { return getTokens(Api.Slash); }
		public TerminalNode Slash(int i) {
			return getToken(Api.Slash, i);
		}
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_path);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Slash) {
				{
				{
				setState(106);
				match(Slash);
				setState(109);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(107);
					string();
					}
					break;
				case 2:
					{
					setState(108);
					variable();
					}
					break;
				}
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public TerminalNode QUESTION() { return getToken(Api.QUESTION, 0); }
		public SearchContext search() {
			return getRuleContext(SearchContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(QUESTION);
			setState(117);
			search();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SearchContext extends ParserRuleContext {
		public List<SearchparameterContext> searchparameter() {
			return getRuleContexts(SearchparameterContext.class);
		}
		public SearchparameterContext searchparameter(int i) {
			return getRuleContext(SearchparameterContext.class,i);
		}
		public List<TerminalNode> BITAND() { return getTokens(Api.BITAND); }
		public TerminalNode BITAND(int i) {
			return getToken(Api.BITAND, i);
		}
		public SearchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_search; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterSearch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitSearch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitSearch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SearchContext search() throws RecognitionException {
		SearchContext _localctx = new SearchContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_search);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			searchparameter();
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITAND) {
				{
				{
				setState(120);
				match(BITAND);
				setState(121);
				searchparameter();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SearchparameterContext extends ParserRuleContext {
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(Api.ASSIGN, 0); }
		public TerminalNode Digits() { return getToken(Api.Digits, 0); }
		public TerminalNode HexUri() { return getToken(Api.HexUri, 0); }
		public SearchparameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_searchparameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterSearchparameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitSearchparameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitSearchparameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SearchparameterContext searchparameter() throws RecognitionException {
		SearchparameterContext _localctx = new SearchparameterContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_searchparameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			string();
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(128);
				match(ASSIGN);
				setState(132);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DOLLAR:
				case Identifier:
					{
					setState(129);
					string();
					}
					break;
				case Digits:
					{
					setState(130);
					match(Digits);
					}
					break;
				case HexUri:
					{
					setState(131);
					match(HexUri);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Api.Identifier, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiListener ) ((ApiListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiVisitor ) return ((ApiVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_string);
		try {
			setState(138);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				match(Identifier);
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u008f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\5\5/\n\5\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\7\5\78\n\7\3\7\3\7\3\7\5\7=\n\7\5\7?\n\7\3\7\5\7B\n\7\3\7\5\7E\n"+
		"\7\3\b\3\b\5\bI\n\b\3\t\5\tL\n\t\3\t\3\t\3\t\5\tQ\n\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\5\13]\n\13\3\13\3\13\3\13\5\13b\n\13\7\13d"+
		"\n\13\f\13\16\13g\13\13\3\f\3\f\5\fk\n\f\3\r\3\r\3\r\5\rp\n\r\7\rr\n\r"+
		"\f\r\16\ru\13\r\3\16\3\16\3\16\3\17\3\17\3\17\7\17}\n\17\f\17\16\17\u0080"+
		"\13\17\3\20\3\20\3\20\3\20\3\20\5\20\u0087\n\20\5\20\u0089\n\20\3\21\3"+
		"\21\5\21\u008d\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \2\3\3\2\3\n\2\u0093\2\"\3\2\2\2\4%\3\2\2\2\6\'\3\2\2\2\b+\3\2\2\2\n"+
		"\62\3\2\2\2\f\67\3\2\2\2\16H\3\2\2\2\20K\3\2\2\2\22R\3\2\2\2\24\\\3\2"+
		"\2\2\26j\3\2\2\2\30s\3\2\2\2\32v\3\2\2\2\34y\3\2\2\2\36\u0081\3\2\2\2"+
		" \u008c\3\2\2\2\"#\5\4\3\2#$\5\6\4\2$\3\3\2\2\2%&\7\30\2\2&\5\3\2\2\2"+
		"\'(\5\n\6\2()\7\37\2\2)*\5\f\7\2*\7\3\2\2\2+,\7\r\2\2,.\7\13\2\2-/\7\35"+
		"\2\2.-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\f\2\2\61\t\3\2\2\2\62\63"+
		"\t\2\2\2\63\13\3\2\2\2\64\65\5\16\b\2\65\66\7\24\2\2\668\3\2\2\2\67\64"+
		"\3\2\2\2\678\3\2\2\28>\3\2\2\29<\5\20\t\2:;\7\21\2\2;=\5\26\f\2<:\3\2"+
		"\2\2<=\3\2\2\2=?\3\2\2\2>9\3\2\2\2>?\3\2\2\2?A\3\2\2\2@B\5\30\r\2A@\3"+
		"\2\2\2AB\3\2\2\2BD\3\2\2\2CE\5\32\16\2DC\3\2\2\2DE\3\2\2\2E\r\3\2\2\2"+
		"FI\5 \21\2GI\5\b\5\2HF\3\2\2\2HG\3\2\2\2I\17\3\2\2\2JL\7\16\2\2KJ\3\2"+
		"\2\2KL\3\2\2\2LP\3\2\2\2MQ\5\22\n\2NQ\5\24\13\2OQ\5\b\5\2PM\3\2\2\2PN"+
		"\3\2\2\2PO\3\2\2\2Q\21\3\2\2\2RS\7 \2\2ST\7\17\2\2TU\7 \2\2UV\7\17\2\2"+
		"VW\7 \2\2WX\7\17\2\2XY\7 \2\2Y\23\3\2\2\2Z]\5 \21\2[]\5\b\5\2\\Z\3\2\2"+
		"\2\\[\3\2\2\2]e\3\2\2\2^a\7\17\2\2_b\5 \21\2`b\5\b\5\2a_\3\2\2\2a`\3\2"+
		"\2\2bd\3\2\2\2c^\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\25\3\2\2\2ge\3"+
		"\2\2\2hk\7 \2\2ik\5\b\5\2jh\3\2\2\2ji\3\2\2\2k\27\3\2\2\2lo\7\16\2\2m"+
		"p\5 \21\2np\5\b\5\2om\3\2\2\2on\3\2\2\2pr\3\2\2\2ql\3\2\2\2ru\3\2\2\2"+
		"sq\3\2\2\2st\3\2\2\2t\31\3\2\2\2us\3\2\2\2vw\7\22\2\2wx\5\34\17\2x\33"+
		"\3\2\2\2y~\5\36\20\2z{\7\23\2\2{}\5\36\20\2|z\3\2\2\2}\u0080\3\2\2\2~"+
		"|\3\2\2\2~\177\3\2\2\2\177\35\3\2\2\2\u0080~\3\2\2\2\u0081\u0088\5 \21"+
		"\2\u0082\u0086\7\20\2\2\u0083\u0087\5 \21\2\u0084\u0087\7 \2\2\u0085\u0087"+
		"\7!\2\2\u0086\u0083\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0085\3\2\2\2\u0087"+
		"\u0089\3\2\2\2\u0088\u0082\3\2\2\2\u0088\u0089\3\2\2\2\u0089\37\3\2\2"+
		"\2\u008a\u008d\7\35\2\2\u008b\u008d\5\b\5\2\u008c\u008a\3\2\2\2\u008c"+
		"\u008b\3\2\2\2\u008d!\3\2\2\2\25.\67<>ADHKP\\aejos~\u0086\u0088\u008c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}