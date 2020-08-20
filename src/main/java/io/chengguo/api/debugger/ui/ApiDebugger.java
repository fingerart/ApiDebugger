package io.chengguo.api.debugger.ui;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.util.net.HttpConfigurable;
import com.intellij.util.net.IdeHttpClientHelpers;
import com.intellij.util.net.ssl.CertificateManager;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.jetbrains.annotations.NotNull;

import java.net.URI;

import static io.chengguo.api.debugger.lang.psi.ApiTypes.*;


public class ApiDebugger {
    private static final Logger LOG = Logger.getInstance(ApiDebugger.class);

    private final Project mProject;

    public ApiDebugger(Project project) {
        mProject = project;
    }

    public void debug(@NotNull ApiDebuggerRequest apiRequest, @NotNull IDebugListener debugListener) {
        FileDocumentManager.getInstance().saveAllDocuments();
        debugListener.onStart();
        ProgressManager.getInstance().run(new Task.Backgroundable(mProject, "标题", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                try {
                    CloseableHttpClient client = createHttpClient();
                    HttpRequestBase httpRequest = createHttpRequest(apiRequest);
                    CloseableHttpResponse response = client.execute(httpRequest);
                } catch (Exception e) {
                    LOG.error(e);
                    ApplicationManager.getApplication().invokeLater(() -> debugListener.onError(e));
                }
            }
        });
    }

    private HttpRequestBase createHttpRequest(ApiDebuggerRequest apiRequest) {
        String url = apiRequest.baseUrl;
        if (Api_GET.equals(apiRequest.method)) {
            return new HttpGet(url);
        } else if (Api_POST.equals(apiRequest.method)) {
            return new HttpPost(url);
        } else if (Api_DELETE.equals(apiRequest.method)) {
            return new HttpDelete(url);
        }else if (Api_HEAD.equals(apiRequest.method)) {
            return new HttpHead(url);
        }else if(Api_OPTIONS.equals(apiRequest.method)){
            return new HttpOptions(url);
        }
        return new HttpRequestBase() {
            {
                setURI(URI.create(url));
            }

            @Override
            public String getMethod() {
                return apiRequest.method;
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
        void onStart() {
        }

        void onResponse() {
        }

        void onError(Exception e) {
        }

        void onDone() {
        }
    }
}