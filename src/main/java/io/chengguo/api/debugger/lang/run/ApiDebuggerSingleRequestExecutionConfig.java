package io.chengguo.api.debugger.lang.run;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiDescription;

import java.util.List;

public class ApiDebuggerSingleRequestExecutionConfig implements ApiDebuggerExecutionConfig {

    private final List<ApiApiBlock> mApiBlocks;

    public ApiDebuggerSingleRequestExecutionConfig(ApiApiBlock apiBlock) {
        mApiBlocks = ContainerUtil.newSmartList(apiBlock);
    }

    @Override
    public List<ApiApiBlock> getApiBlocks() {
        return mApiBlocks;
    }

    @Override
    public String getName() {
        ApiApiBlock apiApiBlock = mApiBlocks.get(0);
        ApiDescription titleDescriptionItem = apiApiBlock.getDescriptionByKey("title");
        String descrTitle = titleDescriptionItem != null ? titleDescriptionItem.getValue() : null;
        if (StringUtil.isNotEmpty(descrTitle)) {
            return descrTitle;
        }
        return apiApiBlock.getRequest().getRequestLine().getRequestTarget().getUrl(ApiVariableReplacer.EMPTY);
    }
}
