package io.chengguo.api.debugger.lang;

import com.intellij.openapi.util.Pair;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiRequest;
import io.chengguo.api.debugger.lang.psi.ApiRequestLine;
import io.chengguo.api.debugger.lang.psi.ApiRequestTarget;
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
        ApiRequest reqElement = element.getRequest();
        if (reqElement == null) {
            throw new ApiRequestInvalidException();
        }
        ApiRequestLine reqLineElement = reqElement.getRequestLine();
        ApiRequestTarget reqTargetElement = reqLineElement.getRequestTarget();
        ApiDebuggerRequest request = new ApiDebuggerRequest();
        request.method = reqLineElement.getMethod().getText();
        request.url = reqTargetElement.getUrl(replacer);
        request.parameters = pairToMap(reqTargetElement.getParameters(replacer));
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
