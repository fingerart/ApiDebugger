package me.fingerart.idea.engine.interf;

import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * http 回调接口
 * Created by fingerart on 16/10/18.
 */
public interface ArtHttpListener {
    void onPre();

    void onSuccess(HttpResponse response);

    void onError(HttpResponse response, IOException e);

    void onFinish();
}
