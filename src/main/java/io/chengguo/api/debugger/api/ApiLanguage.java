package io.chengguo.api.debugger.api;

import com.intellij.lang.Language;

public class ApiLanguage extends Language {

    public static final ApiLanguage INSTANCE;

    protected ApiLanguage() {
        super("Api");
    }

    static {
        INSTANCE = new ApiLanguage();
    }
}
