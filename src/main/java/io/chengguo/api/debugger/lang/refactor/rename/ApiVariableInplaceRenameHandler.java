package io.chengguo.api.debugger.lang.refactor.rename;

import com.intellij.json.psi.JsonProperty;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenameHandler;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenamer;
import io.chengguo.api.debugger.lang.ApiLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 变量原地重命名处理器
 */
@Deprecated
public class ApiVariableInplaceRenameHandler extends VariableInplaceRenameHandler {

    @Override
    protected boolean isAvailable(@Nullable PsiElement element, @NotNull Editor editor, @NotNull PsiFile file) {
        System.out.println("ApiVariableInplaceRenameHandler: element = " + element + ", editor = " + editor + ", file = " + file);
        return file.getLanguage() == ApiLanguage.INSTANCE && element instanceof JsonProperty;
    }

    @Nullable
    @Override
    protected VariableInplaceRenamer createRenamer(@NotNull PsiElement elementToRename, @NotNull Editor editor) {
        System.out.println("ApiVariableInplaceRenameHandler: elementToRename = " + elementToRename + ", editor = " + editor);
        return new ApiVariableInplaceRenamer((PsiNamedElement) elementToRename, editor);
    }
}
