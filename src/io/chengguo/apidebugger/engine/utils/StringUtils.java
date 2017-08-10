package io.chengguo.apidebugger.engine.utils;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.w3c.tidy.Tidy;

import javax.xml.transform.TransformerException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * Created by fingerart on 17/7/17.
 */
public class StringUtils {
    private static final int JSON_INDENT = 2;

    public static String formatJson(@NotNull String json) throws JSONException {
        json = json.trim();
        if (json.startsWith("{")) {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.toString(JSON_INDENT);
        }
        if (json.startsWith("[")) {
            JSONArray jsonArray = new JSONArray(json);
            return jsonArray.toString(JSON_INDENT);
        }
        return json;
    }

    public static String formatXml(@NotNull String xml) throws TransformerException {
        StringReader stringReader = new StringReader(xml);
        Tidy tidy = new Tidy();
        tidy.setXmlOut(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setTidyMark(false);
        tidy.setForceOutput(true);
        tidy.setSmartIndent(true);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);
        StringWriter stringWriter = new StringWriter();
        tidy.parse(stringReader, stringWriter);
        return stringWriter.toString();
    }

    public static String formatHtml(String html) {
        StringReader stringReader = new StringReader(html);
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setTidyMark(false);
        tidy.setSmartIndent(true);
        tidy.setForceOutput(true);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);
        StringWriter stringWriter = new StringWriter();
        tidy.parse(stringReader, stringWriter);
        return stringWriter.toString();
    }

    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
