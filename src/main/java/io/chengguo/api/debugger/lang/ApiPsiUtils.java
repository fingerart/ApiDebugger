package io.chengguo.api.debugger.lang;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @NotNull
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

    public static PsiFile findFileByPath(Project project, String filePath) {
        if (StringUtil.isEmpty(filePath)) {
            return null;
        }
        String url = StringUtil.isEmpty(VirtualFileManager.extractProtocol(filePath)) ? VfsUtilCore.pathToUrl(filePath) : filePath;
        VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
        VirtualFile virtualFile = virtualFileManager.findFileByUrl(url);
        return virtualFile != null ? PsiManager.getInstance(project).findFile(virtualFile) : null;
    }

    /**
     * 是否是叶子节点
     *
     * @param element
     * @return
     */
    public static boolean isLeafElement(@Nullable PsiElement element) {
        return element == null || element.getNode().getFirstChildNode() == null;
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

    public static int skipWhitespacesForward(int offset, @NotNull final CharSequence text) {
        while (offset < text.length() && isWhitespaceOrTab(text.charAt(offset))) {
            ++offset;
        }
        return offset;
    }

    private static boolean isWhitespaceOrTab(final char c) {
        return c == ' ' || c == '\t';
    }


    public static boolean isOfType(@NotNull final PsiElement element, @NotNull final IElementType type) {
        ASTNode node = element.getNode();
        return node != null && node.getElementType() == type;
    }

    @Nullable
    public static PsiElement getPrevSiblingIgnoreWhitespace(@Nullable final PsiElement element) {
        final PsiElement sibling = (element != null) ? element.getPrevSibling() : null;
        return (sibling instanceof PsiWhiteSpace) ? sibling.getPrevSibling() : sibling;
    }

    @Nullable
    public static PsiElement getNextSiblingByType(@Nullable PsiElement element, @NotNull IElementType type, final boolean strict) {
        PsiElement sibling;
        for (sibling = ((element != null && strict) ? element.getNextSibling() : element); sibling != null && !isOfType(sibling, type); sibling = sibling.getNextSibling()) {
        }
        return sibling;
    }
}