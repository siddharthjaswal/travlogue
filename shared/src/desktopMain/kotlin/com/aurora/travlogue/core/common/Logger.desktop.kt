package com.aurora.travlogue.core.common

/**
 * JVM/Desktop implementation of Logger using println
 */
actual object Logger {
    actual fun debug(tag: String, message: String) {
        println("DEBUG [$tag]: $message")
    }

    actual fun info(tag: String, message: String) {
        println("INFO [$tag]: $message")
    }

    actual fun warn(tag: String, message: String) {
        println("WARN [$tag]: $message")
    }

    actual fun error(tag: String, message: String, throwable: Throwable?) {
        val errorMessage = if (throwable != null) {
            "$message\n${throwable.stackTraceToString()}"
        } else {
            message
        }
        System.err.println("ERROR [$tag]: $errorMessage")
    }
}
