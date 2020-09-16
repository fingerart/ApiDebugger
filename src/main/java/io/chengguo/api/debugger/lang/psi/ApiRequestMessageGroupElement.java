package io.chengguo.api.debugger.lang.psi;

import java.util.List;

public interface ApiRequestMessageGroupElement extends ApiElement{
    public List<ApiRequestMessageElement> getRequestMessageList();
}
