package io.chengguo.api.debugger.api.spi;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import io.chengguo.api.debugger.api.ApiFileType;
import org.jetbrains.annotations.NotNull;

public class ApiFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(ApiFileType.INSTANCE);
    }
}
