package io.chengguo.api.debugger.lang;

import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiRequest;
import io.chengguo.api.debugger.lang.psi.ApiRequestLine;
import io.chengguo.api.debugger.lang.psi.ApiRequestTarget;
import io.chengguo.api.debugger.lang.run.ApiRequestInvalidException;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;

import java.net.URI;

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
//        request.parameters = reqTargetElement.getParameters(replacer);

        return request;
    }
}
