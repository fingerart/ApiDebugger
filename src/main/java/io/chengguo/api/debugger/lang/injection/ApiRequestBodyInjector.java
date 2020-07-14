package io.chengguo.api.debugger.lang.injection;

import com.intellij.lang.Language;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiRequestMessageGroup;
import io.chengguo.api.debugger.lang.psi.impl.ApiBodyMixin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ApiRequestBodyInjector implements MultiHostInjector {
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement context) {
        if (context instanceof ApiBodyMixin) {
            Language language = getInjectingLanguage(context);
            if (language == null) {
                return;
            }
            MultiHostRegistrar injector = registrar.startInjecting(language);
            injector.addPlace(null, null, (PsiLanguageInjectionHost) context, TextRange.create(0, context.getTextLength()));
            injector.doneInjecting();
        }
    }

    @Nullable
    private Language getInjectingLanguage(@NotNull PsiElement psiElement) {
        // 根据 Content-Type 动态调整语言
        ApiApiBlock apiBlock = PsiTreeUtil.getParentOfType(psiElement, ApiApiBlock.class);
        if (apiBlock != null) {
            String mimeType = apiBlock.getMimeType();
            if (StringUtil.isNotEmpty(mimeType)) {
                if (mimeType.endsWith("+json")) {
                    mimeType = "application/json";
                }
                return ContainerUtil.getFirstItem(Language.findInstancesByMimeType(mimeType));
            }
        }
        return null;
    }

    @NotNull
    @Override
    public List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return Collections.singletonList(ApiRequestMessageGroup.class);
    }
}