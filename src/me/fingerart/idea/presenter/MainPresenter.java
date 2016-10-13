package me.fingerart.idea.presenter;

import me.fingerart.idea.engine.component.StateProjectComponent;
import me.fingerart.idea.engine.interf.UploadProgressListener;
import me.fingerart.idea.engine.log.Log;
import me.fingerart.idea.engine.net.ArtHttp;
import me.fingerart.idea.engine.utils.StreamUtil;
import me.fingerart.idea.engine.utils.VerifyUtil;
import me.fingerart.idea.engine.utils.ViewUtil;
import me.fingerart.idea.ui.iview.IMainWindowView;
import org.apache.http.HttpResponse;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by FingerArt on 16/10/1.
 */
public class MainPresenter implements UploadProgressListener {

    private IMainWindowView mView;

    public MainPresenter(IMainWindowView view) {
        mView = view;
    }

    /**
     * 处理文件上传
     *
     * @param url
     * @param filePath
     * @param tableParams
     */
    public void handleUploadFile(String url, String filePath, TableModel tableParams) {
        if (!VerifyUtil.verifyUrl(url)) {
            mView.showE("请求的Url无效");
            return;
        }

        if (TextUtils.isEmpty(filePath)) {
            mView.showE("请选择一个上传的文件");
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            mView.showE("选择的文件不存在");
            return;
        }

        LinkedHashMap<String, String> params = ViewUtil.getTableContent(tableParams);

        mView.startUpload();
        upload(url, file, params);
    }

    private void upload(String url, File file, LinkedHashMap<String, String> params) {
        ArtHttp
                .post()
                .url(url)
                .addParam("file", file)
                .addParam(params)
                .build()
                .execute(this);
    }

    public void cancelUpload() {
        ArtHttp.cancelAll();
    }

    @Override
    public void onPre() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mView.startUpload();
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
    public void onError(HttpResponse response, IOException e) {
        Log.e(e.getMessage());
    }

    @Override
    public void onFinish() {
        Log.i("MainPresenter.onFinish");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mView.finishUpload();
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
