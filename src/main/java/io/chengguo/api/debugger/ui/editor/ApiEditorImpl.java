package io.chengguo.api.debugger.ui.editor;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;

public class ApiEditorImpl implements ApiEditor {

    private final ApiEditorUI mEditorUI;
    private final Project mProject;
    private final VirtualFile mFile;

    public ApiEditorImpl(Project project, VirtualFile file) {
        mProject = project;
        mFile = file;
        mEditorUI = new ApiEditorUI(this);
    }

    @Override
    public JComponent getComponent() {
        return mEditorUI;
    }

    @Override
    public boolean isValid() {
        return mFile.isValid();
    }
}
