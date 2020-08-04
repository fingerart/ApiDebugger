package io.chengguo.api.debugger.lang.refactor.rename;

import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.SuggestedNameInfo;
import com.intellij.refactoring.rename.NameSuggestionProvider;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * 原地重命名建议
 * <p>
 * 原地重命名时弹出建议项
 */
public class ApiVariableNameSuggestionProvider implements NameSuggestionProvider {
    @Nullable
    @Override
    public SuggestedNameInfo getSuggestedNames(PsiElement element, @Nullable PsiElement nameSuggestionContext, Set<String> result) {
        if (element instanceof ApiVariable) {
            result.add("haha");
            result.add("haha2");
            result.add("haha3");
        }
        return null;
    }
}
