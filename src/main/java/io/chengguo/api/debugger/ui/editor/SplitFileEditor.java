package io.chengguo.api.debugger.ui.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.icons.AllIcons;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.ui.JBSplitter;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Two panel editor with three states: Editor, Preview and Editor with Preview.
 */
public abstract class SplitFileEditor extends UserDataHolderBase implements FileEditor {
    protected final TextEditor mEditor;
    protected final FileEditor mPreview;
    @NotNull
    private final mListenersMultimap mListenersGenerator = new mListenersMultimap();
    private Layout mLayout;
    private JComponent mComponent;
    private SplitEditorToolbar mToolbarWrapper;
    private final String mName;

    public SplitFileEditor(@NotNull TextEditor editor, @NotNull FileEditor preview, @NotNull String editorName) {
        mEditor = editor;
        mPreview = preview;
        mName = editorName;
    }

    public SplitFileEditor(@NotNull TextEditor editor, @NotNull FileEditor preview) {
        this(editor, preview, "SplitFileEditor");
    }

    @Nullable
    @Override
    public BackgroundEditorHighlighter getBackgroundHighlighter() {
        return mEditor.getBackgroundHighlighter();
    }

    @Nullable
    @Override
    public FileEditorLocation getCurrentLocation() {
        return mEditor.getCurrentLocation();
    }

    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder() {
        return mEditor.getStructureViewBuilder();
    }

    @Override
    public void dispose() {
        Disposer.dispose(mEditor);
        Disposer.dispose(mPreview);
    }

    @Override
    public void selectNotify() {
        mEditor.selectNotify();
        mPreview.selectNotify();
    }

    @Override
    public void deselectNotify() {
        mEditor.deselectNotify();
        mPreview.deselectNotify();
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        if (mComponent == null) {
            final JBSplitter splitter = new JBSplitter(false, 0.5f, 0.15f, 0.85f);
            splitter.setSplitterProportionKey(getSplitterProportionKey());
            splitter.setFirstComponent(mEditor.getComponent());
            splitter.setSecondComponent(mPreview.getComponent());
            splitter.setDividerWidth(3);

            mToolbarWrapper = createToolbarWrapper(splitter);
            Disposer.register(this, mToolbarWrapper);

            if (mLayout == null) {
                String lastUsed = PropertiesComponent.getInstance().getValue(getLayoutPropertyName());
                mLayout = Layout.fromName(lastUsed, getDefaultLayout());
            }
            adjustEditorsVisibility();

            mComponent = JBUI.Panels.simplePanel(splitter).addToTop(mToolbarWrapper);
        }
        return mComponent;
    }

    @NotNull
    protected Layout getDefaultLayout() {
        return Layout.SHOW_EDITOR_AND_PREVIEW;
    }

    @NotNull
    private SplitEditorToolbar createToolbarWrapper(@NotNull JComponent targetComponentForActions) {
        ActionToolbar leftToolbar = createToolbar();
        if (leftToolbar != null) {
            leftToolbar.setTargetComponent(targetComponentForActions);
            leftToolbar.setReservePlaceAutoPopupIcon(false);
        }

        ActionToolbar rightToolbar = createRightToolbar();
        rightToolbar.setTargetComponent(targetComponentForActions);
        rightToolbar.setReservePlaceAutoPopupIcon(false);

        return new SplitEditorToolbar(leftToolbar, rightToolbar);
    }

    @Override
    public void setState(@NotNull FileEditorState state) {
        if (state instanceof mFileEditorState) {
            final mFileEditorState compositeState = (mFileEditorState) state;
            if (compositeState.getFirstState() != null) {
                mEditor.setState(compositeState.getFirstState());
            }
            if (compositeState.getSecondState() != null) {
                mPreview.setState(compositeState.getSecondState());
            }
            if (compositeState.getSplitLayout() != null) {
                mLayout = compositeState.getSplitLayout();
                invalidateLayout();
            }
        }
    }

    private void adjustEditorsVisibility() {
        mEditor.getComponent().setVisible(mLayout == Layout.SHOW_EDITOR || mLayout == Layout.SHOW_EDITOR_AND_PREVIEW);
        mPreview.getComponent().setVisible(mLayout == Layout.SHOW_PREVIEW || mLayout == Layout.SHOW_EDITOR_AND_PREVIEW);
    }

    private void invalidateLayout() {
        adjustEditorsVisibility();
        mToolbarWrapper.refresh();
        mComponent.repaint();

        final JComponent focusComponent = getPreferredFocusedComponent();
        if (focusComponent != null) {
            IdeFocusManager.findInstanceByComponent(focusComponent).requestFocus(focusComponent, true);
        }
    }

    @NotNull
    protected String getSplitterProportionKey() {
        return "SplitFileEditor.SplitterProportionKey";
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        switch (mLayout) {
            case SHOW_EDITOR_AND_PREVIEW:
            case SHOW_EDITOR:
                return mEditor.getPreferredFocusedComponent();
            case SHOW_PREVIEW:
                return mPreview.getPreferredFocusedComponent();
            default:
                throw new IllegalStateException(mLayout.mName);
        }
    }

    @NotNull
    @Override
    public String getName() {
        return mName;
    }

    @NotNull
    @Override
    public FileEditorState getState(@NotNull FileEditorStateLevel level) {
        return new mFileEditorState(mLayout, mEditor.getState(level), mPreview.getState(level));
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {
        mEditor.addPropertyChangeListener(listener);
        mPreview.addPropertyChangeListener(listener);

        final DoublingEventListenerDelegate delegate = mListenersGenerator.addListenerAndGetDelegate(listener);
        mEditor.addPropertyChangeListener(delegate);
        mPreview.addPropertyChangeListener(delegate);
    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {
        mEditor.removePropertyChangeListener(listener);
        mPreview.removePropertyChangeListener(listener);

        final DoublingEventListenerDelegate delegate = mListenersGenerator.removeListenerAndGetDelegate(listener);
        if (delegate != null) {
            mEditor.removePropertyChangeListener(delegate);
            mPreview.removePropertyChangeListener(delegate);
        }
    }

    @NotNull
    public TextEditor getTextEditor() {
        return mEditor;
    }

    public Layout getLayout() {
        return mLayout;
    }

    static class mFileEditorState implements FileEditorState {
        private final Layout mSplitLayout;
        private final FileEditorState mFirstState;
        private final FileEditorState mSecondState;

        mFileEditorState(Layout layout, FileEditorState firstState, FileEditorState secondState) {
            mSplitLayout = layout;
            mFirstState = firstState;
            mSecondState = secondState;
        }

        @Nullable
        public Layout getSplitLayout() {
            return mSplitLayout;
        }

        @Nullable
        public FileEditorState getFirstState() {
            return mFirstState;
        }

        @Nullable
        public FileEditorState getSecondState() {
            return mSecondState;
        }

        @Override
        public boolean canBeMergedWith(FileEditorState otherState, FileEditorStateLevel level) {
            return otherState instanceof mFileEditorState
                    && (mFirstState == null || mFirstState.canBeMergedWith(((mFileEditorState) otherState).mFirstState, level))
                    && (mSecondState == null || mSecondState.canBeMergedWith(((mFileEditorState) otherState).mSecondState, level));
        }
    }

    @Override
    public boolean isModified() {
        return mEditor.isModified() || mPreview.isModified();
    }

    @Override
    public boolean isValid() {
        return mEditor.isValid() && mPreview.isValid();
    }

    private class DoublingEventListenerDelegate implements PropertyChangeListener {
        @NotNull
        private final PropertyChangeListener mDelegate;

        private DoublingEventListenerDelegate(@NotNull PropertyChangeListener delegate) {
            mDelegate = delegate;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            mDelegate.propertyChange(
                    new PropertyChangeEvent(SplitFileEditor.this, evt.getPropertyName(), evt.getOldValue(), evt.getNewValue()));
        }
    }

    private class mListenersMultimap {
        private final Map<PropertyChangeListener, Pair<Integer, DoublingEventListenerDelegate>> mMap = new HashMap<>();

        @NotNull
        public DoublingEventListenerDelegate addListenerAndGetDelegate(@NotNull PropertyChangeListener listener) {
            if (!mMap.containsKey(listener)) {
                mMap.put(listener, Pair.create(1, new DoublingEventListenerDelegate(listener)));
            } else {
                final Pair<Integer, DoublingEventListenerDelegate> oldPair = mMap.get(listener);
                mMap.put(listener, Pair.create(oldPair.getFirst() + 1, oldPair.getSecond()));
            }

            return mMap.get(listener).getSecond();
        }

        @Nullable
        public DoublingEventListenerDelegate removeListenerAndGetDelegate(@NotNull PropertyChangeListener listener) {
            final Pair<Integer, DoublingEventListenerDelegate> oldPair = mMap.get(listener);
            if (oldPair == null) {
                return null;
            }

            if (oldPair.getFirst() == 1) {
                mMap.remove(listener);
            } else {
                mMap.put(listener, Pair.create(oldPair.getFirst() - 1, oldPair.getSecond()));
            }
            return oldPair.getSecond();
        }
    }

    @Nullable
    protected ActionToolbar createToolbar() {
        ActionGroup actionGroup = createLeftToolbarActionGroup();
        if (actionGroup != null) {
            return ActionManager.getInstance().createActionToolbar("SplitFileEditor", actionGroup, true);
        } else {
            return null;
        }
    }

    @Nullable
    protected ActionGroup createLeftToolbarActionGroup() {
        return null;
    }

    @NotNull
    private ActionToolbar createRightToolbar() {
        final ActionGroup viewActions = createViewActionGroup();
        final ActionGroup group = createRightToolbarActionGroup();
        final ActionGroup rightToolbarActions = group == null
                ? viewActions
                : new DefaultActionGroup(group, Separator.create(), viewActions);
        return ActionManager.getInstance().createActionToolbar("SplitFileEditor", rightToolbarActions, true);
    }

    @NotNull
    protected ActionGroup createViewActionGroup() {
        return new DefaultActionGroup(
                getShowEditorAction(),
                getShowPreviewAction()
        );
    }

    @Nullable
    protected ActionGroup createRightToolbarActionGroup() {
        return null;
    }

    @NotNull
    protected ToggleAction getShowEditorAction() {
        return new ChangeViewModeAction(Layout.SHOW_EDITOR);
    }

    @NotNull
    protected ToggleAction getShowPreviewAction() {
        return new ChangeViewModeAction(Layout.SHOW_PREVIEW);
    }

    @NotNull
    protected ToggleAction getShowEditorAndPreviewAction() {
        return new ChangeViewModeAction(Layout.SHOW_EDITOR_AND_PREVIEW);
    }

    public enum Layout {
        SHOW_EDITOR("Editor only", AllIcons.General.LayoutEditorOnly),
        SHOW_PREVIEW("Preview only", AllIcons.General.LayoutPreviewOnly),
        SHOW_EDITOR_AND_PREVIEW("Editor and Preview", AllIcons.General.LayoutEditorPreview);

        private final String mName;
        private final Icon mIcon;

        Layout(String name, Icon icon) {
            mName = name;
            mIcon = icon;
        }

        public static Layout fromName(String name, Layout defaultValue) {
            for (Layout layout : Layout.values()) {
                if (layout.mName.equals(name)) {
                    return layout;
                }
            }
            return defaultValue;
        }

        public String getName() {
            return mName;
        }

        public Icon getIcon() {
            return mIcon;
        }
    }

    private class ChangeViewModeAction extends ToggleAction implements DumbAware {
        private final Layout mActionLayout;

        ChangeViewModeAction(Layout layout) {
            super(layout.getName(), layout.getName(), layout.getIcon());
            mActionLayout = layout;
        }

        @Override
        public boolean isSelected(@NotNull AnActionEvent e) {
            return mLayout == mActionLayout;
        }

        @Override
        public void setSelected(@NotNull AnActionEvent e, boolean state) {
            if (state) {
                mLayout = mActionLayout;
                PropertiesComponent.getInstance().setValue(getLayoutPropertyName(), mLayout.mName, Layout.SHOW_EDITOR_AND_PREVIEW.mName);
                adjustEditorsVisibility();
            }
        }
    }

    @NotNull
    private String getLayoutPropertyName() {
        return mName + "Layout";
    }
}