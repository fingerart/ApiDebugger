package io.chengguo.apidebugger.engine.http;

/**
 * Created by fingerart on 17/7/23.
 */
public class PutRequestBuilder extends FormRequestBuilder {

    @Override
    public FormRequest build() {
        return new PutRequest(this);
    }
}
