package io.chengguo.api.debugger.lang.psi;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.PsiFile;
import io.chengguo.api.debugger.ApiDebuggerTestCase;
import io.chengguo.api.debugger.lang.ApiPsiUtils;

import java.io.File;
import java.io.IOException;

public class ApiPsiGeneratorTest extends ApiDebuggerTestCase {

    @Override
    protected String getBasePath() {
        return "util";
    }

    public void testCreateVariable() {
        ApiVariable username = ApiPsiGenerator.createVariable(getProject(), "username");
        assertEquals("{{username}}", username.getText());
    }

    public void testCreateDummyFile() throws IOException {
        File testFile = new File(getTestDataPath(), getTestName(true) + ".api");
        String content = FileUtil.loadFile(testFile);
        PsiFile dummyFile = ApiPsiGenerator.createDummyFile(getProject(), content);
        ApiApiBlock[] apiBlocks = ApiPsiUtils.findApiBlocks(dummyFile);
        assertEquals(1, apiBlocks.length);
    }
}