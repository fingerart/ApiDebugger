package me.fingerart.idea.engine.utils;

import org.apache.http.util.TextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/8.
 */
public class CommonUtil {
    public static String nullToEmpty(String string) {
        return TextUtils.isEmpty(string) ? "" : string;
    }

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
}
