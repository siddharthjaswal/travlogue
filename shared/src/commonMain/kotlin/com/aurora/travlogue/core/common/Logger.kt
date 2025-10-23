package com.aurora.travlogue.core.common

/**
 * Platform-agnostic logger interface
 * Actual implementations are provided per platform
 */
expect object Logger {
    fun debug(tag: String, message: String)
    fun info(tag: String, message: String)
    fun warn(tag: String, message: String)
    fun error(tag: String, message: String, throwable: Throwable? = null)
}
