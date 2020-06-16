package io.chengguo.api.debugger.lang;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ApiPsiFile extends PsiFileBase {
    public ApiPsiFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, ApiLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return ApiFileType.INSTANCE;
    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        return ApiDebuggerIcons.FAVICON;
    }

    @Override
    public String toString() {
        return ApiDebuggerBundle.message("api.debugger.action.description");
    }
}
