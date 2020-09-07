package io.chengguo.api.debugger.lang.run;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.progress.*;
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.util.net.HttpConfigurable;
import com.intellij.util.net.IdeHttpClientHelpers;
import com.intellij.util.net.ssl.CertificateManager;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;

import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;


public class ApiDebuggerExecutor {
    private static final Logger LOG = Logger.getInstance(ApiDebuggerExecutor.class);

    private final Project mProject;

    public ApiDebuggerExecutor(Project project) {
        mProject = project;
    }

    public void execute(ApiDebuggerRequest request, IDebugListener debugListener, boolean isWithProgress) {
        FileDocumentManager.getInstance().saveAllDocuments();
        Task.Backgroundable task = new Task.Backgroundable(mProject, request.url, true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                try {
                    indicator.setIndeterminate(true);
                    indicator.setText(ApiDebuggerBundle.message("api.debugger.run.indicator.connecting"));
                    CloseableHttpClient client = createHttpClient();
                    HttpRequestBase httpRequest = createHttpRequest(request);
                    CloseableHttpResponse response = client.execute(httpRequest);
                    Header contentLength = response.getFirstHeader("Content-Length");
                    Long length = contentLength == null ? null : Long.parseLong(contentLength.getValue());
                    if (length != null) {
                        indicator.setIndeterminate(false);
                        indicator.setFraction(0.0);
                        indicator.setText(ApiDebuggerBundle.message("api.debugger.run.indicator.accessing"));
                    }
                    HttpEntity entity = response.getEntity();
                    InputStream body = entity.getContent();
                    ContentType contentType = ContentType.getOrDefault(entity);
                    Charset charset = contentType.getCharset();
                    if (charset == null) {
                        charset = HTTP.DEF_CONTENT_CHARSET;
                    }
                    CountingInputStream countingInputStream = new CountingInputStream(body);
                    InputStreamReader in = new InputStreamReader(countingInputStream, charset);
                    StringBuilder sb = new StringBuilder();
                    char[] b = new char[4096];
                    for (int len; (len = in.read(b)) != -1 && !indicator.isCanceled(); ) {
                        sb.append(new String(b, 0, len));
                        if (length != null) {
                            indicator.setFraction(((double) (countingInputStream.getCount())) / length);
                            indicator.setText(countingInputStream.getCount() / 1000L + " of " + length / 1000L + "Kb");
                        }
                    }
                    ApplicationManager.getApplication().invokeLater(() -> debugListener.onResponse(sb));
                } catch (Exception e) {
                    ApplicationManager.getApplication().invokeLater(() -> debugListener.onError(e));
                } finally {
                    ApplicationManager.getApplication().invokeLater(debugListener::onDone);
                }
            }
        };
        StandardProgressIndicator indicator = ApplicationManager.getApplication().isHeadlessEnvironment() || !isWithProgress ? new EmptyProgressIndicator() : new BackgroundableProcessIndicator(task);
        if (ApplicationManager.getApplication().isDispatchThread()) {
            ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, indicator);
        } else {
            ApplicationManager.getApplication().invokeLater(() -> ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, indicator));
        }
    }

    private HttpRequestBase createHttpRequest(ApiDebuggerRequest apiRequest) {
        final String url = apiRequest.url;
        final String method = apiRequest.method;
        if (Api_GET.equals(method)) {
            return new HttpGet(url);
        } else if (Api_POST.equals(method)) {
            return new HttpPost(url);
        } else if (Api_DELETE.equals(method)) {
            return new HttpDelete(url);
        } else if (Api_HEAD.equals(method)) {
            return new HttpHead(url);
        } else if (Api_OPTIONS.equals(method)) {
            return new HttpOptions(url);
        } else if (Api_PUT.equals(method)) {
            return new HttpPut(url);
        } else if (Api_TRACE.equals(method)) {
            return new HttpTrace(url);
        }
        LOG.info(String.format("other method: %s", method));
        return new HttpRequestBase() {
            {
                setURI(URI.create(url));
            }

            @Override
            public String getMethod() {
                return method;
            }
        };
    }

    private CloseableHttpClient createHttpClient() {
        HttpClientBuilder builder = HttpClients.custom();
        try {
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(
                    CertificateManager.getInstance().getSslContext(),
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
            );
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("https", factory)
                    .register("http", new PlainConnectionSocketFactory())
                    .build();
            builder.setConnectionManager(new BasicHttpClientConnectionManager(registry));
        } catch (Exception e) {
            LOG.error(e);
        }
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        if (haveProxy()) {
            IdeHttpClientHelpers.ApacheHttpClient4.setProxyIfEnabled(requestConfigBuilder);
            IdeHttpClientHelpers.ApacheHttpClient4.setProxyCredentialsIfEnabled(credentialsProvider);
        }
        builder.setDefaultRequestConfig(requestConfigBuilder.build());
        builder.setDefaultCredentialsProvider(credentialsProvider);
        return builder.build();
    }

    private boolean haveProxy() {
        HttpConfigurable instance = HttpConfigurable.getInstance();
        return (instance.USE_HTTP_PROXY || instance.USE_PROXY_PAC);
    }

    /**
     * 调试监听器
     */
    public static abstract class IDebugListener {
        public void onStart() {
        }

        public void onResponse(StringBuilder builder) {
        }

        public void onError(Exception e) {
        }

        public void onDone() {
        }
    }
}