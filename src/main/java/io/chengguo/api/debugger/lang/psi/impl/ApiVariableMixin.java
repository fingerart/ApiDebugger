package io.chengguo.api.debugger.lang.psi.impl;

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
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.FileBasedIndex;
import com.intellij.util.indexing.ID;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class ApiVariableMixin extends ApiNamedElementImpl implements ApiVariable, PsiPolyVariantReference {

    public static final ID<String, Set<String>> KEY = ID.create("api.debugger.environment");

    public ApiVariableMixin(IElementType type) {
        super(type);
    }

    @NotNull
    @Override
    public PsiElement getElement() {
        return this;
    }

    @NotNull
    @Override
    public TextRange getRangeInElement() {
        PsiElement identifier = getIdentifier();
        if (identifier != null) {
            int startOffset = identifier.getTextRange().getStartOffset() - getTextRange().getStartOffset();
            return new TextRange(startOffset, startOffset + identifier.getTextLength());
        }
        return new TextRange(0, getTextLength());
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = getProject();
        PsiFile containingFile = getContainingFile();
        String name = getName();

        if (StringUtil.isEmpty(name)) {
            return ResolveResult.EMPTY_ARRAY;
        }
        GlobalSearchScope scope = getSearchScope(project, containingFile);
        final List<ResolveResult> result = new ArrayList<>();

        addVariableDefinitions(project, name, "env", result, scope);

        return result.toArray(ResolveResult.EMPTY_ARRAY);
    }

    private void addVariableDefinitions(Project project, String name, String env, List<ResolveResult> result, GlobalSearchScope scope) {
        FileBasedIndex.getInstance().processValues(KEY, env, null, (file, value) -> {
            if (value.contains(name)) {
                PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
                if (!(psiFile instanceof JsonFile)) {
                    return true;
                }
                System.out.println("ApiVariableMixin.addVariableDefinitions: " + psiFile.getText());
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
    public static GlobalSearchScope getSearchScope(@NotNull final Project project, @Nullable final PsiFile contextFile) {
        GlobalSearchScope projectScope = GlobalSearchScope.projectScope(project);
        VirtualFile context = (contextFile != null) ? contextFile.getVirtualFile() : null;
        if (contextFile != null && !ScratchUtil.isScratch(context) && !projectScope.contains(context)) {
            VirtualFile dir = context.getParent();
            if (dir != null) {
                VirtualFile[] files = dir.getChildren();
                return GlobalSearchScope.filesScope(project, (Collection) ((files.length > 0) ? ContainerUtil.newArrayList((Object[]) files) : Collections.emptyList()));
            }
        }
        return projectScope;
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return getText();
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        return setName(newElementName);
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        throw new UnsupportedOperationException("Method bindToElement is not implemented in " + this.getClass().getName());
    }

    @Override
    public boolean isReferenceTo(@NotNull PsiElement element) {
        return false;
    }

    @Override
    public PsiReference getReference() {
        return getIdentifier() != null ? this : null;
    }

    @Override
    public boolean isSoft() {
        return false;
    }
}