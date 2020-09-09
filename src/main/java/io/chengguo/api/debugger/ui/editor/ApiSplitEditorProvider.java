package io.chengguo.api.debugger.ui.editor;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import io.chengguo.api.debugger.lang.ApiFileType;
import org.jetbrains.annotations.NotNull;

/**
 * 参考
 *
 * @see org.intellij.images.editor.impl.ImageFileEditorProvider
 * @see org.intellij.images.editor.impl.ImageFileEditorState
 * @see org.intellij.images.editor.impl.ImageEditorImpl
 * @see org.intellij.images.editor.impl.ImageEditorUI
 * @see org.intellij.images.editor.ImageEditor
 */
public class ApiSplitEditorProvider implements FileEditorProvider {

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return ApiFileType.INSTANCE.equals(file.getFileType());
    }

    @NotNull
    @Override
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        TextEditor textEditor = (TextEditor) TextEditorProvider.getInstance().createEditor(project, file);
        ApiFileEditor fileEditor = new ApiFileEditor(project, file);
        return new ApiSplitEditor(textEditor, fileEditor, "ApiDebugger");
    }

    @NotNull
    @Override
    public String getEditorTypeId() {
        return "api-debugger-editor";
    }

    @NotNull
    @Override
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }
}
