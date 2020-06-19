package io.chengguo.api.debugger.extension

import java.lang.Exception

inline fun <T : Any> T?.requireNonNull(predicate: () -> Exception = { NullPointerException() }): T {
    if (this == null) {
        throw predicate()
    }
    return this
}

inline fun <T : Any> T?.requireNonNull(
    message: String,
    predicate: (message: String) -> Exception = { NullPointerException(message) }
): T {
    if (this == null) {
        throw predicate(message)
    }
    return this
}