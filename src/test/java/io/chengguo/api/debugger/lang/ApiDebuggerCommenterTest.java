package io.chengguo.api.debugger.lang;

import com.intellij.util.text.CharArrayUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApiDebuggerCommenterTest {
    @Test
    public void regionMatchesTest() {
        String input = "/*abcdefg*/";
        System.out.println(CharArrayUtil.regionMatches(input, input.length()-2, "*/"));
    }
}