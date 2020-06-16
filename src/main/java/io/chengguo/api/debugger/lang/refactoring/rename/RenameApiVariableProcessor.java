package io.chengguo.api.debugger.lang.refactoring.rename;

import com.intellij.psi.PsiElement;
import com.intellij.psi.search.SearchScope;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;
import com.intellij.usageView.UsageInfo;
import io.chengguo.api.debugger.lang.psi.ApiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class RenameApiVariableProcessor extends RenamePsiElementProcessor {
    @Override
    public boolean canProcessElement(@NotNull PsiElement element) {
        return element instanceof ApiElement;
    }

    @Override
    public void prepareRenaming(@NotNull PsiElement element, @NotNull String newName, @NotNull Map<PsiElement, String> allRenames, @NotNull SearchScope scope) {

    }

    @Override
    public void findCollisions(@NotNull PsiElement element, @NotNull String newName, @NotNull Map<? extends PsiElement, String> allRenames, @NotNull List<UsageInfo> result) {
        allRenames.forEach((key, value) -> {
            //TODO 查找所有的变量节点，抽取到工具类中
            
        });
    }
}
