package io.chengguo.api.debugger.lang.psi.impl;

import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiElement;
import io.chengguo.api.debugger.ApiDebuggerTestCase;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiVariable;

import java.util.List;

public class ApiPsiImplUtilsTest extends ApiDebuggerTestCase {

    @Override
    protected String getBasePath() {
        return "util";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myFixture.configureByFile("testPsiUtils.api");
    }

    public void testGetIdentifier() {
        ApiVariable variable = ApiPsiUtils.findFirstVariable(myFixture.getFile());
        assertNotNull(variable);
        PsiElement identifier = ApiPsiImplUtils.getIdentifier(variable);
        assertNotNull(identifier);
        assertEquals("address", identifier.getText());
    }

    public void testGetElementTextNotNull() {
        ApiVariable variable = ApiPsiUtils.findFirstVariable(myFixture.getFile());
        assertNotNull(variable);
        String idText = ApiPsiImplUtils.getElementTextNotNull(variable.getIdentifier());
        assertEquals("address", idText);
        String emptyIdText = ApiPsiImplUtils.getElementTextNotNull(null);
        assertNotNull(emptyIdText);
    }

    public void testGetScheme() {
        ApiApiBlock apiBlock = ApiPsiUtils.findFirstApiBlock(myFixture.getFile());
        assertTrue(apiBlock != null && apiBlock.getRequest() != null);
        String scheme = ApiPsiImplUtils.getScheme(apiBlock.getRequest().getRequestLine().getRequestTarget().getScheme());
        assertEquals("https", scheme);
    }

    public void testGetUrl() {
        ApiApiBlock apiBlock = ApiPsiUtils.findFirstApiBlock(myFixture.getFile());
        assertTrue(apiBlock != null && apiBlock.getRequest() != null);
        String url = ApiPsiImplUtils.getUrl(apiBlock.getRequest().getRequestLine().getRequestTarget(), ApiVariableReplacer.EMPTY);
        assertEquals("https://echo.tenon.dev/post", url);
    }

    public void testGetParameters() {
        ApiApiBlock apiBlock = ApiPsiUtils.findFirstApiBlock(myFixture.getFile());
        assertTrue(apiBlock != null && apiBlock.getRequest() != null);
        List<Pair<String, String>> parameters = ApiPsiImplUtils.getParameters(apiBlock.getRequest().getRequestLine().getRequestTarget().getQuery(), ApiVariableReplacer.EMPTY);
        assertSize(3, parameters);
        assertEquals("apiDebugger", parameters.get(0).second);
        assertEquals("18", parameters.get(1).second);
    }

    public void testGetDescriptionKey() {
        ApiApiBlock apiBlock = ApiPsiUtils.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        String key = apiBlock.getDescription().getDescriptionTitle().getDescriptionItem().getKey();
        assertEquals("title", key);
    }

    public void testGetDescriptionValue() {
        ApiApiBlock apiBlock = ApiPsiUtils.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        String value = apiBlock.getDescription().getDescriptionTitle().getDescriptionItem().getValue();
        assertEquals("Create Dummy File", value);
    }
}