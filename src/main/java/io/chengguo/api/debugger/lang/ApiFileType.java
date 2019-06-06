package io.chengguo.api.debugger.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * *.api文件类型
 */
public class ApiFileType extends LanguageFileType {

    public static final ApiFileType INSTANCE;

    static {
        INSTANCE = new ApiFileType(ApiLanguage.INSTANCE);
    }

    /**
     * Creates a language file type for the specified language.
     *
     * @param language The language used in the files of the type.
     */
    protected ApiFileType(@NotNull Language language) {
        super(language);
    }

    @NotNull
    @Override
    public String getName() {
        return ApiDebuggerBundle.message("api.debugger.action.name");
    }

    @NotNull
    @Override
    public String getDescription() {
        return ApiDebuggerBundle.message("api.debugger.action.description");
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "api";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return ApiDebuggerIcons.API_FILE_TYPE;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (this == obj || obj.getClass() == ApiFileType.class || obj instanceof ApiFileType);
    }
}
