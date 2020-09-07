package io.chengguo.api.debugger.lang;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.TokenSet;
import io.chengguo.api.debugger.ApiDebuggerTestCase;
import io.chengguo.api.debugger.lang.psi.*;

public class ApiPsiUtilsTest extends ApiDebuggerTestCase {
    private static final String FILE_NAME = "testPsiUtils.api";

    @Override
    protected String getBasePath() {
        return "util";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myFixture.configureByFile(FILE_NAME);
    }

    public void testIsOfTypes() {
        ApiApiBlock apiBlock = ApiPsiUtils.findFirstApiBlock(myFixture.getFile());
        assertTrue(apiBlock != null && apiBlock.getRequest() != null);
        ApiPsiUtils.isOfTypes(apiBlock.getRequest().getRequestLine().getMethod(), TokenSet.create(
                ApiTypes.Api_GET,
                ApiTypes.Api_POST,
                ApiTypes.Api_PUT
        ));
    }

    public void testFindFirstApiBlock() {
        ApiApiBlock apiBlock = ApiPsiUtils.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
    }

    public void testFindApiBlocks() {
        ApiApiBlock[] apiBlocks = ApiPsiUtils.findApiBlocks(myFixture.getFile());
        assertSize(1, apiBlocks);
    }

    public void testFindFirstVariable() {
        ApiVariable variable = ApiPsiUtils.findFirstVariable(myFixture.getFile());
        assertNotNull(variable);
        assertEquals("address", variable.getName());
    }

    public void testFindVariables() {
        ApiVariable[] variables = ApiPsiUtils.findVariables(myFixture.getFile());
        assertSize(1, variables);
        assertEquals("address", variables[0].getName());
    }

    public void testFindFileByPath() {
        String path = getTestDataPath() + "/" + FILE_NAME;
        PsiFile psiFile = ApiPsiUtils.findFileByPath(getProject(), path);
        assertNotNull(psiFile);
        assertSame(psiFile.getFileType(), ApiFileType.INSTANCE);
    }

    public void testIsLeafElement() {
        ApiVariable variable = ApiPsiUtils.findFirstVariable(myFixture.getFile());
        assertNotNull(variable);

        boolean isLeafElement = ApiPsiUtils.isLeafElement(variable);
        assertFalse(isLeafElement);

        isLeafElement = ApiPsiUtils.isLeafElement(variable.getIdentifier());
        assertTrue(isLeafElement);
    }

    public void testSkipWhitespacesForward() {
        int offset = ApiPsiUtils.skipWhitespacesForward(0, "  This is my test text.");
        assertEquals(2, offset);
    }

    public void testIsOfType() {
        ApiVariable variable = ApiPsiUtils.findFirstVariable(myFixture.getFile());
        assertNotNull(variable);
        boolean ofType = ApiPsiUtils.isOfType(variable, ApiTypes.Api_VARIABLE);
        assertTrue(ofType);
    }

    public void testGetPrevSiblingIgnoreWhitespace() {
        ApiApiBlock apiBlock = ApiPsiUtils.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        ApiRequest requestElement = apiBlock.getRequest();
        PsiElement psiElement = ApiPsiUtils.getPrevSiblingIgnoreWhitespace(requestElement);
        assertNotNull(psiElement);
        assertEquals(ApiTypes.Api_DESCRIPTION, psiElement.getNode().getElementType());
    }

    public void testGetNextSiblingByType() {
        ApiApiBlock apiBlock = ApiPsiUtils.findFirstApiBlock(myFixture.getFile());
        assertNotNull(apiBlock);
        ApiDescription descriptionElement = apiBlock.getDescription();
        PsiElement psiElement = ApiPsiUtils.getNextSiblingByType(descriptionElement, ApiTypes.Api_REQUEST, true);
        assertNotNull(psiElement);
    }
}