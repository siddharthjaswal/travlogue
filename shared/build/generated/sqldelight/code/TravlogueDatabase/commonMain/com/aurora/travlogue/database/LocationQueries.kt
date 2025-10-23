package com.aurora.travlogue.database

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Double
import kotlin.Long
import kotlin.String

public class LocationQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getLocationsByTripId(tripId: String, mapper: (
    id: String,
    tripId: String,
    name: String,
    country: String,
    date: String?,
    latitude: Double?,
    longitude: Double?,
    order: Long,
    timezone: String?,
    arrivalDateTime: String?,
    departureDateTime: String?,
  ) -> T): Query<T> = GetLocationsByTripIdQuery(tripId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4),
      cursor.getDouble(5),
      cursor.getDouble(6),
      cursor.getLong(7)!!,
      cursor.getString(8),
      cursor.getString(9),
      cursor.getString(10)
    )
  }

  public fun getLocationsByTripId(tripId: String): Query<Locations> = getLocationsByTripId(tripId) {
      id, tripId_, name, country, date, latitude, longitude, order, timezone, arrivalDateTime,
      departureDateTime ->
    Locations(
      id,
      tripId_,
      name,
      country,
      date,
      latitude,
      longitude,
      order,
      timezone,
      arrivalDateTime,
      departureDateTime
    )
  }

  public fun <T : Any> getLocationById(id: String, mapper: (
    id: String,
    tripId: String,
    name: String,
    country: String,
    date: String?,
    latitude: Double?,
    longitude: Double?,
    order: Long,
    timezone: String?,
    arrivalDateTime: String?,
    departureDateTime: String?,
  ) -> T): Query<T> = GetLocationByIdQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4),
      cursor.getDouble(5),
      cursor.getDouble(6),
      cursor.getLong(7)!!,
      cursor.getString(8),
      cursor.getString(9),
      cursor.getString(10)
    )
  }

  public fun getLocationById(id: String): Query<Locations> = getLocationById(id) { id_, tripId,
      name, country, date, latitude, longitude, order, timezone, arrivalDateTime,
      departureDateTime ->
    Locations(
      id_,
      tripId,
      name,
      country,
      date,
      latitude,
      longitude,
      order,
      timezone,
      arrivalDateTime,
      departureDateTime
    )
  }

  public fun countLocationsByTripId(tripId: String): Query<Long> =
      CountLocationsByTripIdQuery(tripId) { cursor ->
    cursor.getLong(0)!!
  }

  public fun insertLocation(
    id: String,
    tripId: String,
    name: String,
    country: String,
    date: String?,
    latitude: Double?,
    longitude: Double?,
    order: Long,
    timezone: String?,
    arrivalDateTime: String?,
    departureDateTime: String?,
  ) {
    driver.execute(-1_209_727_998, """
        |INSERT INTO locations (id, tripId, name, country, date, latitude, longitude, `order`, timezone, arrivalDateTime, departureDateTime)
        |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 11) {
          bindString(0, id)
          bindString(1, tripId)
          bindString(2, name)
          bindString(3, country)
          bindString(4, date)
          bindDouble(5, latitude)
          bindDouble(6, longitude)
          bindLong(7, order)
          bindString(8, timezone)
          bindString(9, arrivalDateTime)
          bindString(10, departureDateTime)
        }
    notifyQueries(-1_209_727_998) { emit ->
      emit("locations")
    }
  }

  public fun updateLocation(
    name: String,
    country: String,
    date: String?,
    latitude: Double?,
    longitude: Double?,
    order: Long,
    timezone: String?,
    arrivalDateTime: String?,
    departureDateTime: String?,
    id: String,
  ) {
    driver.execute(-1_662_686_702, """
        |UPDATE locations
        |SET name = ?, country = ?, date = ?, latitude = ?, longitude = ?, `order` = ?, timezone = ?, arrivalDateTime = ?, departureDateTime = ?
        |WHERE id = ?
        """.trimMargin(), 10) {
          bindString(0, name)
          bindString(1, country)
          bindString(2, date)
          bindDouble(3, latitude)
          bindDouble(4, longitude)
          bindLong(5, order)
          bindString(6, timezone)
          bindString(7, arrivalDateTime)
          bindString(8, departureDateTime)
          bindString(9, id)
        }
    notifyQueries(-1_662_686_702) { emit ->
      emit("locations")
    }
  }

  public fun deleteLocation(id: String) {
    driver.execute(-290_867_980, """
        |DELETE FROM locations
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(-290_867_980) { emit ->
      emit("activities")
      emit("gaps")
      emit("locations")
    }
  }

  public fun deleteLocationsByTripId(tripId: String) {
    driver.execute(-227_736_970, """
        |DELETE FROM locations
        |WHERE tripId = ?
        """.trimMargin(), 1) {
          bindString(0, tripId)
        }
    notifyQueries(-227_736_970) { emit ->
      emit("activities")
      emit("gaps")
      emit("locations")
    }
  }

  private inner class GetLocationsByTripIdQuery<out T : Any>(
    public val tripId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("locations", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("locations", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-456_161_389, """
    |SELECT locations.id, locations.tripId, locations.name, locations.country, locations.date, locations.latitude, locations.longitude, locations.`order`, locations.timezone, locations.arrivalDateTime, locations.departureDateTime FROM locations
    |WHERE tripId = ?
    |ORDER BY `order` ASC
    """.trimMargin(), mapper, 1) {
      bindString(0, tripId)
    }

    override fun toString(): String = "Location.sq:getLocationsByTripId"
  }

  private inner class GetLocationByIdQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("locations", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("locations", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(1_555_687_337, """
    |SELECT locations.id, locations.tripId, locations.name, locations.country, locations.date, locations.latitude, locations.longitude, locations.`order`, locations.timezone, locations.arrivalDateTime, locations.departureDateTime FROM locations
    |WHERE id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "Location.sq:getLocationById"
  }

  private inner class CountLocationsByTripIdQuery<out T : Any>(
    public val tripId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("locations", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("locations", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(613_827_066, """
    |SELECT COUNT(*) FROM locations
    |WHERE tripId = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, tripId)
    }

    override fun toString(): String = "Location.sq:countLocationsByTripId"
  }
}
