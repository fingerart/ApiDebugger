package io.chengguo.api.debugger.ui.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.EventDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ApiFileEditor extends UserDataHolderBase implements FileEditor, PropertyChangeListener {
    private static final String NAME = "ApiFileEditor";

    private final Project mProject;
    private final VirtualFile mFile;
    private final ApiEditorImpl mEditor;
    private final EventDispatcher<PropertyChangeListener> mDispatcher = EventDispatcher.create(PropertyChangeListener.class);

    public ApiFileEditor(Project project, VirtualFile file) {
        mProject = project;
        mFile = file;
        mEditor = new ApiEditorImpl(project, file);
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return mEditor.getComponent();
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return mEditor.getComponent();
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setState(@NotNull FileEditorState state) {

    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return mEditor.isValid();
    }

    @Override
    public void selectNotify() {
    }

    @Override
    public void deselectNotify() {
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {
        mDispatcher.addListener(listener);
    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {
        mDispatcher.removeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        PropertyChangeEvent editorEvent = new PropertyChangeEvent(this, event.getPropertyName(), event.getOldValue(), event.getNewValue());
        mDispatcher.getMulticaster().propertyChange(editorEvent);
    }

    @Nullable
    @Override
    public BackgroundEditorHighlighter getBackgroundHighlighter() {
        return null;
    }

    @Nullable
    @Override
    public FileEditorLocation getCurrentLocation() {
        return null;
    }

    @Override
    public void dispose() {
    }

    public ApiEditor getApiEditor() {
        return mEditor;
    }
}
