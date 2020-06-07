package io.chengguo.api.debugger.lang.formatter;

import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.FormattingModelProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class ApiFormattingModelBuilder implements FormattingModelBuilder {
    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        ApiBlock rootBlock = new ApiBlock(element.getNode(),settings);
        return FormattingModelProvider.createFormattingModelForPsiFile(
                element.getContainingFile(),
                rootBlock,
                settings);
    }
}
