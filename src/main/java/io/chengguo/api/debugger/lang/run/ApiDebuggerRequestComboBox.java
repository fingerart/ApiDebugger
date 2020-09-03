package io.chengguo.api.debugger.lang.run;

import com.intellij.ide.todo.MultiLineTodoRenderer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.psi.PsiFile;
import com.intellij.ui.*;
import com.intellij.ui.popup.list.IconListPopupRenderer;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ui.table.IconTableCellRenderer;
import io.chengguo.api.debugger.lang.ApiBlockConverter;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import org.jdesktop.swingx.renderer.DefaultListRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ApiDebuggerRequestComboBox extends ComboBox<ApiDebuggerRequestComboBox.RequestItem> {
    private static final Logger LOG = Logger.getInstance(ApiDebuggerRequestComboBox.class);

    public ApiDebuggerRequestComboBox() {
    }

    public void update(Project project, PsiFile psiFile, int selectedIndex) {
        List<RequestItem> items = findAllRequestItems(psiFile);
        RequestItem selectedRequestItem = findSelectedRequestItem(items, selectedIndex);
        setModel(new CollectionComboBoxModel<>(items));
        setSelectedItem(selectedRequestItem);
        setRenderer(new ColoredListCellRenderer<RequestItem>() {
            @Override
            protected void customizeCellRenderer(@NotNull JList<? extends RequestItem> list, RequestItem item, int index, boolean selected, boolean hasFocus) {
                append(item.index + " #");
                append(item.getMethod(), SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES);
                appendTextPadding(JBUIScale.scale(10));
                append(item.getUri(), SimpleTextAttributes.GRAYED_ATTRIBUTES);
            }
        });
    }

    @Nullable
    private RequestItem findSelectedRequestItem(List<RequestItem> items, int selectedIndex) {
        try {
            return items.get(Math.max(selectedIndex, 0));
        } catch (Exception ignored) {
        }
        return null;
    }

    private List<RequestItem> findAllRequestItems(PsiFile psiFile) {
        ApiApiBlock[] apiBlocks = ApiPsiUtils.findApiBlocks(psiFile);
        ArrayList<RequestItem> results = new ArrayList<>();
        for (int index = 0; index < apiBlocks.length; index++) {
            try {
                ApiDebuggerRequest request = ApiBlockConverter.toApiBlock(apiBlocks[index], ApiVariableReplacer.EMPTY);
                results.add(new RequestItem(index + 1, request.method, request.baseUrl, true));
            } catch (ApiRequestInvalidException e) {
                LOG.error(e);
                results.add(new RequestItem(index + 1, null, null, false));
            }
        }
        return results;
    }

    public static class RequestItem {
        private int index;
        private String method;
        private String uri;
        private boolean isValid;

        public RequestItem(int index, String method, String uri, boolean isValid) {
            this.index = index;
            this.method = method;
            this.uri = uri;
            this.isValid = isValid;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public boolean isValid() {
            return isValid;
        }

        public void setValid(boolean valid) {
            isValid = valid;
        }

        @Override
        public String toString() {
            return "RequestItem{" +
                    "method='" + method + '\'' +
                    ", uri='" + uri + '\'' +
                    ", index=" + index +
                    ", isValid=" + isValid +
                    '}';
        }
    }
}
