package me.fingerart.test;

import me.fingerart.idea.engine.interf.ArtHttpListener;
import me.fingerart.idea.engine.net.ArtHttp;
import me.fingerart.idea.engine.net.BaseRequest;
import me.fingerart.idea.engine.utils.StreamUtil;
import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * Created by fingerart on 16/10/18.
 */
public class GetRequestTest {
    public static void main(String[] s) {
        BaseRequest request = ArtHttp
                .get()
                .url("http://httpbin.org/get")
                .addParam("User-Agent", "ArtHttp")
                .addHeader("User-Agent", "ArtHttp")
                .addCookie("username", "fingerart")
                .addCookie("sfsdfsfsf", "asdf")
                .build();
        request.execute(new ArtHttpListener() {
            @Override
            public void onPre() {
                System.out.println("GetRequestTest.onPre");
            }

            @Override
            public void onSuccess(HttpResponse response) {
                try {
                    System.out.println(StreamUtil.outputString(response.getEntity().getContent()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(HttpResponse response, IOException e) {
                System.out.println("response = [" + response + "], e = [" + e + "]");
            }

            @Override
            public void onFinish() {
                System.out.println("GetRequestTest.onFinish");
            }
        });
    }
}
