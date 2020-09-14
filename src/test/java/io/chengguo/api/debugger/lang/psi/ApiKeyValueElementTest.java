package io.chengguo.api.debugger.lang.psi;

import io.chengguo.api.debugger.ApiDebuggerTestCase;
import io.chengguo.api.debugger.lang.ApiPsiUtil;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;

import java.util.List;

public class ApiKeyValueElementTest extends ApiDebuggerTestCase {
    private ApiVariableReplacer mVariableReplacer;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myFixture.copyFileToProject("env/api.env.json", "api.env.json");
        myFixture.configureByFile("psi/testKeyValueElement.api");
        mVariableReplacer = ApiVariableReplacer.create(ApiEnvironment.create(getProject(), "product"));
    }

    public void testGetKey() {
        ApiApiBlock apiBlock = ApiPsiUtil.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        ApiRequest request = apiBlock.getRequest();
        List<ApiHeaderField> headerFields = request.getHeaderFieldList();
        assertSize(2, headerFields);
        ApiHeaderField contentType = headerFields.get(0);
        assertEquals("Content-Type", contentType.getKey());
        ApiHeaderField xVersion = headerFields.get(1);
        assertEquals("X-App-Version", xVersion.getKey());
    }

    public void testGetValue() {
        ApiApiBlock apiBlock = ApiPsiUtil.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        ApiRequest request = apiBlock.getRequest();
        List<ApiHeaderField> headerFields = request.getHeaderFieldList();
        assertSize(2, headerFields);
        ApiHeaderField contentType = headerFields.get(0);
        assertEquals("application/x-www-form-urlencoded", contentType.getValue());
        ApiHeaderField xVersion = headerFields.get(1);
        assertEquals("{{version}}", xVersion.getValue());
        assertEquals("v1.0.0+1", xVersion.getValue(mVariableReplacer));
    }
}