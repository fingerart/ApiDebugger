package io.chengguo.apidebugger.engine.http;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.TextUtils;

import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by fingerart on 17/7/11.
 */
public class XWwwUrlencodedRequest extends FormRequest {

    public XWwwUrlencodedRequest(FormRequestBuilder builder) {
        super(builder);
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        List<? extends NameValuePair> parameters = buildParams();

        HttpPost post = new HttpPost(mUrl);
        ArtHttp.addTag(post.toString(), post);
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, Charset.forName("UTF-8"));
        post.setEntity(entity);

        return post;
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
