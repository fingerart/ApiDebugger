package io.chengguo.api.debugger.lang.run;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiFile;
import com.intellij.ui.CollectionComboBoxModel;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import io.chengguo.api.debugger.lang.ApiBlockConverter;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ApiDebuggerRequestComboBox extends ComboBox<ApiDebuggerRequestComboBox.RequestItem> {
    private static final Logger LOG = Logger.getInstance(ApiDebuggerRequestComboBox.class);

    public ApiDebuggerRequestComboBox() {
    }

    public void update(Project project, String envName, PsiFile psiFile, int selectedIndex) {
        ApiEnvironment environment = ApiEnvironment.create(project, envName);
        ApiVariableReplacer variableReplacer = environment == ApiEnvironment.empty() ? ApiVariableReplacer.PLAIN : ApiVariableReplacer.create(environment);
        List<RequestItem> items = findAllRequestItems(psiFile, variableReplacer);
        RequestItem selectedRequestItem = findSelectedRequestItem(items, selectedIndex);
        setModel(new CollectionComboBoxModel<>(items));
        setSelectedItem(selectedRequestItem);
        setRenderer(new ColoredListCellRenderer<RequestItem>() {
            @Override
            protected void customizeCellRenderer(@NotNull JList<? extends RequestItem> list, RequestItem item, int index, boolean selected, boolean hasFocus) {
                append(String.format("%d # ", item.index), SimpleTextAttributes.GRAYED_ATTRIBUTES);
                append(String.format(" %s ", item.getMethod()), SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES);
                append(StringUtil.notNullize(item.getUri()));
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

    private List<RequestItem> findAllRequestItems(PsiFile psiFile, ApiVariableReplacer variableReplacer) {
        ApiApiBlock[] apiBlocks = ApiPsiUtils.findApiBlocks(psiFile);
        ArrayList<RequestItem> results = new ArrayList<>();
        for (int index = 0; index < apiBlocks.length; index++) {
            try {
                ApiDebuggerRequest request = ApiBlockConverter.toApiBlock(apiBlocks[index], variableReplacer);
                results.add(new RequestItem(index + 1, request.method, request.url, true));
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
