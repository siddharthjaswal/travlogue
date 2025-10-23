package com.aurora.travlogue.core.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.aurora.travlogue.database.TravlogueDatabase

/**
 * iOS implementation of DatabaseDriverFactory
 */
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = TravlogueDatabase.Schema,
            name = "travlogue.db"
        )
    }
}
