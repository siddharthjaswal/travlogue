package com.aurora.travlogue.core.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.aurora.travlogue.database.TravlogueDatabase

/**
 * Desktop (JVM) implementation of DatabaseDriverFactory
 */
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        TravlogueDatabase.Schema.create(driver)
        return driver
    }
}
