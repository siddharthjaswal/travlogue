package com.aurora.travlogue

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * Custom test runner for Hilt integration tests.
 *
 * This runner replaces the application with HiltTestApplication,
 * enabling dependency injection in Android instrumented tests.
 *
 * Configured in app/build.gradle.kts:
 * ```
 * testInstrumentationRunner = "com.aurora.travlogue.HiltTestRunner"
 * ```
 */
class HiltTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
