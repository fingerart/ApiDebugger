package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ApiPsiElementPresentationFactory {

    static ItemPresentation getItemPresentation(PsiNamedElement element) {
        return new ItemPresentation() {

            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return element.getIcon(Iconable.ICON_FLAG_READ_STATUS);
            }
        };
    }
}
