package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.lang.psi.ApiElementGenerator;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import io.chengguo.api.debugger.lang.psi.ApiVariableName;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ApiPsiImplUtils {

    @NotNull
    public static String getName(@NotNull ApiVariableName element) {
        PsiElement identifier = element.getNameIdentifier();
        if (identifier != null) {
            return identifier.getText();
        } else {
            return null;
        }
    }

    public static PsiElement setName(ApiVariableName element, @NotNull String newName) {
        if (element != null && !Objects.equals(element.getName(), newName)) {
            ApiVariable newVariable = new ApiElementGenerator(element.getProject()).createVariable(newName);
            if (newVariable != null) {
                ApiVariableName newVariableName = newVariable.getVariableName();
                if (newVariableName != null) {
                    element.replace(newVariableName);
                }
            }
        }
        return element;
    }

    @NotNull
    public static String getValue(@NotNull ApiVariableName element) {
        return "我是变量的值";
    }
}