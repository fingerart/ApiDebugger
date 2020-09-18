package io.chengguo.api.debugger.ui;

import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;

public class KeyValuePair extends Pair<String, String> {
    public final String key;
    public final String value;

    /**
     * @param key
     * @param value
     */
    public KeyValuePair(String key, String value) {
        super(key, value);
        this.key = key;
        this.value = value;
    }

    public static KeyValuePair create(String key, String value) {
        return new KeyValuePair(key, value);
    }

    public static KeyValuePair create(@NotNull Pair<String, String> pair) {
        if (pair instanceof KeyValuePair) {
            return (KeyValuePair) pair;
        }
        return new KeyValuePair(pair.first, pair.second);
    }

    public String getKey() {
        return first;
    }

    public String getValue() {
        return second;
    }
}
