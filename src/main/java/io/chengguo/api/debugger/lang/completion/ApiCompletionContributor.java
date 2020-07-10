package io.chengguo.api.debugger.lang.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.PlatformIcons;
import com.intellij.util.ProcessingContext;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.environment.ApiEnvironmentIndex;
import io.chengguo.api.debugger.lang.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * 完成贡献者
 * <br/>
 * 当输入中或按 ⌃Space 时的内容展示
 */
public class ApiCompletionContributor extends CompletionContributor {
    public static final List<String> METHODS = ContainerUtil.newArrayList("GET", "POST", "HEAD", "PUT", "DELETE", "CONNECT", "PATCH", "OPTIONS", "TRACE");
    public static final List<String> SCHEMES = ContainerUtil.newArrayList("http", "https");

    public ApiCompletionContributor() {
//        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_METHOD).withParent(ApiRequestLine.class), MethodCompletionProvider.INSTANCE);
//        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_SCHEME), SchemeCompletionProvider.INSTANCE);
        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_IDENTIFIER), VariableCompletionProvider.INSTANCE);
        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_HEADER_FIELD_NAME), HeaderNameProvider.INSTANCE);
    }

    private static class VariableCompletionProvider extends CompletionProvider<CompletionParameters> {
        public static final VariableCompletionProvider INSTANCE = new VariableCompletionProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            PsiElement element = parameters.getOriginalPosition();
            if (element != null) {
                addCompletions(parameters, context, result, element);
            }
        }

        private void addCompletions(CompletionParameters parameters, ProcessingContext context, CompletionResultSet result, PsiElement element) {
            Project project = element.getProject();
            Collection<String> allVariables = ApiEnvironmentIndex.getAllVariables(project, element.getContainingFile());
            fillVariables(allVariables, element, result);
        }

        private void fillVariables(Collection<String> allVariables, PsiElement element, CompletionResultSet result) {
            for (String variable : allVariables) {
                result.addElement(LookupElementBuilder
                        .create(variable)
                        .withIcon(PlatformIcons.VARIABLE_ICON)
                        .withInsertHandler(ApiSuffixInsertHandler.VARIABLE_OPTION)
                );
            }
        }
    }

    private static class MethodCompletionProvider extends CompletionProvider<CompletionParameters> {
        private static final MethodCompletionProvider INSTANCE = new MethodCompletionProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            for (final String method : METHODS) {
                result.addElement(LookupElementBuilder.create(method).withIcon(PlatformIcons.METHOD_ICON).withBoldness(true).withInsertHandler(AddSpaceInsertHandler.INSTANCE));
            }
        }
    }

    private static class SchemeCompletionProvider extends CompletionProvider<CompletionParameters> {
        private static final SchemeCompletionProvider INSTANCE = new SchemeCompletionProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            for (final String scheme : SCHEMES) {
                result.addElement(LookupElementBuilder.create(scheme).withBoldness(true).withInsertHandler(ApiSuffixInsertHandler.SCHEME));
            }
        }
    }

    private static class HeaderNameProvider extends CompletionProvider<CompletionParameters> {
        private static final HeaderNameProvider INSTANCE = new HeaderNameProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {

            result.addElement(LookupElementBuilder.create("Content-Type").withIcon(PlatformIcons.EXPORT_ICON));
        }
    }
}
