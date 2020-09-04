package io.chengguo.api.debugger;

import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import io.chengguo.api.debugger.lang.ApiFileType;

import java.io.File;

public abstract class ApiDebuggerTestCase extends BasePlatformTestCase {
    protected static final String EXT = ApiFileType.INSTANCE.getDefaultExtension();

    protected String mergeApiExt(String fileName) {
        return fileName + "." + EXT;
    }

    @Override
    protected String getTestDataPath() {
        return new File("testData/" + getBasePath()).getAbsolutePath();
    }
}
