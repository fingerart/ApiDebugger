package io.chengguo.apidebugger.engine.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * Created by fingerart on 17/2/27.
 */
public class DebuggerEventBus extends EventBus {
    private static volatile DebuggerEventBus mEventBus;

    public static DebuggerEventBus getDefault() {
        if (mEventBus == null) {
            synchronized (DebuggerEventBus.class) {
                if (mEventBus == null) {
                    mEventBus = new DebuggerEventBus();
                }
            }
        }
        return mEventBus;
    }

}
