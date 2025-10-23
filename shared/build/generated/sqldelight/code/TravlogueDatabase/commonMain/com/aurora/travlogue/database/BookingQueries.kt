package com.aurora.travlogue.database

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Double
import kotlin.String

public class BookingQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getBookingsByTripId(tripId: String, mapper: (
    id: String,
    tripId: String,
    type: String,
    confirmationNumber: String?,
    provider: String,
    startDateTime: String,
    endDateTime: String?,
    timezone: String,
    endTimezone: String?,
    fromLocation: String?,
    toLocation: String?,
    price: Double?,
    currency: String?,
    notes: String?,
    imageUri: String?,
  ) -> T): Query<T> = GetBookingsByTripIdQuery(tripId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6),
      cursor.getString(7)!!,
      cursor.getString(8),
      cursor.getString(9),
      cursor.getString(10),
      cursor.getDouble(11),
      cursor.getString(12),
      cursor.getString(13),
      cursor.getString(14)
    )
  }

  public fun getBookingsByTripId(tripId: String): Query<Bookings> = getBookingsByTripId(tripId) {
      id, tripId_, type, confirmationNumber, provider, startDateTime, endDateTime, timezone,
      endTimezone, fromLocation, toLocation, price, currency, notes, imageUri ->
    Bookings(
      id,
      tripId_,
      type,
      confirmationNumber,
      provider,
      startDateTime,
      endDateTime,
      timezone,
      endTimezone,
      fromLocation,
      toLocation,
      price,
      currency,
      notes,
      imageUri
    )
  }

  public fun <T : Any> getBookingById(id: String, mapper: (
    id: String,
    tripId: String,
    type: String,
    confirmationNumber: String?,
    provider: String,
    startDateTime: String,
    endDateTime: String?,
    timezone: String,
    endTimezone: String?,
    fromLocation: String?,
    toLocation: String?,
    price: Double?,
    currency: String?,
    notes: String?,
    imageUri: String?,
  ) -> T): Query<T> = GetBookingByIdQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6),
      cursor.getString(7)!!,
      cursor.getString(8),
      cursor.getString(9),
      cursor.getString(10),
      cursor.getDouble(11),
      cursor.getString(12),
      cursor.getString(13),
      cursor.getString(14)
    )
  }

  public fun getBookingById(id: String): Query<Bookings> = getBookingById(id) { id_, tripId, type,
      confirmationNumber, provider, startDateTime, endDateTime, timezone, endTimezone, fromLocation,
      toLocation, price, currency, notes, imageUri ->
    Bookings(
      id_,
      tripId,
      type,
      confirmationNumber,
      provider,
      startDateTime,
      endDateTime,
      timezone,
      endTimezone,
      fromLocation,
      toLocation,
      price,
      currency,
      notes,
      imageUri
    )
  }

  public fun <T : Any> getTransitBookingsByTripId(tripId: String, mapper: (
    id: String,
    tripId: String,
    type: String,
    confirmationNumber: String?,
    provider: String,
    startDateTime: String,
    endDateTime: String?,
    timezone: String,
    endTimezone: String?,
    fromLocation: String?,
    toLocation: String?,
    price: Double?,
    currency: String?,
    notes: String?,
    imageUri: String?,
  ) -> T): Query<T> = GetTransitBookingsByTripIdQuery(tripId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6),
      cursor.getString(7)!!,
      cursor.getString(8),
      cursor.getString(9),
      cursor.getString(10),
      cursor.getDouble(11),
      cursor.getString(12),
      cursor.getString(13),
      cursor.getString(14)
    )
  }

  public fun getTransitBookingsByTripId(tripId: String): Query<Bookings> =
      getTransitBookingsByTripId(tripId) { id, tripId_, type, confirmationNumber, provider,
      startDateTime, endDateTime, timezone, endTimezone, fromLocation, toLocation, price, currency,
      notes, imageUri ->
    Bookings(
      id,
      tripId_,
      type,
      confirmationNumber,
      provider,
      startDateTime,
      endDateTime,
      timezone,
      endTimezone,
      fromLocation,
      toLocation,
      price,
      currency,
      notes,
      imageUri
    )
  }

  public fun insertBooking(
    id: String,
    tripId: String,
    type: String,
    confirmationNumber: String?,
    provider: String,
    startDateTime: String,
    endDateTime: String?,
    timezone: String,
    endTimezone: String?,
    fromLocation: String?,
    toLocation: String?,
    price: Double?,
    currency: String?,
    notes: String?,
    imageUri: String?,
  ) {
    driver.execute(710_488_138, """
        |INSERT INTO bookings (id, tripId, type, confirmationNumber, provider, startDateTime, endDateTime, timezone, endTimezone, fromLocation, toLocation, price, currency, notes, imageUri)
        |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 15) {
          bindString(0, id)
          bindString(1, tripId)
          bindString(2, type)
          bindString(3, confirmationNumber)
          bindString(4, provider)
          bindString(5, startDateTime)
          bindString(6, endDateTime)
          bindString(7, timezone)
          bindString(8, endTimezone)
          bindString(9, fromLocation)
          bindString(10, toLocation)
          bindDouble(11, price)
          bindString(12, currency)
          bindString(13, notes)
          bindString(14, imageUri)
        }
    notifyQueries(710_488_138) { emit ->
      emit("bookings")
    }
  }

  public fun updateBooking(
    type: String,
    confirmationNumber: String?,
    provider: String,
    startDateTime: String,
    endDateTime: String?,
    timezone: String,
    endTimezone: String?,
    fromLocation: String?,
    toLocation: String?,
    price: Double?,
    currency: String?,
    notes: String?,
    imageUri: String?,
    id: String,
  ) {
    driver.execute(-273_954_758, """
        |UPDATE bookings
        |SET type = ?, confirmationNumber = ?, provider = ?, startDateTime = ?, endDateTime = ?, timezone = ?, endTimezone = ?, fromLocation = ?, toLocation = ?, price = ?, currency = ?, notes = ?, imageUri = ?
        |WHERE id = ?
        """.trimMargin(), 14) {
          bindString(0, type)
          bindString(1, confirmationNumber)
          bindString(2, provider)
          bindString(3, startDateTime)
          bindString(4, endDateTime)
          bindString(5, timezone)
          bindString(6, endTimezone)
          bindString(7, fromLocation)
          bindString(8, toLocation)
          bindDouble(9, price)
          bindString(10, currency)
          bindString(11, notes)
          bindString(12, imageUri)
          bindString(13, id)
        }
    notifyQueries(-273_954_758) { emit ->
      emit("bookings")
    }
  }

  public fun deleteBooking(id: String) {
    driver.execute(1_017_223_448, """
        |DELETE FROM bookings
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(1_017_223_448) { emit ->
      emit("bookings")
    }
  }

  public fun deleteBookingsByTripId(tripId: String) {
    driver.execute(761_890_514, """
        |DELETE FROM bookings
        |WHERE tripId = ?
        """.trimMargin(), 1) {
          bindString(0, tripId)
        }
    notifyQueries(761_890_514) { emit ->
      emit("bookings")
    }
  }

  private inner class GetBookingsByTripIdQuery<out T : Any>(
    public val tripId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("bookings", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("bookings", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(341_665_169, """
    |SELECT bookings.id, bookings.tripId, bookings.type, bookings.confirmationNumber, bookings.provider, bookings.startDateTime, bookings.endDateTime, bookings.timezone, bookings.endTimezone, bookings.fromLocation, bookings.toLocation, bookings.price, bookings.currency, bookings.notes, bookings.imageUri FROM bookings
    |WHERE tripId = ?
    |ORDER BY startDateTime ASC
    """.trimMargin(), mapper, 1) {
      bindString(0, tripId)
    }

    override fun toString(): String = "Booking.sq:getBookingsByTripId"
  }

  private inner class GetBookingByIdQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("bookings", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("bookings", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(1_214_038_763, """
    |SELECT bookings.id, bookings.tripId, bookings.type, bookings.confirmationNumber, bookings.provider, bookings.startDateTime, bookings.endDateTime, bookings.timezone, bookings.endTimezone, bookings.fromLocation, bookings.toLocation, bookings.price, bookings.currency, bookings.notes, bookings.imageUri FROM bookings
    |WHERE id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "Booking.sq:getBookingById"
  }

  private inner class GetTransitBookingsByTripIdQuery<out T : Any>(
    public val tripId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("bookings", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("bookings", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-425_790_524, """
    |SELECT bookings.id, bookings.tripId, bookings.type, bookings.confirmationNumber, bookings.provider, bookings.startDateTime, bookings.endDateTime, bookings.timezone, bookings.endTimezone, bookings.fromLocation, bookings.toLocation, bookings.price, bookings.currency, bookings.notes, bookings.imageUri FROM bookings
    |WHERE tripId = ? AND type IN ('FLIGHT', 'TRAIN', 'BUS')
    |ORDER BY startDateTime ASC
    """.trimMargin(), mapper, 1) {
      bindString(0, tripId)
    }

    override fun toString(): String = "Booking.sq:getTransitBookingsByTripId"
  }
}
