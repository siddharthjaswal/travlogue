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

public class TransitOptionQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getTransitOptionsByGapId(gapId: String, mapper: (
    id: String,
    gapId: String,
    mode: String,
    provider: String?,
    duration: Long,
    price: Double?,
    currency: String?,
    departureTime: String?,
    arrivalTime: String?,
    fetchedAt: Long,
  ) -> T): Query<T> = GetTransitOptionsByGapIdQuery(gapId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getLong(4)!!,
      cursor.getDouble(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8),
      cursor.getLong(9)!!
    )
  }

  public fun getTransitOptionsByGapId(gapId: String): Query<Transit_options> =
      getTransitOptionsByGapId(gapId) { id, gapId_, mode, provider, duration, price, currency,
      departureTime, arrivalTime, fetchedAt ->
    Transit_options(
      id,
      gapId_,
      mode,
      provider,
      duration,
      price,
      currency,
      departureTime,
      arrivalTime,
      fetchedAt
    )
  }

  public fun <T : Any> getTransitOptionById(id: String, mapper: (
    id: String,
    gapId: String,
    mode: String,
    provider: String?,
    duration: Long,
    price: Double?,
    currency: String?,
    departureTime: String?,
    arrivalTime: String?,
    fetchedAt: Long,
  ) -> T): Query<T> = GetTransitOptionByIdQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getLong(4)!!,
      cursor.getDouble(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8),
      cursor.getLong(9)!!
    )
  }

  public fun getTransitOptionById(id: String): Query<Transit_options> = getTransitOptionById(id) {
      id_, gapId, mode, provider, duration, price, currency, departureTime, arrivalTime,
      fetchedAt ->
    Transit_options(
      id_,
      gapId,
      mode,
      provider,
      duration,
      price,
      currency,
      departureTime,
      arrivalTime,
      fetchedAt
    )
  }

  public fun insertTransitOption(
    id: String,
    gapId: String,
    mode: String,
    provider: String?,
    duration: Long,
    price: Double?,
    currency: String?,
    departureTime: String?,
    arrivalTime: String?,
    fetchedAt: Long,
  ) {
    driver.execute(-1_424_826_902, """
        |INSERT INTO transit_options (id, gapId, mode, provider, duration, price, currency, departureTime, arrivalTime, fetchedAt)
        |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 10) {
          bindString(0, id)
          bindString(1, gapId)
          bindString(2, mode)
          bindString(3, provider)
          bindLong(4, duration)
          bindDouble(5, price)
          bindString(6, currency)
          bindString(7, departureTime)
          bindString(8, arrivalTime)
          bindLong(9, fetchedAt)
        }
    notifyQueries(-1_424_826_902) { emit ->
      emit("transit_options")
    }
  }

  public fun updateTransitOption(
    mode: String,
    provider: String?,
    duration: Long,
    price: Double?,
    currency: String?,
    departureTime: String?,
    arrivalTime: String?,
    fetchedAt: Long,
    id: String,
  ) {
    driver.execute(263_176_666, """
        |UPDATE transit_options
        |SET mode = ?, provider = ?, duration = ?, price = ?, currency = ?, departureTime = ?, arrivalTime = ?, fetchedAt = ?
        |WHERE id = ?
        """.trimMargin(), 9) {
          bindString(0, mode)
          bindString(1, provider)
          bindLong(2, duration)
          bindDouble(3, price)
          bindString(4, currency)
          bindString(5, departureTime)
          bindString(6, arrivalTime)
          bindLong(7, fetchedAt)
          bindString(8, id)
        }
    notifyQueries(263_176_666) { emit ->
      emit("transit_options")
    }
  }

  public fun deleteTransitOption(id: String) {
    driver.execute(11_596_856, """
        |DELETE FROM transit_options
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(11_596_856) { emit ->
      emit("transit_options")
    }
  }

  public fun deleteTransitOptionsByGapId(gapId: String) {
    driver.execute(428_366_015, """
        |DELETE FROM transit_options
        |WHERE gapId = ?
        """.trimMargin(), 1) {
          bindString(0, gapId)
        }
    notifyQueries(428_366_015) { emit ->
      emit("transit_options")
    }
  }

  private inner class GetTransitOptionsByGapIdQuery<out T : Any>(
    public val gapId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("transit_options", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("transit_options", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_282_666_530, """
    |SELECT transit_options.id, transit_options.gapId, transit_options.mode, transit_options.provider, transit_options.duration, transit_options.price, transit_options.currency, transit_options.departureTime, transit_options.arrivalTime, transit_options.fetchedAt FROM transit_options
    |WHERE gapId = ?
    |ORDER BY duration ASC, price ASC
    """.trimMargin(), mapper, 1) {
      bindString(0, gapId)
    }

    override fun toString(): String = "TransitOption.sq:getTransitOptionsByGapId"
  }

  private inner class GetTransitOptionByIdQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("transit_options", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("transit_options", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(365_823_689, """
    |SELECT transit_options.id, transit_options.gapId, transit_options.mode, transit_options.provider, transit_options.duration, transit_options.price, transit_options.currency, transit_options.departureTime, transit_options.arrivalTime, transit_options.fetchedAt FROM transit_options
    |WHERE id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "TransitOption.sq:getTransitOptionById"
  }
}
