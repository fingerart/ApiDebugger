package io.chengguo.api.debugger.lang.run;

import com.intellij.psi.PsiFile;
import io.chengguo.api.debugger.lang.ApiPsiUtil;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;

import java.util.Arrays;
import java.util.List;

public class ApiDebuggerFileExecutionConfig implements ApiDebuggerExecutionConfig {

    private final PsiFile mFile;

    public ApiDebuggerFileExecutionConfig(PsiFile file) {
        mFile = file;
    }

    @Override
    public List<ApiApiBlock> getApiBlocks() {
        return Arrays.asList(ApiPsiUtil.findApiBlocks(mFile));
    }

    @Override
    public String getName() {
        return mFile.getName();
    }
}