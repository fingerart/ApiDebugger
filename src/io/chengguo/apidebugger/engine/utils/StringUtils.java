package io.chengguo.apidebugger.engine.utils;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jetbrains.annotations.NotNull;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
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
        Source xmlInput = new StreamSource(new StringReader(xml));
        StreamResult xmlOutput = new StreamResult(new StringWriter());
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", 4);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(xmlInput, xmlOutput);
        return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
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
