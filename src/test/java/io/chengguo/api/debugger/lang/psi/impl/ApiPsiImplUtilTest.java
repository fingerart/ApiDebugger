package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.ApiDebuggerTestCase;
import io.chengguo.api.debugger.lang.ApiPsiUtil;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiDescription;
import io.chengguo.api.debugger.lang.psi.ApiRequestTarget;
import io.chengguo.api.debugger.lang.psi.ApiVariable;

import java.util.List;

public class ApiPsiImplUtilTest extends ApiDebuggerTestCase {

    @Override
    protected String getBasePath() {
        return "util";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myFixture.configureByFile("testPsiImplUtils.api");
    }

    public void testGetIdentifier() {
        ApiVariable variable = ApiPsiUtil.findFirstVariable(myFixture.getFile());
        assertNotNull(variable);
        PsiElement identifier = ApiPsiImplUtil.getIdentifier(variable);
        assertNotNull(identifier);
        assertEquals("address", identifier.getText());
    }

    public void testGetElementTextNotNull() {
        ApiVariable variable = ApiPsiUtil.findFirstVariable(myFixture.getFile());
        assertNotNull(variable);
        String idText = ApiPsiImplUtil.getElementTextNotNull(variable.getIdentifier());
        assertEquals("address", idText);
        String emptyIdText = ApiPsiImplUtil.getElementTextNotNull(null);
        assertNotNull(emptyIdText);
    }

    public void testGetScheme() {
        ApiApiBlock apiBlock = ApiPsiUtil.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        String scheme = ApiPsiImplUtil.getScheme(apiBlock.getRequest().getRequestLine().getRequestTarget().getScheme());
        assertEquals("https", scheme);
    }

    public void testGetUrl() {
        ApiApiBlock apiBlock = ApiPsiUtil.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        ApiRequestTarget requestTarget = apiBlock.getRequest().getRequestLine().getRequestTarget();
        String url = ApiPsiImplUtil.getUrl(requestTarget, ApiVariableReplacer.EMPTY);
        assertEquals("https://echo.tenon.dev/put/userinfo/", url);
    }

    public void testGetParameters() {
        ApiApiBlock apiBlock = ApiPsiUtil.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        List<Pair<String, String>> parameters = ApiPsiImplUtil.getParameters(apiBlock.getRequest().getRequestLine().getRequestTarget().getQuery(), ApiVariableReplacer.EMPTY);
        assertSize(3, parameters);
        assertEquals("apiDebugger", parameters.get(0).second);
        assertEquals("18", parameters.get(1).second);
    }

    public void testGetDescriptionKey() {
        ApiApiBlock apiBlock = ApiPsiUtil.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        ApiDescription titleDescription = apiBlock.getDescriptionByKey("title");
        assertNotNull(titleDescription);
        String key = titleDescription.getKey();
        assertEquals("title", key);
    }

    public void testGetDescriptionValue() {
        ApiApiBlock apiBlock = ApiPsiUtil.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        ApiDescription titleDescription = apiBlock.getDescriptionByKey("title");
        assertNotNull(titleDescription);
        String value = titleDescription.getValue();
        assertEquals("Create Dummy File", value);
    }
}