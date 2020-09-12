package io.chengguo.api.debugger.lang.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ColoredItemPresentation;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import io.chengguo.api.debugger.lang.ApiPsiFile;
import io.chengguo.api.debugger.lang.ApiPsiUtil;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiRequestTarget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * API文件视图结构
 */
public class ApiStructureViewElement extends PsiTreeElementBase<PsiElement> implements ColoredItemPresentation {

    private final String mPresentableText;
    private final Icon mIcon;
    private final Boolean mIsValid;

    public ApiStructureViewElement(PsiElement psiElement, String text, Icon icon, Boolean isValid) {
        super(psiElement);
        mPresentableText = text;
        mIcon = icon;
        mIsValid = isValid;
    }

    @NotNull
    @Override
    public Collection<StructureViewTreeElement> getChildrenBase() {
        PsiElement element = getElement();
        if (element instanceof ApiPsiFile) {
            ArrayList<StructureViewTreeElement> treeElements = new ArrayList<>();
            ApiApiBlock[] apiBlocks = ApiPsiUtil.findApiBlocks(element.getContainingFile());
            for (ApiApiBlock apiBlock : apiBlocks) {
                ApiRequestTarget requestTarget = apiBlock.getRequest().getRequestLine().getRequestTarget();
                String baseUrl = requestTarget.getUrl(ApiVariableReplacer.EMPTY);
                treeElements.add(createApiBlockViewTreeElement(apiBlock, baseUrl, true));
            }
            return treeElements;
        }
        return ContainerUtil.emptyList();
    }

    @Nullable
    @Override
    public String getPresentableText() {
        return mPresentableText;
    }

    @Override
    public Icon getIcon(boolean open) {
        return mIcon;
    }

    @Nullable
    @Override
    public TextAttributesKey getTextAttributesKey() {
        if (!this.mIsValid) {
            return CodeInsightColors.ERRORS_ATTRIBUTES;
        }
        return null;
    }

    public static ApiStructureViewElement create(PsiElement psiElement, String text, Icon icon) {
        return new ApiStructureViewElement(psiElement, text, icon, true);
    }

    public static StructureViewTreeElement createApiBlockViewTreeElement(ApiApiBlock apiBlock, String text, Boolean isValid) {
        return new ApiStructureViewElement(apiBlock, text, ApiDebuggerIcons.API_REQUEST_NAV, isValid);
    }
}
