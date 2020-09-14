package io.chengguo.api.debugger.lang;

import com.intellij.openapi.vfs.VirtualFile;
import io.chengguo.api.debugger.ApiDebuggerTestCase;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiQuery;
import io.chengguo.api.debugger.lang.psi.ApiVariable;

public class ApiVariableReplacerTest extends ApiDebuggerTestCase {

    private VirtualFile mApiFile;
    private ApiVariableReplacer variableReplacer;

    public void setUp() throws Exception {
        super.setUp();
        myFixture.copyFileToProject("env/api.env.json");
        mApiFile = myFixture.copyFileToProject("variable/testVariableReplacer.api");
        variableReplacer = ApiVariableReplacer.create(ApiEnvironment.create(getProject(), "product"));
    }

    public void testGetValue() {
        ApiVariable variable = ApiPsiUtil.findFirstVariable(ApiPsiUtil.findFileByVF(getProject(), mApiFile));
        assertNotNull(variable);
        String host = variableReplacer.getValue(variable);
        assertEquals("tenon.dev", host);
    }

    public void testGetValueByDeepTraversal() {
        ApiApiBlock apiBlock = ApiPsiUtil.findFirstApiBlock(ApiPsiUtil.findFileByVF(getProject(), mApiFile));
        assertNotNull(apiBlock);
        assertNotNull(apiBlock.getRequest());
        ApiQuery apiQuery = apiBlock.getRequest().getRequestLine().getRequestTarget().getQuery();
        String query = variableReplacer.getValue(apiQuery, true);
        assertEquals("name=tenon&age=18&language=go", query);
    }
}