package io.chengguo.apidebugger.engine.net;

import io.chengguo.apidebugger.engine.interf.ArtHttpListener;
import io.chengguo.apidebugger.engine.interf.ProgressListener;
import io.chengguo.apidebugger.engine.log.Log;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/2.
 */
public class ProgressMultipartEntity extends MultipartEntity {
    private ProgressListener mListener;

    public ProgressMultipartEntity(HttpMultipartMode mode, String boundary, Charset charset, ArtHttpListener listener) {
        super(mode, boundary, charset);
        if (listener != null && listener instanceof ProgressListener) {
            mListener = (ProgressListener) listener;
        }
    }

    public ProgressMultipartEntity(HttpMultipartMode mode, ArtHttpListener listener) {
        this(mode, null, null, listener);
    }

    public ProgressMultipartEntity(ArtHttpListener listener) {
        this(HttpMultipartMode.STRICT, listener);
    }

    public ProgressMultipartEntity() {
        this(null);
    }

    /**
     * 添加文件参数
     *
     * @param key
     * @param file
     * @return
     */
    public ProgressMultipartEntity addPart(String key, File file) {
        FileBody contentBody = new FileBody(file);
        addPart(key, contentBody);
        return this;
    }

    /**
     * 添加文本参数
     *
     * @param key
     * @param text
     * @return
     */
    public ProgressMultipartEntity addPart(String key, String text) {
        StringBody stringBody = new StringBody(text, ContentType.TEXT_PLAIN);
        addPart(key, stringBody);
        return this;
    }

    public ProgressMultipartEntity addPart(HashMap<String, String> params) {
        if (params == null) return this;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            addPart(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public void writeTo(OutputStream outStream) {
        try {
            super.writeTo(new ProgressOutputStream(outStream, mListener));
        } catch (IOException e) {
            Log.e(e.getMessage());
        }
    }

    private class ProgressOutputStream extends OutputStream {
        private OutputStream out;
        private ProgressListener mListener;
        private long progress;

        public ProgressOutputStream(OutputStream outStream, ProgressListener listener) {
            out = outStream;
            mListener = listener;
            progress = 0;
        }

        @Override
        public void write(int b) throws IOException {
            out.write(b);
            progress++;
            if (mListener != null) {
                mListener.onProgress(getContentLength(), progress);
            }
        }

        @Override
        public void write(@NotNull byte[] b) throws IOException {
            out.write(b);
            progress += b.length;
            if (mListener != null) {
                mListener.onProgress(getContentLength(), progress);
            }
        }

        @Override
        public void write(@NotNull byte[] b, int off, int len) throws IOException {
            out.write(b, off, len);
            progress += len;
            if (mListener != null) {
                mListener.onProgress(getContentLength(), progress);
            }
        }
    }
}
