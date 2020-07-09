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

    private static final String BUNDLE = "io.chengguo.api.debugger.ApiDebuggerBundle";

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
        return CommonBundle.message(getBundle(), key, params);
    }

    public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key) {
        return message(key, new Object[0]);
    }
}
