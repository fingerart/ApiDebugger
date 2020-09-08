package io.chengguo.api.debugger.ui.editor;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.ui.components.JBLabel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class ApiEditorUI extends JPanel implements DataProvider, Disposable {
    public ApiEditorUI(ApiEditor editor) {
        setLayout(new BorderLayout());
        add(new JBLabel("测试"), BorderLayout.NORTH);
    }

    @Nullable
    @Override
    public Object getData(@NotNull String dataId) {
        return null;
    }

    @Override
    public void dispose() {
        removeAll();
    }
}
