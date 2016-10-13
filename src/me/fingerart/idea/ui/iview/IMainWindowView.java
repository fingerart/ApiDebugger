package me.fingerart.idea.ui.iview;

/**
 * Created by FingerArt on 16/10/7.
 */
public abstract class IMainWindowView extends BaseView {
    public abstract void startUpload();

    /**
     * 开始上传
     *
     * @param percentage
     */
    public abstract void uploading(short percentage);

    public abstract void finishUpload();

    public abstract void showUploadedResult(String info);
}
