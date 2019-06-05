package io.chengguo.apidebugger.engine.utils;

import java.io.*;

/**
 * Created by FingerArt on 16/10/7.
 */
public class IOUtil {

    /**
     * InputStream convert to String
     *
     * @param is
     * @return
     */
    public static String outputString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str;
        try {
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
