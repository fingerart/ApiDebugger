package io.chengguo.api.debugger.lang.psi;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import io.chengguo.api.debugger.lang.ApiFileType;
import org.jetbrains.annotations.NotNull;

public class ApiFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(ApiFileType.INSTANCE);
    }
}
