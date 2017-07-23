package io.chengguo.apidebugger.engine.http;

/**
 * Created by fingerart on 17/7/24.
 */
public class PatchRequestBuilder extends FormRequestBuilder {
    @Override
    public FormRequest build() {
        return new PatchRequest(this);
    }
}
