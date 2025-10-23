package com.aurora.travlogue.database

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class TripQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getAllTrips(mapper: (
    id: String,
    name: String,
    originCity: String,
    dateType: String,
    startDate: String?,
    endDate: String?,
    flexibleMonth: String?,
    flexibleDuration: Long?,
    createdAt: Long,
    updatedAt: Long,
  ) -> T): Query<T> = Query(607_218_783, arrayOf("trips"), driver, "Trip.sq", "getAllTrips", """
  |SELECT trips.id, trips.name, trips.originCity, trips.dateType, trips.startDate, trips.endDate, trips.flexibleMonth, trips.flexibleDuration, trips.createdAt, trips.updatedAt FROM trips
  |ORDER BY createdAt DESC
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getLong(7),
      cursor.getLong(8)!!,
      cursor.getLong(9)!!
    )
  }

  public fun getAllTrips(): Query<Trips> = getAllTrips { id, name, originCity, dateType, startDate,
      endDate, flexibleMonth, flexibleDuration, createdAt, updatedAt ->
    Trips(
      id,
      name,
      originCity,
      dateType,
      startDate,
      endDate,
      flexibleMonth,
      flexibleDuration,
      createdAt,
      updatedAt
    )
  }

  public fun <T : Any> getTripById(id: String, mapper: (
    id: String,
    name: String,
    originCity: String,
    dateType: String,
    startDate: String?,
    endDate: String?,
    flexibleMonth: String?,
    flexibleDuration: Long?,
    createdAt: Long,
    updatedAt: Long,
  ) -> T): Query<T> = GetTripByIdQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getLong(7),
      cursor.getLong(8)!!,
      cursor.getLong(9)!!
    )
  }

  public fun getTripById(id: String): Query<Trips> = getTripById(id) { id_, name, originCity,
      dateType, startDate, endDate, flexibleMonth, flexibleDuration, createdAt, updatedAt ->
    Trips(
      id_,
      name,
      originCity,
      dateType,
      startDate,
      endDate,
      flexibleMonth,
      flexibleDuration,
      createdAt,
      updatedAt
    )
  }

  public fun countTrips(): Query<Long> = Query(-42_098_045, arrayOf("trips"), driver, "Trip.sq",
      "countTrips", "SELECT COUNT(*) FROM trips") { cursor ->
    cursor.getLong(0)!!
  }

  public fun insertTrip(
    id: String,
    name: String,
    originCity: String,
    dateType: String,
    startDate: String?,
    endDate: String?,
    flexibleMonth: String?,
    flexibleDuration: Long?,
    createdAt: Long,
    updatedAt: Long,
  ) {
    driver.execute(1_969_432_546, """
        |INSERT INTO trips (id, name, originCity, dateType, startDate, endDate, flexibleMonth, flexibleDuration, createdAt, updatedAt)
        |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 10) {
          bindString(0, id)
          bindString(1, name)
          bindString(2, originCity)
          bindString(3, dateType)
          bindString(4, startDate)
          bindString(5, endDate)
          bindString(6, flexibleMonth)
          bindLong(7, flexibleDuration)
          bindLong(8, createdAt)
          bindLong(9, updatedAt)
        }
    notifyQueries(1_969_432_546) { emit ->
      emit("trips")
    }
  }

  public fun updateTrip(
    name: String,
    originCity: String,
    dateType: String,
    startDate: String?,
    endDate: String?,
    flexibleMonth: String?,
    flexibleDuration: Long?,
    updatedAt: Long,
    id: String,
  ) {
    driver.execute(707_335_666, """
        |UPDATE trips
        |SET name = ?, originCity = ?, dateType = ?, startDate = ?, endDate = ?, flexibleMonth = ?, flexibleDuration = ?, updatedAt = ?
        |WHERE id = ?
        """.trimMargin(), 9) {
          bindString(0, name)
          bindString(1, originCity)
          bindString(2, dateType)
          bindString(3, startDate)
          bindString(4, endDate)
          bindString(5, flexibleMonth)
          bindLong(6, flexibleDuration)
          bindLong(7, updatedAt)
          bindString(8, id)
        }
    notifyQueries(707_335_666) { emit ->
      emit("trips")
    }
  }

  public fun deleteTrip(id: String) {
    driver.execute(-1_527_111_212, """
        |DELETE FROM trips
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(-1_527_111_212) { emit ->
      emit("bookings")
      emit("gaps")
      emit("locations")
      emit("trips")
    }
  }

  private inner class GetTripByIdQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("trips", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("trips", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(329_486_889, """
    |SELECT trips.id, trips.name, trips.originCity, trips.dateType, trips.startDate, trips.endDate, trips.flexibleMonth, trips.flexibleDuration, trips.createdAt, trips.updatedAt FROM trips
    |WHERE id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "Trip.sq:getTripById"
  }
}
