package io.chengguo.api.debugger;

import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.diagnostic.ErrorReportSubmitter;
import com.intellij.openapi.diagnostic.IdeaLoggingEvent;
import com.intellij.openapi.diagnostic.SubmittedReportInfo;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 上报插件错误
 */
public class ApiDebuggerErrorReportSubmitter extends ErrorReportSubmitter {

    private static final String TAG_PLATFORM_VERSION = "platform";
    private static final String TAG_OS = "os";
    private static final String TAG_OS_VERSION = "os_version";
    private static final String TAG_OS_ARCH = "os_arch";
    private static final String TAG_JAVA_VERSION = "java_version";
    private static final String TAG_JAVA_RUNTIME_VERSION = "java_runtime_version";

    private static final String EXTRA_MORE_EVENTS = "More Events";
    private static final String EXTRA_ADDITIONAL_INFO = "Additional Info";

    @NotNull
    @Override
    public String getReportActionText() {
        return ApiDebuggerBundle.message("api.debugger.report.action_text");
    }

    @Override
    public boolean submit(@NotNull IdeaLoggingEvent[] events, @Nullable String additionalInfo, @NotNull Component parentComponent, @NotNull Consumer<SubmittedReportInfo> consumer) {
        log(events, additionalInfo);
        consumer.consume(new SubmittedReportInfo(null, null, SubmittedReportInfo.SubmissionStatus.NEW_ISSUE));
        Messages.showInfoMessage(parentComponent, ApiDebuggerBundle.message("api.debugger.report.default_response"), ApiDebuggerBundle.message("api.debugger.report.default_response_title"));
        return true;
    }

    private void log(@NotNull IdeaLoggingEvent[] events, @Nullable String additionalInfo) {
        IdeaLoggingEvent ideaEvent = events[0];
        if (ideaEvent.getThrowable() == null) {
            return;
        }
        LinkedHashMap<String, Object> customData = new LinkedHashMap<>();
        customData.put(TAG_PLATFORM_VERSION, ApplicationInfo.getInstance().getBuild().asString());
        customData.put(TAG_OS, SystemInfo.OS_NAME);
        customData.put(TAG_OS_VERSION, SystemInfo.OS_VERSION);
        customData.put(TAG_OS_ARCH, SystemInfo.OS_ARCH);
        customData.put(TAG_JAVA_VERSION, SystemInfo.JAVA_VERSION);
        customData.put(TAG_JAVA_RUNTIME_VERSION, SystemInfo.JAVA_RUNTIME_VERSION);
        if (additionalInfo != null) {
            customData.put(EXTRA_ADDITIONAL_INFO, additionalInfo);
        }
        if (events.length > 1) {
            customData.put(EXTRA_MORE_EVENTS,
                    Stream.of(events).map(Object::toString).collect(Collectors.joining("\n")));
        }

        // TODO log
        System.out.println(ideaEvent.getThrowable().toString());
        System.out.println(customData.toString());
    }
}
