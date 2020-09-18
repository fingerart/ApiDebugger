package io.chengguo.api.debugger.lang;

import com.intellij.openapi.util.io.StreamUtil;
import io.chengguo.api.debugger.ApiDebuggerTestCase;
import io.chengguo.api.debugger.lang.environment.ApiEnvironment;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.run.ApiRequestInvalidException;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import io.chengguo.api.debugger.ui.ApiFormBodyPart;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.IOException;
import java.util.List;

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
        assertEquals("https://tenon.dev/post", request.getUrl());
        assertEquals("https://tenon.dev/post?name=tenon&age=18&language=go", request.getFullUrl());
        assertSize(3, request.getParameters());
        assertEquals("application/octet-stream", request.getHeader("Content-Type").value);
        assertEquals("/src/testPostFile.txt", request.getFileToSend());
    }

    public void testToApiBlock2() throws ApiRequestInvalidException, IOException {
        ApiApiBlock[] apiBlocks = ApiPsiUtil.findApiBlocks(myFixture.getFile());
        assertSize(2, apiBlocks);
        ApiApiBlock apiApiBlock = apiBlocks[1];
        ApiDebuggerRequest request = ApiBlockConverter.toApiBlock(apiApiBlock, mVariableReplacer);
        assertEquals("http://tenon.dev/post", request.getUrl());
        assertEquals("multipart/form-data; boundary=__X__BOUNDARY__", request.getHeader("Content-Type").value);
        assertEquals("__X__BOUNDARY__", request.getMultipartBoundary());
        List<ApiFormBodyPart> formBodyParts = request.getFormBodyParts();
        assertSize(4, formBodyParts);

        FormBodyPart formBodyPart1 = formBodyParts.get(0).toFormBodyPart();
        assertEquals("txt", formBodyPart1.getName());
        ContentBody body1 = formBodyPart1.getBody();
        assertInstanceOf(body1, FileBody.class);
        assertEquals("testPostFile.txt", body1.getFilename());
        assertEquals("text/plain", body1.getMimeType());
        assertEquals("/src/testPostFile.txt", ((FileBody) body1).getFile().getPath());

        FormBodyPart formBodyPart2 = formBodyParts.get(1).toFormBodyPart();
        assertEquals("userInfo", formBodyPart2.getName());
        ContentBody body2 = formBodyPart2.getBody();
        assertInstanceOf(body2, StringBody.class);
        assertEquals("application/json", body2.getMimeType());
        assertEquals("{\n" +
                "    \"username\": \"go\"\n" +
                "}", StreamUtil.readTextFrom(((StringBody) body2).getReader()));

        FormBodyPart formBodyPart3 = formBodyParts.get(2).toFormBodyPart();
        assertEquals("avator", formBodyPart3.getName());
        ContentBody body3 = formBodyPart3.getBody();
        assertInstanceOf(body3, FileBody.class);
        assertEquals("avator.png", body3.getFilename());
        assertEquals("/src/testPostFile.png", ((FileBody) body3).getFile().getPath());

        FormBodyPart formBodyPart4 = formBodyParts.get(3).toFormBodyPart();
        assertEquals("3", formBodyPart4.getName());
        assertEmpty(StreamUtil.readTextFrom(((StringBody) formBodyPart4.getBody()).getReader()));
    }
}