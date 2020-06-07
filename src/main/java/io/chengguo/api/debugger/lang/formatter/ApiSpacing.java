package io.chengguo.api.debugger.lang.formatter;

import com.intellij.formatting.SpacingBuilder;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import io.chengguo.api.debugger.lang.ApiLanguage;
import io.chengguo.api.debugger.lang.psi.ApiTypes;

public class ApiSpacing {

    public static SpacingBuilder createSpacingBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, ApiLanguage.INSTANCE)
                .after(ApiTypes.Api_COLON)
                .spaces(1)
                .before(ApiTypes.Api_COLON)
                .spaces(0);
    }
}