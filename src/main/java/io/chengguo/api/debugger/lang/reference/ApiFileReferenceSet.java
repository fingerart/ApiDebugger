package io.chengguo.api.debugger.lang.reference;

import com.intellij.ide.scratch.ScratchUtil;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vcs.changes.ignore.reference.IgnoreReferenceSet;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.ManagingFS;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @see IgnoreReferenceSet
 */
public class ApiFileReferenceSet extends FileReferenceSet {
    public ApiFileReferenceSet(String str, @NotNull PsiElement element, int startInElement, PsiReferenceProvider provider, boolean caseSensitive, boolean endingSlashNotAllowed, @Nullable FileType[] suitableFileTypes) {
        super(str, element, startInElement, provider, caseSensitive, endingSlashNotAllowed, suitableFileTypes);
    }
    protected boolean isUrlEncoded() {
        return true;
    }

    protected boolean isSoft() {
        return true;
    }

    @NotNull
    public Collection<PsiFileSystemItem> computeDefaultContexts() {
        PsiFile containingFile = getContainingFile();
        if (containingFile == null) {
            return super.computeDefaultContexts();
        }
        if (isAbsolutePathReference()) {
            final List<VirtualFile> files = new ArrayList<>();
            ContainerUtil.addAll(files, ManagingFS.getInstance().getLocalRoots());
            ContainerUtil.addAll(files, containingFile.getVirtualFile().getFileSystem().findFileByPath("/"));
            return toFileSystemItems(files);
        }
        Collection<PsiFileSystemItem> items = ContainerUtil.newSmartList();
        if (ScratchUtil.isScratch(containingFile.getVirtualFile())) {
            items.addAll(toFileSystemItems(ProjectRootManager.getInstance(containingFile.getProject()).getContentRoots()));
        }
        else {
            items.addAll(super.computeDefaultContexts());
        }
        return items;
    }

}
