package io.chengguo.api.debugger.ui.editor;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.TextEditor;
import org.jetbrains.annotations.NotNull;

public class ApiSplitEditor extends SplitFileEditor {
    public ApiSplitEditor(@NotNull TextEditor editor, @NotNull FileEditor preview, @NotNull String editorName) {
        super(editor, preview, editorName);
    }

    public ApiSplitEditor(@NotNull TextEditor editor, @NotNull FileEditor preview) {
        super(editor, preview);
    }

    @NotNull
    @Override
    protected Layout getDefaultLayout() {
        return Layout.SHOW_EDITOR;
    }

    private ActionGroup getActionGroupById(String groupId) {
        ActionManager actionManager = ActionManager.getInstance();
        if (!actionManager.isGroup(groupId)) {
            throw new IllegalStateException(groupId + " should have been a group");
        }
        return (ActionGroup) actionManager.getAction(groupId);
    }
}
