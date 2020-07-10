package io.chengguo.api.debugger.lang.injection;

import com.intellij.json.JsonLanguage;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import io.chengguo.api.debugger.lang.psi.ApiRequestMessageGroup;
import io.chengguo.api.debugger.lang.psi.impl.ApiBodyMixin;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class ApiRequestBodyInjector implements MultiHostInjector {
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement context) {
        if (context instanceof ApiBodyMixin) {
            // 根据 Content-Type 动态调整语言
            MultiHostRegistrar injector = registrar.startInjecting(JsonLanguage.INSTANCE);
            injector.addPlace(null, null, (PsiLanguageInjectionHost) context, TextRange.create(0, context.getTextLength()));
            injector.doneInjecting();
        }
    }

    @NotNull
    @Override
    public List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return Collections.singletonList(ApiRequestMessageGroup.class);
    }
}