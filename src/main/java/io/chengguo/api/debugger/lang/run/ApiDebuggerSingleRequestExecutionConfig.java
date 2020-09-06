package io.chengguo.api.debugger.lang.run;

import com.intellij.util.containers.ContainerUtil;
import com.twelvemonkeys.util.CollectionUtil;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;

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
        return mApiBlocks.get(0).getDescription().getDescriptionTitle().getDescriptionItem().getText();
    }
}
