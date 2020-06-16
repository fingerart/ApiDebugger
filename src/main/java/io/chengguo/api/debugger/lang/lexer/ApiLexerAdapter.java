package io.chengguo.api.debugger.lang.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class ApiLexerAdapter extends FlexAdapter {
    public ApiLexerAdapter() {
        super(new ApiLexer());
    }
}