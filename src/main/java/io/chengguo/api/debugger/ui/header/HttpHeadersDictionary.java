package io.chengguo.api.debugger.ui.header;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class HttpHeadersDictionary {
    private static final List<String> CONTENT_TYPES = ContainerUtil.newArrayList("application/javascript", "application/json", "application/x-www-form-urlencoded", "application/xml", "application/zip", "application/pdf", "application/sql", "application/msword", "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation", "audio/mpeg", "audio/vorbis", "multipart/form-data", "text/css", "text/html", "text/csv", "text/plain", "image/png", "image/jpeg", "image/gif");
    private static final List<String> ENCODINGS = ContainerUtil.newArrayList("compress", "deflate", "exi", "gzip", "identity", "pack200-gzip", "br", "bzip2", "lzma", "peerdist", "sdch", "xpress", "xz");
    private static Map<String, HttpHeaderDocumentation> ourHeaders = null;
    private static Map<String, List<String>> ourHeaderValues;
    private static final Map<String, List<String>> ourHeaderOptionNames = new HashMap<>();

    static {
        ourHeaderOptionNames.put("Content-Type", ContainerUtil.newArrayList("charset", "boundary"));
    }

    @NotNull
    public static synchronized Map<String, HttpHeaderDocumentation> getHeaders() {
        if (HttpHeadersDictionary.ourHeaders == null) {
            HttpHeadersDictionary.ourHeaders = readHeaders();
        }
        return HttpHeadersDictionary.ourHeaders;
    }

    @Nullable
    public static HttpHeaderDocumentation getDocumentation(@NotNull final String fieldName) {
        Map<String, HttpHeaderDocumentation> headers = getHeaders();
        return headers.get(fieldName);
    }

    @NotNull
    private static Map<String, HttpHeaderDocumentation> readHeaders() {
        Map<String, HttpHeaderDocumentation> result = new HashMap<>();
        InputStream stream = HttpHeadersDictionary.class.getResourceAsStream("/raw/http-headers.json");
        try {
            String file = (stream != null) ? FileUtil.loadTextAndClose(stream) : "";
            if (StringUtil.isNotEmpty(file)) {
                JsonElement root = new JsonParser().parse(file);
                if (root.isJsonArray()) {
                    JsonArray array = root.getAsJsonArray();
                    for (JsonElement element : array) {
                        if (element.isJsonObject()) {
                            HttpHeaderDocumentation header = HttpHeaderDocumentation.read(element.getAsJsonObject());
                            if (header == null) {
                                continue;
                            }
                            result.put(header.getName(), header);
                        }
                    }
                }
            }
        } catch (IOException e) {
            Logger.getInstance(HttpHeadersDictionary.class).error(e);
        }
        return result;
    }

    @NotNull
    public static Collection<String> getHeaderValues(@NotNull Project project, @NotNull String headerName) {
        if (HttpHeadersDictionary.ourHeaderValues == null) {
            HttpHeadersDictionary.ourHeaderValues = readHeaderValues();
        }
        if (StringUtil.equals(headerName, "Accept")) {
            ApiDebuggerDataProvider[] extensions = ApiDebuggerDataProvider.EP_NAME.getExtensions();
            if (extensions.length > 0) {
                List<String> mimeTypes = new ArrayList<>(HttpHeadersDictionary.ourHeaderValues.get(headerName));
                for (ApiDebuggerDataProvider extension : extensions) {
                    Collections.addAll(mimeTypes, extension.getAllMimeTypes(project));
                }
                return mimeTypes;
            }
        }
        return HttpHeadersDictionary.ourHeaderValues.containsKey(headerName) ? HttpHeadersDictionary.ourHeaderValues.get(headerName) : ContainerUtil.emptyList();
    }

    @NotNull
    public static Collection<String> getHeaderOptionNames(@NotNull String headerName) {
        return HttpHeadersDictionary.ourHeaderOptionNames.containsKey(headerName) ? HttpHeadersDictionary.ourHeaderOptionNames.get(headerName) : ContainerUtil.emptyList();
    }

    @NotNull
    private static Map<String, List<String>> readHeaderValues() {
        Map<String, List<String>> values = new HashMap<>();
        values.put("Accept", HttpHeadersDictionary.CONTENT_TYPES);
        values.put("Content-Type", HttpHeadersDictionary.CONTENT_TYPES);
        values.put("Accept-Encoding", HttpHeadersDictionary.ENCODINGS);
        return values;
    }
}