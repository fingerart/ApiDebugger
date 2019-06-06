package io.chengguo.api.debugger.api.spi;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.FileElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

class ApiFileViewProvider extends AbstractFileViewProvider {

    protected ApiFileViewProvider(@NotNull PsiManager manager, @NotNull VirtualFile virtualFile, boolean eventSystemEnabled, @NotNull FileType type) {
        super(manager, virtualFile, eventSystemEnabled, type);
    }

    @Nullable
    @Override
    protected PsiFile getPsiInner(Language target) {
        return null;
    }

    @Override
    public PsiFile getCachedPsi(@NotNull Language target) {
        return null;
    }

    @NotNull
    @Override
    public List<PsiFile> getCachedPsiFiles() {
        return null;
    }

    @NotNull
    @Override
    public List<FileElement> getKnownTreeRoots() {
        return null;
    }

    @NotNull
    @Override
    public Language getBaseLanguage() {
        return null;
    }

    @NotNull
    @Override
    public Set<Language> getLanguages() {
        return null;
    }

    @NotNull
    @Override
    public List<PsiFile> getAllFiles() {
        return null;
    }

    @Nullable
    @Override
    public PsiElement findElementAt(int offset) {
        return null;
    }

    @Nullable
    @Override
    public PsiReference findReferenceAt(int offset) {
        return null;
    }

    @Nullable
    @Override
    public PsiElement findElementAt(int offset, @NotNull Class<? extends Language> lang) {
        return null;
    }

    @NotNull
    @Override
    public FileViewProvider createCopy(@NotNull VirtualFile copy) {
        return null;
    }
}
