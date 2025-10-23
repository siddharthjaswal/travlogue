package com.aurora.travlogue.database

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class GapQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getGapsByTripId(tripId: String, mapper: (
    id: String,
    tripId: String,
    gapType: String,
    fromLocationId: String,
    toLocationId: String,
    fromDate: String,
    toDate: String,
    isResolved: Long,
  ) -> T): Query<T> = GetGapsByTripIdQuery(tripId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getLong(7)!!
    )
  }

  public fun getGapsByTripId(tripId: String): Query<Gaps> = getGapsByTripId(tripId) { id, tripId_,
      gapType, fromLocationId, toLocationId, fromDate, toDate, isResolved ->
    Gaps(
      id,
      tripId_,
      gapType,
      fromLocationId,
      toLocationId,
      fromDate,
      toDate,
      isResolved
    )
  }

  public fun <T : Any> getUnresolvedGapsByTripId(tripId: String, mapper: (
    id: String,
    tripId: String,
    gapType: String,
    fromLocationId: String,
    toLocationId: String,
    fromDate: String,
    toDate: String,
    isResolved: Long,
  ) -> T): Query<T> = GetUnresolvedGapsByTripIdQuery(tripId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getLong(7)!!
    )
  }

  public fun getUnresolvedGapsByTripId(tripId: String): Query<Gaps> =
      getUnresolvedGapsByTripId(tripId) { id, tripId_, gapType, fromLocationId, toLocationId,
      fromDate, toDate, isResolved ->
    Gaps(
      id,
      tripId_,
      gapType,
      fromLocationId,
      toLocationId,
      fromDate,
      toDate,
      isResolved
    )
  }

  public fun <T : Any> getGapById(id: String, mapper: (
    id: String,
    tripId: String,
    gapType: String,
    fromLocationId: String,
    toLocationId: String,
    fromDate: String,
    toDate: String,
    isResolved: Long,
  ) -> T): Query<T> = GetGapByIdQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getLong(7)!!
    )
  }

  public fun getGapById(id: String): Query<Gaps> = getGapById(id) { id_, tripId, gapType,
      fromLocationId, toLocationId, fromDate, toDate, isResolved ->
    Gaps(
      id_,
      tripId,
      gapType,
      fromLocationId,
      toLocationId,
      fromDate,
      toDate,
      isResolved
    )
  }

  public fun insertGap(
    id: String,
    tripId: String,
    gapType: String,
    fromLocationId: String,
    toLocationId: String,
    fromDate: String,
    toDate: String,
    isResolved: Long,
  ) {
    driver.execute(-382_143_382, """
        |INSERT INTO gaps (id, tripId, gapType, fromLocationId, toLocationId, fromDate, toDate, isResolved)
        |VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 8) {
          bindString(0, id)
          bindString(1, tripId)
          bindString(2, gapType)
          bindString(3, fromLocationId)
          bindString(4, toLocationId)
          bindString(5, fromDate)
          bindString(6, toDate)
          bindLong(7, isResolved)
        }
    notifyQueries(-382_143_382) { emit ->
      emit("gaps")
    }
  }

  public fun updateGap(
    gapType: String,
    fromLocationId: String,
    toLocationId: String,
    fromDate: String,
    toDate: String,
    isResolved: Long,
    id: String,
  ) {
    driver.execute(-1_946_876_838, """
        |UPDATE gaps
        |SET gapType = ?, fromLocationId = ?, toLocationId = ?, fromDate = ?, toDate = ?, isResolved = ?
        |WHERE id = ?
        """.trimMargin(), 7) {
          bindString(0, gapType)
          bindString(1, fromLocationId)
          bindString(2, toLocationId)
          bindString(3, fromDate)
          bindString(4, toDate)
          bindLong(5, isResolved)
          bindString(6, id)
        }
    notifyQueries(-1_946_876_838) { emit ->
      emit("gaps")
    }
  }

  public fun markGapAsResolved(id: String) {
    driver.execute(-394_135_040, """
        |UPDATE gaps
        |SET isResolved = 1
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(-394_135_040) { emit ->
      emit("gaps")
    }
  }

  public fun deleteGap(id: String) {
    driver.execute(-356_387_784, """
        |DELETE FROM gaps
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(-356_387_784) { emit ->
      emit("gaps")
      emit("transit_options")
    }
  }

  public fun deleteGapsByTripId(tripId: String) {
    driver.execute(128_823_218, """
        |DELETE FROM gaps
        |WHERE tripId = ?
        """.trimMargin(), 1) {
          bindString(0, tripId)
        }
    notifyQueries(128_823_218) { emit ->
      emit("gaps")
      emit("transit_options")
    }
  }

  private inner class GetGapsByTripIdQuery<out T : Any>(
    public val tripId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("gaps", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("gaps", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(2_135_245_463, """
    |SELECT gaps.id, gaps.tripId, gaps.gapType, gaps.fromLocationId, gaps.toLocationId, gaps.fromDate, gaps.toDate, gaps.isResolved FROM gaps
    |WHERE tripId = ?
    |ORDER BY fromDate ASC
    """.trimMargin(), mapper, 1) {
      bindString(0, tripId)
    }

    override fun toString(): String = "Gap.sq:getGapsByTripId"
  }

  private inner class GetUnresolvedGapsByTripIdQuery<out T : Any>(
    public val tripId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("gaps", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("gaps", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_588_834_552, """
    |SELECT gaps.id, gaps.tripId, gaps.gapType, gaps.fromLocationId, gaps.toLocationId, gaps.fromDate, gaps.toDate, gaps.isResolved FROM gaps
    |WHERE tripId = ? AND isResolved = 0
    |ORDER BY fromDate ASC
    """.trimMargin(), mapper, 1) {
      bindString(0, tripId)
    }

    override fun toString(): String = "Gap.sq:getUnresolvedGapsByTripId"
  }

  private inner class GetGapByIdQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("gaps", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("gaps", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(915_692_069, """
    |SELECT gaps.id, gaps.tripId, gaps.gapType, gaps.fromLocationId, gaps.toLocationId, gaps.fromDate, gaps.toDate, gaps.isResolved FROM gaps
    |WHERE id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "Gap.sq:getGapById"
  }
}
