package io.chengguo.api.debugger.lang;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class ApiPsiFile extends PsiFileBase {
    protected ApiPsiFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, ApiLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return ApiFileType.INSTANCE;
    }
}
