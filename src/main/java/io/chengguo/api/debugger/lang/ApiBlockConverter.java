package io.chengguo.api.debugger.lang;

import com.intellij.openapi.util.Pair;
import io.chengguo.api.debugger.lang.psi.*;
import io.chengguo.api.debugger.lang.run.ApiRequestInvalidException;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ApiBlock实体与Psi之间的转换
 */
public class ApiBlockConverter {

    public static ApiDebuggerRequest toApiBlock(ApiApiBlock element, ApiVariableReplacer replacer) throws ApiRequestInvalidException {
        ApiRequestLine reqLineElement = element.getRequest().getRequestLine();
        ApiRequestTarget reqTargetElement = reqLineElement.getRequestTarget();
        ApiDebuggerRequest request = new ApiDebuggerRequest(reqLineElement.getMethod().getText(), reqTargetElement.getUrl(replacer));
        request.addParameter(pairToMap(reqTargetElement.getParameters(replacer)));
        request.addHeader(pairToMap(element.getRequest().getHeaders(replacer)));
        ApiRequestBody requestBody = element.getRequest().getRequestBody();
        request.setRequestBody(requestBody == null ? "" : requestBody.getText());
        return request;
    }

    private static Map<String, String> pairToMap(List<Pair<String, String>> pairs) {
        HashMap<String, String> result = new HashMap<>();
        for (Pair<String, String> pair : pairs) {
            result.put(pair.first, pair.second);
        }
        return result;
    }


}
