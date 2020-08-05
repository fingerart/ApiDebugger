package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.navigation.ColoredItemPresentation;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiNamedElement;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ApiPsiElementPresentationFactory {

    static ItemPresentation getItemPresentation(PsiNamedElement element) {
        return new ColoredItemPresentation() {

            @Nullable
            @Override
            public TextAttributesKey getTextAttributesKey() {
                return CodeInsightColors.WARNINGS_ATTRIBUTES;
            }

            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return "我是LocationString";
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return element.getIcon(Iconable.ICON_FLAG_READ_STATUS);
            }
        };
    }

    static ItemPresentation getItemPresentation(ApiApiBlock element) {
        return new ItemPresentation() {

            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return "我是LocationString";
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return element.getIcon(Iconable.ICON_FLAG_READ_STATUS);
            }
        };
    }
}
