package io.chengguo.api.debugger.lang.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ColoredItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.ApiPsiFile;
import io.chengguo.api.debugger.lang.psi.ApiDebugger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class ApiStructureViewElement extends PsiTreeElementBase<PsiElement> implements ColoredItemPresentation {
    protected ApiStructureViewElement(PsiElement psiElement) {
        super(psiElement);
    }

    @NotNull
    @Override
    public Collection<StructureViewTreeElement> getChildrenBase() {
        PsiElement element = getElement();
        if (element instanceof ApiPsiFile) {
            ApiDebugger type = PsiTreeUtil.findChildOfType(element, ApiDebugger.class);
            if (type != null) {

                return ContainerUtil.newArrayList();
            }
        }
        return ContainerUtil.emptyList();
    }

    @Nullable
    @Override
    public String getPresentableText() {
        return null;
    }

    @Nullable
    @Override
    public TextAttributesKey getTextAttributesKey() {
        return null;
    }

    public static ApiStructureViewElement create(PsiElement psiElement) {
        return new ApiStructureViewElement(psiElement);
    }
}
