package io.chengguo.api.debugger.lang.refactor;

import com.intellij.lang.Language;
import com.intellij.lang.refactoring.InlineActionHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.ApiLanguage;
import io.chengguo.api.debugger.lang.psi.ApiVariable;

@Deprecated
public class ApiInlineVariableActionHandler extends InlineActionHandler {
    @Override
    public boolean isEnabledForLanguage(Language language) {
        return language == ApiLanguage.INSTANCE;
    }

    @Override
    public boolean canInlineElement(PsiElement element) {
        return element instanceof ApiVariable;
    }

    @Override
    public void inlineElement(Project project, Editor editor, PsiElement element) {
        System.out.println("ApiInlineVariableActionHandler.inlineElement" + "project = " + project + ", editor = " + editor + ", element = " + element);
    }
}
