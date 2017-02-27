package io.chengguo.apidebugger.engine.log;

/**
 * Created by FingerArt on 16/10/1.
 */
public class Log implements ILog {

    public static void i(String message) {
        System.out.println(message);
    }

    public static void d(String message) {
        System.out.println(message);
    }

    public static void e(String message) {
        System.out.println(message);
    }
}
