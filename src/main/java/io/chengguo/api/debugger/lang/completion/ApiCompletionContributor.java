package io.chengguo.api.debugger.lang.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.PlatformIcons;
import com.intellij.util.ProcessingContext;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.environment.ApiEnvironmentIndex;
import io.chengguo.api.debugger.lang.psi.ApiHeaderField;
import io.chengguo.api.debugger.lang.psi.ApiHost;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.ui.header.HttpHeaderDocumentation;
import io.chengguo.api.debugger.ui.header.HttpHeadersDictionary;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * 完成贡献者
 * <br/>
 * 当输入中 或 按 ⌃Space 时的内容展示
 */
public class ApiCompletionContributor extends CompletionContributor {
    public static final List<String> METHODS = ContainerUtil.newArrayList("GET", "POST", "HEAD", "PUT", "DELETE", "CONNECT", "PATCH", "OPTIONS", "TRACE");
    public static final List<String> SCHEMES = ContainerUtil.newArrayList("http", "https");

    public ApiCompletionContributor() {
//        extend(CompletionType.BASIC, psiElement(), MethodCompletionProvider.INSTANCE);
        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_HOST_VALUE).withParent(ApiHost.class), SchemeCompletionProvider.INSTANCE);
        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_IDENTIFIER), VariableCompletionProvider.INSTANCE);
        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_HEADER_FIELD_NAME), HeaderNameProvider.INSTANCE);
        extend(CompletionType.BASIC, psiElement(ApiTypes.Api_HEADER_FIELD_VALUE), HeaderValueProvider.INSTANCE);
    }

    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        super.beforeCompletion(context);
        // TODO: 2020/8/6
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
            CompletionResultSet resultSet = result.caseInsensitive();
            for (String variable : allVariables) {
                LookupElementBuilder lookupBuilder = LookupElementBuilder.create(variable)
                        .withIcon(PlatformIcons.VARIABLE_ICON)
                        .withInsertHandler(ApiSuffixInsertHandler.VARIABLE_OPTION);
                resultSet.addElement(lookupBuilder);
            }
        }
    }

    private static class MethodCompletionProvider extends CompletionProvider<CompletionParameters> {
        private static final MethodCompletionProvider INSTANCE = new MethodCompletionProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            CompletionResultSet resultSet = result.caseInsensitive();
            for (String method : METHODS) {
                LookupElementBuilder lookupBuilder = LookupElementBuilder.create(method)
                        .withIcon(PlatformIcons.METHOD_ICON)
                        .withBoldness(true)
                        .withInsertHandler(AddSpaceInsertHandler.INSTANCE);
                resultSet.addElement(lookupBuilder);
            }
        }
    }

    private static class SchemeCompletionProvider extends CompletionProvider<CompletionParameters> {
        private static final SchemeCompletionProvider INSTANCE = new SchemeCompletionProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            CompletionResultSet resultSet = result.caseInsensitive();
            for (String scheme : SCHEMES) {
                LookupElementBuilder lookupBuilder = LookupElementBuilder.create(scheme)
                        .withBoldness(true)
                        .withIcon(PlatformIcons.FIELD_ICON)
                        .withInsertHandler(ApiSuffixInsertHandler.SCHEME);
                resultSet.addElement(lookupBuilder);
            }
        }
    }

    private static class HeaderNameProvider extends CompletionProvider<CompletionParameters> {
        private static final HeaderNameProvider INSTANCE = new HeaderNameProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            CompletionResultSet resultSet = result.caseInsensitive();
            for (HttpHeaderDocumentation header : HttpHeadersDictionary.getHeaders().values()) {
                LookupElementBuilder lookupBuilder = LookupElementBuilder.create(header, header.getName())
                        .withStrikeoutness(header.isDeprecated())
                        .withIcon(PlatformIcons.METHOD_ICON)
                        .withTypeIconRightAligned(true)
                        .withInsertHandler(ApiSuffixInsertHandler.FIELD_SEPARATOR);
                resultSet.addElement(lookupBuilder);
            }
        }
    }

    private static class HeaderValueProvider extends CompletionProvider<CompletionParameters> {
        private static HeaderValueProvider INSTANCE = new HeaderValueProvider();

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            ApiHeaderField headerField = PsiTreeUtil.getParentOfType(parameters.getPosition(), ApiHeaderField.class);
            String fieldName = headerField != null ? headerField.getName() : null;
            if (StringUtil.isNotEmpty(fieldName)) {
                CompletionResultSet resultSet = result.caseInsensitive();
                Collection<String> headerValues = HttpHeadersDictionary.getHeaderValues(parameters.getPosition().getProject(), fieldName);
                for (String headerValue : headerValues) {
                    resultSet.addElement(LookupElementBuilder.create(headerValue));
                }
                Collection<String> headerOptionNames = HttpHeadersDictionary.getHeaderOptionNames(fieldName);
                for (String headerOptionName : headerOptionNames) {
                    resultSet.addElement(LookupElementBuilder.create(headerOptionName).withInsertHandler(ApiSuffixInsertHandler.HEADER_OPTION));
                }
            }
        }
    }
}
