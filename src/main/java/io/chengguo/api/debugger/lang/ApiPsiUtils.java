package io.chengguo.api.debugger.lang;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApiPsiUtils {
    public static final ApiApiBlock[] EMPTY = new ApiApiBlock[]{};

    public static boolean isOfTypes(@NotNull PsiElement element, @NotNull final TokenSet types) {
        ASTNode node = element.getNode();
        return node != null && types.contains(node.getElementType());
    }

    @Nullable
    public static ApiApiBlock findFirstApiBlock(PsiFile psiFile) {
        ApiApiBlock[] apiBlocks = findApiBlocks(psiFile);
        return apiBlocks.length == 0 ? null : apiBlocks[0];
    }

    public static ApiApiBlock[] findApiBlocks(PsiFile psiFile) {
        return PsiTreeUtil.findChildrenOfType(psiFile, ApiApiBlock.class).toArray(EMPTY);
    }

    @Nullable
    public static ApiVariable findFirstVariable(PsiFile psiFile) {
        ApiVariable[] apiVariables = findVariables(psiFile);
        return apiVariables.length == 0 ? null : apiVariables[0];
    }

    public static ApiVariable[] findVariables(PsiFile psiFile) {
        return PsiTreeUtil.findChildrenOfType(psiFile, ApiVariable.class).toArray(new ApiVariable[0]);
    }
/*
    public static ApiVariableName[] findVariableNames(PsiFile psiFile) {
        return PsiTreeUtil.findChildrenOfType(psiFile, ApiVariableName.class).toArray(new ApiVariableName[0]);
    }

    public static List<ApiVariableName> findVariableNames(@NotNull Project project) {
        return findVariableNames(project, null);
    }

    public static List<ApiVariableName> findVariableNames(@NotNull Project project, @Nullable String key) {
        List<ApiVariableName> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(ApiFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            ApiPsiFile apiFile = (ApiPsiFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (apiFile != null) {
                ApiVariableName[] variableNames = PsiTreeUtil.getChildrenOfType(apiFile, ApiVariableName.class);
                if (variableNames != null) {
                    for (ApiVariableName variableName : variableNames) {
                        if (key == null || key.equals(variableName.getName())) {
                            result.add(variableName);
                        }
                    }
                }
            }
        }
        return result;
    }*/
}
