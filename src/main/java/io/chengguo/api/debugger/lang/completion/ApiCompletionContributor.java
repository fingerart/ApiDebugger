package io.chengguo.api.debugger.lang.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiHeaderFieldKey;
import io.chengguo.api.debugger.lang.psi.ApiRequest;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;

@Deprecated
public class ApiCompletionContributor extends CompletionContributor {
    public ApiCompletionContributor() {
//        extend(CompletionType.BASIC, psiElement().withParent(ApiRequest.class), MethodCompletionProvider.INSTANCE);
    }

    private static class MethodCompletionProvider extends CompletionProvider<CompletionParameters> {
        public static final MethodCompletionProvider INSTANCE = new MethodCompletionProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            result.addElement(LookupElementBuilder.create("GET"));
        }
    }
}
