package io.chengguo.api.debugger.lang;

import io.chengguo.api.debugger.ApiDebuggerTestCase;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.run.ApiRequestInvalidException;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;

public class ApiBlockConverterTest extends ApiDebuggerTestCase {

    private ApiVariableReplacer mVariableReplacer;

    @Override
    protected String getBasePath() {
        return "lang/converter";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myFixture.copyFileToProject("api.env.json");
        myFixture.copyFileToProject("testPostFile.txt");
        myFixture.copyFileToProject("testPostFile.png");
        myFixture.configureByFile("testApiBlockConverter.api");
        mVariableReplacer = ApiVariableReplacer.create(ApiEnvironment.create(getProject(), "converter"));
    }

    public void testToApiBlock() throws ApiRequestInvalidException {
        ApiApiBlock apiApiBlock = ApiPsiUtil.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiApiBlock);
        ApiDebuggerRequest request = ApiBlockConverter.toApiBlock(apiApiBlock, mVariableReplacer);
        System.out.println(request);
    }
}