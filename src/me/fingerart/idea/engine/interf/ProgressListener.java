package me.fingerart.idea.engine.interf;

/**
 * 带进度的回调接口
 * Created by FingerArt on 16/10/2.
 */
public interface ProgressListener extends ArtHttpListener {
    void onProgress(long total, long progress);
}
