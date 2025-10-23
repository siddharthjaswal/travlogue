package com.aurora.travlogue.core.common

import platform.Foundation.NSLog

/**
 * iOS implementation of Logger using NSLog
 */
actual object Logger {
    actual fun debug(tag: String, message: String) {
        NSLog("DEBUG [$tag]: $message")
    }

    actual fun info(tag: String, message: String) {
        NSLog("INFO [$tag]: $message")
    }

    actual fun warn(tag: String, message: String) {
        NSLog("WARN [$tag]: $message")
    }

    actual fun error(tag: String, message: String, throwable: Throwable?) {
        val errorMessage = if (throwable != null) {
            "$message\n${throwable.message}"
        } else {
            message
        }
        NSLog("ERROR [$tag]: $errorMessage")
    }
}
