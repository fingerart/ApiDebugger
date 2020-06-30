package io.chengguo.api.debugger.lang.psi;

import com.google.common.base.Strings;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.ide.scratch.ScratchUtil;
import com.intellij.json.JsonUtil;
import com.intellij.json.psi.JsonFile;
import com.intellij.json.psi.JsonObject;
import com.intellij.json.psi.JsonProperty;
import com.intellij.json.psi.JsonValue;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.FileBasedIndex;
import com.intellij.util.indexing.ID;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.environment.ApiEnvironmentIndex;
import io.chengguo.api.debugger.lang.environment.ApiEnvironmentInputFilter;
import io.chengguo.api.debugger.lang.psi.impl.ApiNamedElementImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ApiVariableDefinitionReference<T extends ApiNamedElementImpl> extends PsiPolyVariantReferenceBase<T> {

    private final TextRange mRangeInElement;
    private final String mIdentifier;

    public ApiVariableDefinitionReference(@NotNull T element, String identifier, TextRange textRange, TextRange rangeInElement) {
        super(element, textRange);
        mIdentifier = identifier;
        mRangeInElement = rangeInElement;
    }

    @NotNull
    @Override
    public TextRange getRangeInElement() {
        return mRangeInElement;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        PsiFile containingFile = myElement.getContainingFile();
        GlobalSearchScope scope = ApiEnvironmentIndex.getSearchScope(project, containingFile);
        final List<ResolveResult> result = new ArrayList<>();
        String defaultEnv = "env";
        addVariableDefinitions(project, mIdentifier, defaultEnv, result, scope);

        for (final String env : FileBasedIndex.getInstance().getAllKeys(ApiEnvironmentIndex.KEY, project)) {
            if (!StringUtil.equals(env, defaultEnv)) {
                addVariableDefinitions(project, mIdentifier, env, result, scope);
            }
        }
        return result.isEmpty() ? ResolveResult.EMPTY_ARRAY : result.toArray(ResolveResult.EMPTY_ARRAY);
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        return myElement.setName(newElementName);
    }

    private void addVariableDefinitions(Project project, String name, String env, List<ResolveResult> result, GlobalSearchScope scope) {
        FileBasedIndex.getInstance().processValues(ApiEnvironmentIndex.KEY, env, null, (file, value) -> {
            if (value.contains(name)) {
                PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
                if (!(psiFile instanceof JsonFile)) {
                    return true;
                }
                JsonValue root = ((JsonFile) psiFile).getTopLevelValue();
                JsonObject environment = (root instanceof JsonObject) ? JsonUtil.getPropertyValueOfType((JsonObject) root, env, JsonObject.class) : null;
                JsonProperty property = (environment != null) ? environment.findProperty(name) : null;
                if (property != null) {
                    result.add(new PsiElementResolveResult(property));
                }
            }
            return true;
        }, scope);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
//        List<ApiVariableName> variableNames = ApiPsiUtils.findVariableNames(project);
        List<LookupElement> variants = new ArrayList<>();
        /*for (ApiVariableName variableName : variableNames) {
            if (!Strings.isNullOrEmpty(variableName.getName())) {
                LookupElementBuilder lookupElementBuilder = LookupElementBuilder
                        .create(variableName)
                        .withIcon(ApiDebuggerIcons.FAVICON)
                        .withTypeText(variableName.getContainingFile().getName());
                variants.add(lookupElementBuilder);
            }
        }*/
        return variants.toArray();
    }

    @Override
    public boolean isReferenceTo(@NotNull PsiElement element) {
        return couldBeReferenceTo(element);
    }

    private boolean couldBeReferenceTo(@NotNull PsiElement element) {
        if (element instanceof JsonProperty && StringUtil.equals(((JsonProperty)element).getName(), myElement.getName()) && element.isValid() && element.isPhysical()) {
            final PsiFile file = element.getContainingFile();
            return file != null && ApiEnvironmentInputFilter.isApiEnvFile(file.getVirtualFile());
        }
        return false;
    }
}
