package io.chengguo.api.debugger.lang;

import com.intellij.lexer.FlexAdapter;
import io.chengguo.api.debugger.lang.lexer._ApiLexer;

import java.io.Reader;

public class ApiLexerAdapter extends FlexAdapter {
    public ApiLexerAdapter() {
        super(new _ApiLexer((Reader) null));
    }
}
