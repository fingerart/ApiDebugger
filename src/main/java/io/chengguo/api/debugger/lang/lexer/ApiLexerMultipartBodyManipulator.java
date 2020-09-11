package io.chengguo.api.debugger.lang.lexer;

import com.intellij.openapi.util.text.StringUtil;

public class ApiLexerMultipartBodyManipulator {
    private static final String MULTIPART_FLAG = "multipart/form-data";
    private static final String MULTIPART_PREFIX = "boundary=";
    private static final String BOUNDARY_PREFIX = "--";
    private boolean mIsStarted;
    private boolean mIsInBoundary;
    private String mBoundary;

    public ApiLexerMultipartBodyManipulator() {
        reset();
    }

    public boolean isStarted() {
        return mIsStarted;
    }

    public boolean isStartedAndDefined() {
        return isStarted() && StringUtil.isNotEmpty(mBoundary);
    }

    public boolean isInBoundary() {
        return mIsInBoundary;
    }

    public boolean isMultipartType(CharSequence type) {
        return StringUtil.equals(type, MULTIPART_FLAG);
    }

    public String getEndMultipartType() {
        return mBoundary + BOUNDARY_PREFIX;
    }

    public boolean isEndMultipartType(CharSequence type) {
        return StringUtil.equals(type, getEndMultipartType());
    }

    public void trySetBoundary(CharSequence text) {
        if (isStarted() && StringUtil.isEmpty(mBoundary)) {
            if (StringUtil.startsWith(text, MULTIPART_PREFIX)) {
                CharSequence boundary = text.subSequence(MULTIPART_PREFIX.length(), text.length());
                mBoundary = BOUNDARY_PREFIX + StringUtil.unquoteString(boundary.toString());
            }
        }
    }

    public void start() {
        mIsStarted = true;
    }

    public void setIsInBoundary() {
        mIsInBoundary = true;
    }

    public void reset() {
        mIsStarted = false;
        mIsInBoundary = false;
        mBoundary = null;
    }

    public String getBoundary() {
        return mBoundary;
    }

    @Override
    public String toString() {
        return "ApiLexerMultipartBodyManipulator{" +
                "mIsStarted=" + mIsStarted +
                ", mIsInBoundary=" + mIsInBoundary +
                ", mBoundary='" + mBoundary + '\'' +
                '}';
    }
}
