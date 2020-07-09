package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import io.chengguo.api.debugger.extension.ObjectExKt;
import io.chengguo.api.debugger.lang.psi.ApiElementGenerator;
import io.chengguo.api.debugger.lang.psi.ApiNamedElement;
import io.chengguo.api.debugger.lang.psi.ApiVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class ApiNamedElementImpl extends ApiElementImpl implements ApiNamedElement {

    private String mCachedName;

    public ApiNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return getIdentifier();
    }

    @Override
    public void subtreeChanged() {
        super.subtreeChanged();
        mCachedName = null;
    }

    @Override
    public String getName() {
        if (mCachedName == null) {
            mCachedName = ApiPsiImplUtils.getIdText(getIdentifier());
        }
        return mCachedName;
    }

    @Override
    public PsiElement setName(@NotNull String newName) throws IncorrectOperationException {
        PsiElement identifier = getIdentifier();
        if (identifier != null && StringUtil.isNotEmpty(newName) && !StringUtil.equals(getName(), newName)) {
            ApiVariable newVariable = new ApiElementGenerator(getProject()).createVariable(newName);
            PsiElement newIdentifier = newVariable.getIdentifier();
            identifier.replace(ObjectExKt.requireNonNull(newIdentifier, IncorrectOperationException::new));
        }
        return this;
    }

    @Override
    public int getTextOffset() {
        PsiElement identifier = getIdentifier();
        return identifier != null ? identifier.getTextOffset() : super.getTextOffset();
    }

//    @NotNull
//    @Override
//    public SearchScope getUseScope() {
//        return new LocalSearchScope(getContainingFile());
//    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        if (this instanceof ApiVariable) {
            return ApiDebuggerIcons.API_FILE_TYPE;
        }
        return super.getIcon(flags);
    }
}