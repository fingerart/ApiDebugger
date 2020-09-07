package io.chengguo.api.debugger.lang.psi;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.PsiFile;
import io.chengguo.api.debugger.ApiDebuggerTestCase;
import io.chengguo.api.debugger.lang.ApiPsiUtils;

import java.io.File;
import java.io.IOException;

public class ApiElementGeneratorTest extends ApiDebuggerTestCase {

    private ApiElementGenerator elementGenerator;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        elementGenerator = new ApiElementGenerator(getProject());
    }

    @Override
    protected String getBasePath() {
        return "util";
    }

    public void testCreateVariable() {
        ApiVariable username = elementGenerator.createVariable("username");
        assertEquals("{{username}}", username.getText());
    }

    public void testCreateDummyFile() throws IOException {
        File testFile = new File(getTestDataPath(), getTestName(true) + ".api");
        String content = FileUtil.loadFile(testFile);
        PsiFile dummyFile = elementGenerator.createDummyFile(content);
        ApiApiBlock[] apiBlocks = ApiPsiUtils.findApiBlocks(dummyFile);
        assertEquals(1, apiBlocks.length);
    }
}