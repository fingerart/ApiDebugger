package io.chengguo.apidebugger.presenter;

import io.chengguo.apidebugger.engine.component.StateProjectComponent;
import io.chengguo.apidebugger.engine.net.BaseRequest;
import io.chengguo.apidebugger.engine.utils.IOUtil;
import io.chengguo.apidebugger.engine.utils.ViewUtil;
import io.chengguo.apidebugger.engine.bean.AttachAttribute;
import io.chengguo.apidebugger.engine.interf.ProgressListener;
import io.chengguo.apidebugger.engine.log.Log;
import io.chengguo.apidebugger.engine.net.ArtHttp;
import io.chengguo.apidebugger.engine.utils.CommonUtil;
import io.chengguo.apidebugger.engine.utils.VerifyUtil;
import io.chengguo.apidebugger.ui.iview.IMainWindowView;
import org.apache.http.HttpResponse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by FingerArt on 16/10/1.
 */
public class MainPresenter implements ProgressListener {

    private IMainWindowView mView;

    public MainPresenter(IMainWindowView view) {
        mView = view;
    }

    /**
     * 处理请求
     *
     * @param method
     * @param url
     * @param paramsModel
     * @param headersModel
     * @param cookiesModel
     * @param filesModel
     */
    public void executeRequest(String method, String url, DefaultTableModel paramsModel, DefaultTableModel headersModel, DefaultTableModel cookiesModel, DefaultTableModel filesModel) {

        if (!VerifyUtil.verifyUrl(url)) {
            mView.showE("请求的Url无效");
            return;
        }

        LinkedHashMap<String, String> params = ViewUtil.getTableContent(paramsModel);
        LinkedHashMap<String, String> headers = ViewUtil.getTableContent(headersModel);
        LinkedHashMap<String, String> cookies = ViewUtil.getTableContent(cookiesModel);
        LinkedHashMap<String, String> files = ViewUtil.getTableContent(filesModel);

        LinkedHashMap<String, File> mapFile = CommonUtil.mapToFile(files);

        AttachAttribute a = new AttachAttribute();
        a.params = params;
        a.headers = headers;
        a.cookies = cookies;
        a.files = files;
        StateProjectComponent.getInstance().addAttach(url, a);
        upload(method, url, params, headers, cookies, mapFile);
    }

    private void upload(String method, String url, LinkedHashMap<String, String> params, LinkedHashMap<String, String> headers, LinkedHashMap<String, String> cookies, LinkedHashMap<String, File> files) {
        mView.startExecute();
        BaseRequest request;
        // @formatter:off
        switch (method.toLowerCase()) {
            case "post":
                request = ArtHttp
                            .post()
                            .url(url)
                            .addHeader(headers)
                            .addParam(params)
                            .addFile(files)
                            .addCookie(cookies)
                            .build();
                break;
            default://get
                request = ArtHttp
                            .get()
                            .url(url)
                            .addHeader(headers)
                            .addParam(params)
                            .addCookie(cookies)
                            .build();
        }
        // @formatter:on
        request.execute(this);
    }

    public void cancelUpload() {
        ArtHttp.cancelAll();
    }

    @Override
    public void onPre() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mView.startExecute();
            }
        });
    }

    @Override
    public void onProgress(long total, long progress) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mView.uploading((short) (((float) progress) / ((float) total) * 100));
            }
        });
    }

    @Override
    public void onSuccess(HttpResponse response) {
        try {
            String info = IOUtil.outputString(response.getEntity().getContent());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    mView.showUploadedResult(info);
                }
            });
            response.getEntity().getContent().close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void onError(IOException e) {
        Log.e(e.getMessage());
    }

    @Override
    public void onFinish() {
        Log.i("MainPresenter.onFinish");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mView.finishExecute();
            }
        });
    }
}
