package me.fingerart.idea.presenter;

import me.fingerart.idea.engine.component.StateProjectComponent;
import me.fingerart.idea.engine.interf.ProgressListener;
import me.fingerart.idea.engine.log.Log;
import me.fingerart.idea.engine.net.ArtHttp;
import me.fingerart.idea.engine.net.BaseRequest;
import me.fingerart.idea.engine.utils.CommonUtil;
import me.fingerart.idea.engine.utils.StreamUtil;
import me.fingerart.idea.engine.utils.VerifyUtil;
import me.fingerart.idea.engine.utils.ViewUtil;
import me.fingerart.idea.ui.iview.IMainWindowView;
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

//        File file = new File(filePath);
//        if (!file.exists()) {
//            mView.showE("选择的文件不存在");
//            return;
//        }

        LinkedHashMap<String, String> params = ViewUtil.getTableContent(paramsModel);
        LinkedHashMap<String, String> headers = ViewUtil.getTableContent(headersModel);
        LinkedHashMap<String, String> cookies = ViewUtil.getTableContent(cookiesModel);
        LinkedHashMap<String, String> files = ViewUtil.getTableContent(filesModel);

        LinkedHashMap<String, File> mapFile = CommonUtil.mapToFile(files);

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
            String info = StreamUtil.outputString(response.getEntity().getContent());
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

    public void delTableParams(JTable table) {
        for (int i : table.getSelectedRows()) {
            String key = (String) table.getModel().getValueAt(i, 0);
            StateProjectComponent.getInstance().delParam(key);
        }
    }
}
