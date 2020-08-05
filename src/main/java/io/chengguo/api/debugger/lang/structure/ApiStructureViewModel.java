package io.chengguo.api.debugger.lang.structure;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApiStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
    public ApiStructureViewModel(@NotNull PsiFile psiFile, @Nullable Editor editor, @NotNull StructureViewTreeElement root) {
        super(psiFile, editor, root);
    }

    public ApiStructureViewModel(@NotNull PsiFile psiFile, @NotNull StructureViewTreeElement root) {
        super(psiFile, root);
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return false;
    }
}
