package com.aurora.travlogue.database.shared

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.aurora.travlogue.database.ActivityQueries
import com.aurora.travlogue.database.BookingQueries
import com.aurora.travlogue.database.GapQueries
import com.aurora.travlogue.database.LocationQueries
import com.aurora.travlogue.database.TransitOptionQueries
import com.aurora.travlogue.database.TravlogueDatabase
import com.aurora.travlogue.database.TripQueries
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<TravlogueDatabase>.schema: SqlSchema<QueryResult.Value<Unit>>
  get() = TravlogueDatabaseImpl.Schema

internal fun KClass<TravlogueDatabase>.newInstance(driver: SqlDriver): TravlogueDatabase =
    TravlogueDatabaseImpl(driver)

private class TravlogueDatabaseImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), TravlogueDatabase {
  override val activityQueries: ActivityQueries = ActivityQueries(driver)

  override val bookingQueries: BookingQueries = BookingQueries(driver)

  override val gapQueries: GapQueries = GapQueries(driver)

  override val locationQueries: LocationQueries = LocationQueries(driver)

  override val transitOptionQueries: TransitOptionQueries = TransitOptionQueries(driver)

  override val tripQueries: TripQueries = TripQueries(driver)

  public object Schema : SqlSchema<QueryResult.Value<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.Value<Unit> {
      driver.execute(null, """
          |CREATE TABLE activities (
          |    id TEXT PRIMARY KEY NOT NULL,
          |    locationId TEXT NOT NULL,
          |    title TEXT NOT NULL,
          |    description TEXT,
          |    date TEXT, -- ISO format: yyyy-MM-dd
          |    timeSlot TEXT, -- 'MORNING', 'AFTERNOON', 'EVENING', 'FULL_DAY'
          |    type TEXT NOT NULL, -- 'ATTRACTION', 'FOOD', 'BOOKING', 'TRANSIT', 'OTHER'
          |    startTime TEXT, -- HH:mm or ISO_LOCAL_TIME
          |    endTime TEXT, -- HH:mm or ISO_LOCAL_TIME
          |    FOREIGN KEY (locationId) REFERENCES locations(id) ON DELETE CASCADE
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE bookings (
          |    id TEXT PRIMARY KEY NOT NULL,
          |    tripId TEXT NOT NULL,
          |    type TEXT NOT NULL, -- 'FLIGHT', 'HOTEL', 'TRAIN', 'BUS', 'TICKET', 'OTHER'
          |    confirmationNumber TEXT,
          |    provider TEXT NOT NULL,
          |    startDateTime TEXT NOT NULL, -- ISO 8601 with timezone: "2025-11-15T14:30:00+01:00"
          |    endDateTime TEXT, -- ISO 8601 with timezone
          |    timezone TEXT NOT NULL, -- IANA timezone for start
          |    endTimezone TEXT, -- IANA timezone for end (if different)
          |    fromLocation TEXT,
          |    toLocation TEXT,
          |    price REAL,
          |    currency TEXT,
          |    notes TEXT,
          |    imageUri TEXT,
          |    FOREIGN KEY (tripId) REFERENCES trips(id) ON DELETE CASCADE
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE gaps (
          |    id TEXT PRIMARY KEY NOT NULL,
          |    tripId TEXT NOT NULL,
          |    gapType TEXT NOT NULL, -- 'MISSING_TRANSIT', 'UNPLANNED_DAY', 'TIME_CONFLICT'
          |    fromLocationId TEXT NOT NULL,
          |    toLocationId TEXT NOT NULL,
          |    fromDate TEXT NOT NULL, -- ISO format: yyyy-MM-dd
          |    toDate TEXT NOT NULL, -- ISO format: yyyy-MM-dd
          |    isResolved INTEGER NOT NULL DEFAULT 0, -- 0 = false, 1 = true
          |    FOREIGN KEY (tripId) REFERENCES trips(id) ON DELETE CASCADE,
          |    FOREIGN KEY (fromLocationId) REFERENCES locations(id) ON DELETE CASCADE,
          |    FOREIGN KEY (toLocationId) REFERENCES locations(id) ON DELETE CASCADE
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE locations (
          |    id TEXT PRIMARY KEY NOT NULL,
          |    tripId TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    country TEXT NOT NULL,
          |    date TEXT, -- ISO format: yyyy-MM-dd (deprecated, use arrivalDateTime)
          |    latitude REAL,
          |    longitude REAL,
          |    `order` INTEGER NOT NULL,
          |    timezone TEXT, -- IANA timezone: "Asia/Tokyo", "Europe/Paris"
          |    arrivalDateTime TEXT, -- ISO 8601 with timezone: "2025-07-01T14:30:00+09:00"
          |    departureDateTime TEXT, -- ISO 8601 with timezone: "2025-07-05T09:00:00+09:00"
          |    FOREIGN KEY (tripId) REFERENCES trips(id) ON DELETE CASCADE
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE transit_options (
          |    id TEXT PRIMARY KEY NOT NULL,
          |    gapId TEXT NOT NULL,
          |    mode TEXT NOT NULL, -- 'FLIGHT', 'TRAIN', 'BUS', 'CAR', 'FERRY'
          |    provider TEXT,
          |    duration INTEGER NOT NULL, -- minutes
          |    price REAL,
          |    currency TEXT,
          |    departureTime TEXT,
          |    arrivalTime TEXT,
          |    fetchedAt INTEGER NOT NULL,
          |    FOREIGN KEY (gapId) REFERENCES gaps(id) ON DELETE CASCADE
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE trips (
          |    id TEXT PRIMARY KEY NOT NULL,
          |    name TEXT NOT NULL,
          |    originCity TEXT NOT NULL,
          |    dateType TEXT NOT NULL, -- 'FIXED' or 'FLEXIBLE'
          |    startDate TEXT, -- ISO format: yyyy-MM-dd
          |    endDate TEXT, -- ISO format: yyyy-MM-dd
          |    flexibleMonth TEXT, -- e.g., "November 2025"
          |    flexibleDuration INTEGER, -- days
          |    createdAt INTEGER NOT NULL,
          |    updatedAt INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, "CREATE INDEX activities_locationId ON activities(locationId)", 0)
      driver.execute(null, "CREATE INDEX bookings_tripId ON bookings(tripId)", 0)
      driver.execute(null, "CREATE INDEX gaps_tripId ON gaps(tripId)", 0)
      driver.execute(null, "CREATE INDEX gaps_fromLocationId ON gaps(fromLocationId)", 0)
      driver.execute(null, "CREATE INDEX gaps_toLocationId ON gaps(toLocationId)", 0)
      driver.execute(null, "CREATE INDEX locations_tripId ON locations(tripId)", 0)
      driver.execute(null, "CREATE INDEX transit_options_gapId ON transit_options(gapId)", 0)
      return QueryResult.Unit
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.Value<Unit> = QueryResult.Unit
  }
}
