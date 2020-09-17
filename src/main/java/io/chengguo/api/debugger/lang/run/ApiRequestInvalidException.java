package io.chengguo.api.debugger.lang.run;

public class ApiRequestInvalidException extends Exception {
    public ApiRequestInvalidException() {
    }

    public ApiRequestInvalidException(String message) {
        super(message);
    }

    public ApiRequestInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRequestInvalidException(Throwable cause) {
        super(cause);
    }

    public ApiRequestInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
