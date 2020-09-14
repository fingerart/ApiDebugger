package io.chengguo.api.debugger.lang.environment;

import com.intellij.json.JsonFileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ArrayUtil;
import com.intellij.util.indexing.DefaultFileTypeSpecificInputFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApiEnvironmentInputFilter extends DefaultFileTypeSpecificInputFilter {

    public static String[] ENV_FILE_NAMES = new String[]{"api.env.json"};
    public static String[] ENV_PRIVATE_FILE_NAMES = new String[]{"api.private.env.json"};

    public ApiEnvironmentInputFilter() {
        super(JsonFileType.INSTANCE);
    }

    @Override
    public boolean acceptInput(@NotNull VirtualFile file) {
        return super.acceptInput(file) && isApiEnvFile(file);
    }

    public static boolean isApiEnvFile(@Nullable VirtualFile file) {
        if (file != null) {
            String fileName = file.getName();
            return ArrayUtil.contains(fileName, ENV_FILE_NAMES) || isPrivateEnvFile(fileName);
        }
        return false;
    }

    private static boolean isPrivateEnvFile(String fileName) {
        return ArrayUtil.contains(fileName, ENV_PRIVATE_FILE_NAMES);
    }
}
