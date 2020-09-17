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

import static io.chengguo.api.debugger.constants.HeaderFields.CONTENT_DISPOSITION;
import static io.chengguo.api.debugger.constants.HeaderFields.CONTENT_TYPE;

public class HttpHeadersDictionary {
    private static final List<String> CONTENT_TYPES = ContainerUtil.newArrayList("application/javascript", "application/json", "application/x-www-form-urlencoded", "application/xml", "application/zip", "application/pdf", "application/octet-stream", "application/sql", "application/msword", "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation", "audio/mpeg", "audio/vorbis", "multipart/form-data", "text/css", "text/html", "text/csv", "text/plain", "image/png", "image/jpeg", "image/gif");
    private static final List<String> ENCODINGS = ContainerUtil.newArrayList("compress", "deflate", "exi", "gzip", "identity", "pack200-gzip", "br", "bzip2", "lzma", "peerdist", "sdch", "xpress", "xz");
    private static final List<String> CONTENT_DISPOSITIONS = ContainerUtil.newArrayList("form-data");
    private static Map<String, HttpHeaderDocumentation> ourHeaders = null;
    private static Map<String, List<String>> ourHeaderValues;
    private static final Map<String, List<String>> ourHeaderOptionNames = new HashMap<>();

    static {
        ourHeaderOptionNames.put(CONTENT_TYPE, ContainerUtil.newArrayList("charset", "boundary"));
        ourHeaderOptionNames.put(CONTENT_DISPOSITION, ContainerUtil.newArrayList("name", "filename"));
    }

    @NotNull
    public static synchronized Map<String, HttpHeaderDocumentation> getHeaders() {
        if (ourHeaders == null) {
            ourHeaders = readHeaders();
        }
        return ourHeaders;
    }

    @Nullable
    public static HttpHeaderDocumentation getDocumentation(@NotNull String fieldName) {
        Map<String, HttpHeaderDocumentation> headers = getHeaders();
        return headers.get(fieldName);
    }

    @NotNull
    private static Map<String, HttpHeaderDocumentation> readHeaders() {
        Map<String, HttpHeaderDocumentation> result = new HashMap<>();
        InputStream stream = HttpHeadersDictionary.class.getResourceAsStream("/raw/http-headers.json");
        try {
            String text = (stream != null) ? FileUtil.loadTextAndClose(stream) : "";
            if (StringUtil.isNotEmpty(text)) {
                JsonElement root = new JsonParser().parse(text);
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
        if (ourHeaderValues == null) {
            ourHeaderValues = readHeaderValues();
        }
        if (StringUtil.equals(headerName, "Accept")) {
            ApiDebuggerDataProvider[] extensions = ApiDebuggerDataProvider.EP_NAME.getExtensions();
            if (extensions.length > 0) {
                List<String> mimeTypes = new ArrayList<>(ourHeaderValues.get(headerName));
                for (ApiDebuggerDataProvider extension : extensions) {
                    Collections.addAll(mimeTypes, extension.getAllMimeTypes(project));
                }
                return mimeTypes;
            }
        }
        return ourHeaderValues.containsKey(headerName) ? ourHeaderValues.get(headerName) : ContainerUtil.emptyList();
    }

    @NotNull
    public static Collection<String> getHeaderOptionNames(@NotNull String headerName) {
        return ourHeaderOptionNames.containsKey(headerName) ? ourHeaderOptionNames.get(headerName) : ContainerUtil.emptyList();
    }

    @NotNull
    private static Map<String, List<String>> readHeaderValues() {
        Map<String, List<String>> values = new HashMap<>();
        values.put("Accept", CONTENT_TYPES);
        values.put("Content-Type", CONTENT_TYPES);
        values.put("Accept-Encoding", ENCODINGS);
        values.put("Content-Disposition", CONTENT_DISPOSITIONS);
        return values;
    }
}