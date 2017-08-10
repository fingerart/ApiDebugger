package io.chengguo.apidebugger.presenter;

import io.chengguo.apidebugger.engine.http.ArtHttp;
import io.chengguo.apidebugger.engine.http.PostRequestBuilder;
import io.chengguo.apidebugger.engine.interf.ArtHttpListener;
import io.chengguo.apidebugger.engine.log.Log;
import io.chengguo.apidebugger.engine.utils.IOUtil;
import io.chengguo.apidebugger.engine.utils.StringUtils;
import io.chengguo.apidebugger.ui.iview.IHttpView;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.TextUtils;
import org.codehaus.jettison.json.JSONException;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

/**
 * Created by fingerart on 17/7/11.
 */
public class DebuggerSession implements ArtHttpListener {

    private IHttpView mView;

    public DebuggerSession(IHttpView view) {
        mView = view;
    }

    /**
     * 执行http请求
     */
    public void execute() {
        String url = mView.url().trim();
        if (validUrl(url)) {
            System.out.println("url not empty");
            return;
        }

        Map<String, String> headers = mView.headers();

        switch (mView.method()) {
            case "GET":
                get(url, headers);
                break;
            case "POST":
                post(url, headers, mView.bodyType());
                break;
            case "PUT":
                put(url, headers, mView.bodyType());
                break;
            case "PATCH":
                patch(url, headers, mView.bodyType());
                break;
            case "DELETE":
                delete(url, headers);
                break;
            case "HEAD":
                head(url, headers);
                break;
            case "OPTIONS":
                options(url, headers);
                break;
            case "TRACE":
                trace(url, headers);
                break;
        }
    }

    private void get(String url, Map<String, String> headers) {
        ArtHttp.get()
                .url(url)
                .addHeader(headers)
                .build()
                .execute(this);
    }

    private void post(String url, Map<String, String> headers, String bodyType) {
        PostRequestBuilder builder = ArtHttp.post().url(url).addHeader(headers);

        convertContentType(builder, bodyType);

        builder.build().execute(this);
    }

    private void put(String url, Map<String, String> headers, String bodyType) {
        PostRequestBuilder builder = ArtHttp.put().url(url).addHeader(headers);

        convertContentType(builder, bodyType);

        builder.build().execute(this);
    }

    private void patch(String url, Map<String, String> headers, String bodyType) {
        PostRequestBuilder builder = ArtHttp.patch().url(url).addHeader(headers);

        convertContentType(builder, bodyType);

        builder.build().execute(this);
    }

    private void delete(String url, Map<String, String> headers) {
        ArtHttp.delete()
                .url(url)
                .addHeader(headers)
                .build()
                .execute(this);
    }

    private void head(String url, Map<String, String> headers) {
        ArtHttp.head().url(url).addHeader(headers).build().execute(this);
    }

    private void options(String url, Map<String, String> headers) {
        ArtHttp.options().url(url).addHeader(headers).build().execute(this);
    }

    private void trace(String url, Map<String, String> headers) {
        ArtHttp.trace().url(url).addHeader(headers).build().execute(this);
    }

    /**
     * set ContentType
     *
     * @param builder
     * @param bodyType
     */
    private void convertContentType(PostRequestBuilder builder, String bodyType) {
        switch (bodyType) {
            case "FormData"://todo 情况复杂！！
                Map<String, String> formData = mView.bodyFormData();
                builder.addParam(formData).formData();
                break;
            case "XWwwFormUrlencoded":
                Map<String, String> urlencoded = mView.bodyUrlencode();
                builder.addParam(urlencoded).xWwwUrlencoded();
                break;
            case "Raw":
                String raw = mView.bodyRaw();
                builder.raw(raw);
                break;
            case "Binary":
                String filePath = mView.bodyBinary();
                File file = new File(filePath);
                builder.binary(file);
                break;
            default:
                Log.e("error type");
        }
    }

    private boolean validUrl(String url) {
        return TextUtils.isEmpty(url);
    }

    @Override
    public void onPre() {
        System.out.println("DebuggerSession.onPre");
    }

    @Override
    public void onSuccess(HttpResponse response) {
        try {
            Vector<String> cookies = new Vector<>();
            Vector<String> headers = new Vector<>();
            Header[] allHeaders = response.getAllHeaders();
            for (Header header : allHeaders) {
                if ("Set-Cookie".equals(header.getName())) {
                    for (String cg : header.getValue().trim().split(";")) {
                        String[] cookie = cg.trim().split("=");
                        cookies.add(cookie[0] + " → " + cookie[1]);
                    }
                } else {
                    headers.add(header.getName() + " → " + header.getValue());
                }
            }

            mView.setCookies(cookies);
            mView.setHeaders(headers);

            String text = IOUtil.outputString(response.getEntity().getContent());
            Header[] contentTypes = response.getHeaders("Content-Type");
            String textFormat = formatContent(text, contentTypes);
            Log.d(textFormat);
            SwingUtilities.invokeLater(() -> {
                mView.showPretty(textFormat, contentTypes);
                mView.showRaw(text);
                mView.showPreview(textFormat);
            });
        } catch (JSONException e) {
            Log.e(e);
        } catch (IOException e) {
            Log.e(e);
        } catch (TransformerException e) {
            Log.e(e);
        }
    }

    private String formatContent(String text, Header[] contentTypes) throws JSONException, TransformerException {
        if (contentTypes == null || contentTypes.length == 0) {
            return text;
        }
        Header contentType = contentTypes[0];
        if (contentType.getValue().contains("text/html")) {
            return StringUtils.formatHtml(text);
        } else if (contentType.getValue().contains("application/xml")) {
            return StringUtils.formatXml(text);
        } else if (contentType.getValue().contains("application/json")) {
            return StringUtils.formatJson(text);
        }
        return text;
    }

    @Override
    public void onError(Exception e) {
        System.out.println("e = [" + e + "]");
    }

    @Override
    public void onFinish() {
        System.out.println("DebuggerSession.onFinish");
    }
}










