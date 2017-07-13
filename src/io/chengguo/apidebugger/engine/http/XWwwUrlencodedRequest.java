package io.chengguo.apidebugger.engine.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by fingerart on 17/7/11.
 */
public class XWwwUrlencodedRequest extends FormRequest {

    public XWwwUrlencodedRequest(FormRequestBuilder builder) {
        super(builder);
    }

    @NotNull
    @Override
    protected HttpEntity getEntity() {
        return new UrlEncodedFormEntity(buildParams(), Charset.forName("UTF-8"));
    }

    private List<? extends NameValuePair> buildParams() {
        ArrayList<NameValuePair> ps = new ArrayList<>();
        if (mParams != null) {
            Iterator<Map.Entry<String, String>> i = mParams.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry<String, String> next = i.next();
                if (!TextUtils.isEmpty(next.getKey().trim())) {
                    ps.add(new BasicNameValuePair(next.getKey(), next.getValue()));
                }
            }
        }
        return ps;
    }
}
