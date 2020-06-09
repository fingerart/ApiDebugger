package io.chengguo.api.debugger.lang.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import io.chengguo.api.debugger.lang.ApiLanguage;
import io.chengguo.api.debugger.lang.psi.ApiHost;
import io.chengguo.api.debugger.lang.psi.ApiMethod;
import io.chengguo.api.debugger.lang.psi.ApiRequestLine;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;

public class ApiCompletionContributor extends CompletionContributor {
    public ApiCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(ApiTypes.Api_HOST_VALUE).withParent(ApiHost.class), new MethodCompletionProvider());
    }

    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        super.beforeCompletion(context);
    }

    private static class MethodCompletionProvider extends CompletionProvider<CompletionParameters> {
        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            result.addElement(LookupElementBuilder.create("GET"));
        }
    }
}
