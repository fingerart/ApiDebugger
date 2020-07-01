package io.chengguo.api.debugger.lang.refactor.rename;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.FakePsiElement;
import com.intellij.psi.search.SearchScope;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;
import com.intellij.usageView.UsageInfo;
import io.chengguo.api.debugger.lang.psi.ApiNamedElement;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Deprecated
public class ApiVariableRenameProcessor extends RenamePsiElementProcessor {
    @Override
    public boolean canProcessElement(@NotNull PsiElement element) {
        return true;
    }

    @NotNull
    @Override
    public Collection<PsiReference> findReferences(@NotNull PsiElement element,
                                                   @NotNull SearchScope searchScope,
                                                   boolean searchInCommentsAndStrings) {
        System.out.println("RenameApiVariableProcessor.findReferences " + "element = " + element + ", searchScope = " + searchScope + ", searchInCommentsAndStrings = " + searchInCommentsAndStrings);
        return super.findReferences(element, searchScope, searchInCommentsAndStrings);
    }

    @Override
    public void findCollisions(@NotNull PsiElement element,
                               @NotNull String newName,
                               @NotNull Map<? extends PsiElement, String> allRenames,
                               @NotNull List<UsageInfo> result) {
        System.out.println("RenameApiVariableProcessor.findCollisions " + "element = " + element + ", newName = " + newName + ", allRenames = " + allRenames + ", result = " + result);
        allRenames.forEach((psiElement, value) -> {
            //TODO 查找所有的变量节点，抽取到工具类中
            System.out.println("RenameApiVariableProcessor.findCollisions: " + psiElement + " - " + value);
        });
    }
}
