package io.chengguo.api.debugger.lang;

import com.intellij.openapi.util.Pair;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.psi.*;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.run.ApiRequestInvalidException;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;

import java.util.Collections;
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
        ApiBodyMessageElement bodyMessageElement = element.getRequest().getBody();

        if (bodyMessageElement instanceof ApiRequestMessageGroup) {
            List<ApiRequestMessageElement> requestMessages = ((ApiRequestMessageGroup) bodyMessageElement).getRequestMessageList();
            if (!requestMessages.isEmpty()) {
                if (requestMessages.size() == 1 && requestMessages.get(0) instanceof ApiInputFile) {
                    // 单个文件，记录文件的路径

                } else {
                    // 文件和文本的混合，解析为文本

                }
            }
        } else if (bodyMessageElement instanceof ApiMultipartMessage) {

        }

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
