package io.chengguo.apidebugger.ui.iview;

/**
 * Created by FingerArt on 16/10/7.
 */
public abstract class IMainWindowView extends BaseView {
    public abstract void startExecute();

    /**
     * 开始上传
     *
     * @param percentage
     */
    public abstract void uploading(short percentage);

    public abstract void finishExecute();

    public abstract void showUploadedResult(String info);
}
