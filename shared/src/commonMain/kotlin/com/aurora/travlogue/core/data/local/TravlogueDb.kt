package com.aurora.travlogue.core.data.local

import com.aurora.travlogue.database.TravlogueDatabase

/**
 * Wrapper class for TravlogueDatabase that provides easy access to all queries
 */
class TravlogueDb(driverFactory: DatabaseDriverFactory) {
    private val driver = driverFactory.createDriver()
    private val database = TravlogueDatabase(driver)

    // Query accessors
    val tripQueries get() = database.tripQueries
    val locationQueries get() = database.locationQueries
    val activityQueries get() = database.activityQueries
    val bookingQueries get() = database.bookingQueries
    val gapQueries get() = database.gapQueries
    val transitOptionQueries get() = database.transitOptionQueries
}
