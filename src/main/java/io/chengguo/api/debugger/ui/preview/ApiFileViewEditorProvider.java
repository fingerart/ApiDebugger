package io.chengguo.api.debugger.ui.preview;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.WeighedFileEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import io.chengguo.api.debugger.lang.ApiFileType;
import org.jetbrains.annotations.NotNull;

public class ApiFileViewEditorProvider extends WeighedFileEditorProvider {

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return ApiFileType.INSTANCE.equals(file.getFileType());
    }

    @NotNull
    @Override
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        return new ApiFileViewEditor(project, file);
    }

    @NotNull
    @Override
    public String getEditorTypeId() {
        return "api-debugger-editor-view";
    }

    @NotNull
    @Override
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.PLACE_BEFORE_DEFAULT_EDITOR;
    }
}
