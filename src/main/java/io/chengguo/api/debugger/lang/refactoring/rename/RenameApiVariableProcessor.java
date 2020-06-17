package io.chengguo.api.debugger.lang.refactoring.rename;

import com.intellij.psi.PsiElement;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;
import com.intellij.usageView.UsageInfo;
import io.chengguo.api.debugger.lang.psi.ApiNamedElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class RenameApiVariableProcessor extends RenamePsiElementProcessor {
    @Override
    public boolean canProcessElement(@NotNull PsiElement element) {
        return element instanceof ApiNamedElement;
    }

    @Override
    public void findCollisions(@NotNull PsiElement element, @NotNull String newName, @NotNull Map<? extends PsiElement, String> allRenames, @NotNull List<UsageInfo> result) {
        System.out.println("element = " + element + ", newName = " + newName + ", allRenames = " + allRenames + ", result = " + result);
        allRenames.forEach((psiElement, value) -> {
            //TODO 查找所有的变量节点，抽取到工具类中
            System.out.println("RenameApiVariableProcessor.findCollisions: " + psiElement + " - " + value);
        });
    }
}
