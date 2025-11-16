package com.aurora.travlogue.core.data.remote

import android.util.Log
import io.ktor.client.plugins.logging.Logger

/**
 * Android implementation of Ktor Logger
 * Outputs HTTP logs to Android logcat with proper formatting
 */
class AndroidKtorLogger : Logger {
    override fun log(message: String) {
        // Determine log level based on message content
        when {
            message.contains("RESPONSE") -> Log.d(TAG, "⬅️ $message")
            message.contains("REQUEST") -> Log.d(TAG, "➡️ $message")
            message.contains("BODY") -> Log.v(TAG, "📦 $message")
            message.contains("METHOD") || message.contains("URL") -> Log.i(TAG, "🌐 $message")
            else -> Log.d(TAG, message)
        }
    }

    companion object {
        private const val TAG = "KtorClient"
    }
}
