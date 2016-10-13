package me.fingerart.idea.engine.interf;

import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * Created by FingerArt on 16/10/2.
 */
public interface UploadProgressListener {
    void onPre();

    void onProgress(long total, long progress);

    void onSuccess(HttpResponse response);

    void onError(HttpResponse response, IOException e);

    void onFinish();
}
