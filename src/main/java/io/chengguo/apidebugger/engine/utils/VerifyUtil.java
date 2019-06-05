package io.chengguo.apidebugger.engine.utils;

import org.apache.http.util.TextUtils;

import java.util.regex.Pattern;

/**
 * 校验工具类
 * Created by FingerArt on 16/10/1.
 */
public class VerifyUtil {

    /**
     * URL 合法性校验
     *
     * @param url
     * @return
     */
    public static boolean verifyUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^http:\\/\\/[a-zA-Z0-9.]+\\..*");
        return pattern.matcher(url).matches();
    }

}
