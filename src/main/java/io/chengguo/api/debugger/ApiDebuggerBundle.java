package io.chengguo.api.debugger;

import com.intellij.CommonBundle;
import com.intellij.reference.SoftReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.Reference;
import java.util.ResourceBundle;

/**
 * Localization bundle
 */
public class ApiDebuggerBundle {

    static final String BUNDLE = "io.chengguo.api.debugger.ApiDebuggerBundle";

    private static Reference<ResourceBundle> mBundleReference;

    private static ResourceBundle getBundle() {
        ResourceBundle bundle = SoftReference.dereference(mBundleReference);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(BUNDLE);
            mBundleReference = new java.lang.ref.SoftReference<>(bundle);
        }
        return bundle;
    }

    public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        if (key == null) {
            $$$reportNull$$$0(0);
        }
        return CommonBundle.message(getBundle(), key, params);
    }

    public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key) {
        return message(key, new Object[0]);
    }

    private static void $$$reportNull$$$0(final int n) {
        final String s = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        final Object[] array = new Object[3];
        switch (n) {
            default: {
                array[0] = "key";
                break;
            }
            case 1: {
                array[0] = "params";
                break;
            }
        }
        array[1] = "io/chengguo/api/debugger/ApiDebuggerBundle";
        array[2] = "message";
        throw new IllegalArgumentException(String.format(s, array));
    }
}
