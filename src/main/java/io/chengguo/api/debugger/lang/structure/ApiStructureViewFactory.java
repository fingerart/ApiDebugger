package io.chengguo.api.debugger.lang.structure;

import com.intellij.ide.structureView.*;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * API文件结构视图构造器
 */
public class ApiStructureViewFactory implements PsiStructureViewFactory {
    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder(@NotNull PsiFile psiFile) {
        return new TreeBasedStructureViewBuilder() {
            @NotNull
            @Override
            public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
                StructureViewTreeElement root = ApiStructureViewElement.create(psiFile, ApiDebuggerBundle.message("api.debugger.action.name"), psiFile.getFileType().getIcon());
                return new ApiStructureViewModel(psiFile, editor, root);
            }

            @Override
            public boolean isRootNodeShown() {
                return true;
            }
        };
    }
}
