package io.chengguo.apidebugger.engine.utils;

import com.intellij.execution.process.ProcessIOExecutorService;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.Future;

/**
 * Created by FingerArt on 16/10/8.
 */
public class CommonUtil {

    /**
     * null 2 empty
     *
     * @param string
     * @return
     */
    public static String nullToEmpty(String string) {
        return TextUtils.isEmpty(string) ? "" : string;
    }

    /**
     * Map 2 Array
     *
     * @param map
     * @return
     */
    public static String[][] mapToArray(HashMap<String, String> map) {
        String[][] arr = new String[map.size()][2];
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            Map.Entry<String, String> entry = iterator.next();
            arr[i][0] = entry.getKey();
            arr[i][1] = entry.getValue();
        }
        return arr;
    }

    /**
     * map 2 Cookie
     *
     * @param map
     * @param kvs 键值分隔符
     * @param s   键值对分隔符
     * @return
     */
    public static String mapToCookieVal(@NotNull HashMap<String, String> map, String kvs, String s) {
        StringBuffer sBuff = new StringBuffer();
        if (map != null && !map.isEmpty()) {
            Set<Map.Entry<String, String>> set = map.entrySet();
            Iterator<Map.Entry<String, String>> iterator = set.iterator();
            while (true) {
                Map.Entry<String, String> entry = iterator.next();
                sBuff.append(entry.getKey());
                sBuff.append(kvs);
                sBuff.append(entry.getValue());
                if (iterator.hasNext()) {
                    sBuff.append(s);
                } else {
                    break;
                }
            }
        }
        return sBuff.toString();
    }

    /**
     * 拼装Url与参数
     *
     * @param url
     * @param params
     * @return
     */
    public static String mergeUrlParams(@NotNull String url, HashMap<String, String> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        String[] s = splitUrl(url);
        List<NameValuePair> parse;
        if (s[1] != null) {
            parse = URLEncodedUtils.parse(s[1], Charset.defaultCharset());
        } else {
            parse = new ArrayList<>();
        }
//        System.out.println(parse);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            parse.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        String p = URLEncodedUtils.format(parse, Charset.defaultCharset());
//        System.out.println(p);
        return s[0] + "?" + p;
    }

    /**
     * 拆分url的域名与参数部分
     *
     * @param url
     * @return
     */
    public static String[] splitUrl(String url) {
        String[] u = new String[]{"", ""};
        if (TextUtils.isEmpty(url)) {
            return u;
        }
        int q = url.indexOf("?");
        if (q >= 0) {
            u[0] = url.substring(0, q);
            u[1] = url.substring(q + 1);
        } else {
            u[0] = url;
        }
        return u;
    }

    /**
     * 将map中的value路径转成File
     *
     * @param files
     * @return
     */
    public static LinkedHashMap<String, File> mapToFile(LinkedHashMap<String, String> files) {
        LinkedHashMap<String, File> fileMap = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : files.entrySet()) {
            String path = entry.getValue();
            if (TextUtils.isEmpty(path)) continue;
            File file = new File(path);
            if (!file.exists()) continue;
            fileMap.put(entry.getKey(), file);
        }
        return fileMap;
    }

    /**
     * 格式化Json
     *
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }
        return sb.toString();
    }

    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    public static String fixProtocol(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            return "http://" + url;
        }
        return url;
    }

    public static void runInUI(Runnable runnable) {
        SwingUtilities.invokeLater(runnable);
    }

    public static Future<?> async(Runnable runnable) {
        return ProcessIOExecutorService.INSTANCE.submit(runnable);
    }
}
