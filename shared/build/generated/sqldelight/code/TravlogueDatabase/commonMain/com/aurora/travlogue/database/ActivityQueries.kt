package com.aurora.travlogue.database

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.String

public class ActivityQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getActivitiesByLocationId(locationId: String, mapper: (
    id: String,
    locationId: String,
    title: String,
    description: String?,
    date: String?,
    timeSlot: String?,
    type: String,
    startTime: String?,
    endTime: String?,
  ) -> T): Query<T> = GetActivitiesByLocationIdQuery(locationId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6)!!,
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun getActivitiesByLocationId(locationId: String): Query<Activities> =
      getActivitiesByLocationId(locationId) { id, locationId_, title, description, date, timeSlot,
      type, startTime, endTime ->
    Activities(
      id,
      locationId_,
      title,
      description,
      date,
      timeSlot,
      type,
      startTime,
      endTime
    )
  }

  public fun <T : Any> getActivitiesByTripId(tripId: String, mapper: (
    id: String,
    locationId: String,
    title: String,
    description: String?,
    date: String?,
    timeSlot: String?,
    type: String,
    startTime: String?,
    endTime: String?,
  ) -> T): Query<T> = GetActivitiesByTripIdQuery(tripId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6)!!,
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun getActivitiesByTripId(tripId: String): Query<Activities> =
      getActivitiesByTripId(tripId) { id, locationId, title, description, date, timeSlot, type,
      startTime, endTime ->
    Activities(
      id,
      locationId,
      title,
      description,
      date,
      timeSlot,
      type,
      startTime,
      endTime
    )
  }

  public fun <T : Any> getActivityById(id: String, mapper: (
    id: String,
    locationId: String,
    title: String,
    description: String?,
    date: String?,
    timeSlot: String?,
    type: String,
    startTime: String?,
    endTime: String?,
  ) -> T): Query<T> = GetActivityByIdQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6)!!,
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun getActivityById(id: String): Query<Activities> = getActivityById(id) { id_, locationId,
      title, description, date, timeSlot, type, startTime, endTime ->
    Activities(
      id_,
      locationId,
      title,
      description,
      date,
      timeSlot,
      type,
      startTime,
      endTime
    )
  }

  public fun insertActivity(
    id: String,
    locationId: String,
    title: String,
    description: String?,
    date: String?,
    timeSlot: String?,
    type: String,
    startTime: String?,
    endTime: String?,
  ) {
    driver.execute(343_230_518, """
        |INSERT INTO activities (id, locationId, title, description, date, timeSlot, type, startTime, endTime)
        |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 9) {
          bindString(0, id)
          bindString(1, locationId)
          bindString(2, title)
          bindString(3, description)
          bindString(4, date)
          bindString(5, timeSlot)
          bindString(6, type)
          bindString(7, startTime)
          bindString(8, endTime)
        }
    notifyQueries(343_230_518) { emit ->
      emit("activities")
    }
  }

  public fun updateActivity(
    title: String,
    description: String?,
    date: String?,
    timeSlot: String?,
    type: String,
    startTime: String?,
    endTime: String?,
    id: String,
  ) {
    driver.execute(-109_728_186, """
        |UPDATE activities
        |SET title = ?, description = ?, date = ?, timeSlot = ?, type = ?, startTime = ?, endTime = ?
        |WHERE id = ?
        """.trimMargin(), 8) {
          bindString(0, title)
          bindString(1, description)
          bindString(2, date)
          bindString(3, timeSlot)
          bindString(4, type)
          bindString(5, startTime)
          bindString(6, endTime)
          bindString(7, id)
        }
    notifyQueries(-109_728_186) { emit ->
      emit("activities")
    }
  }

  public fun deleteActivity(id: String) {
    driver.execute(1_262_090_536, """
        |DELETE FROM activities
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(1_262_090_536) { emit ->
      emit("activities")
    }
  }

  public fun deleteActivitiesByLocationId(locationId: String) {
    driver.execute(-94_490_035, """
        |DELETE FROM activities
        |WHERE locationId = ?
        """.trimMargin(), 1) {
          bindString(0, locationId)
        }
    notifyQueries(-94_490_035) { emit ->
      emit("activities")
    }
  }

  private inner class GetActivitiesByLocationIdQuery<out T : Any>(
    public val locationId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("activities", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("activities", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-849_883_428, """
    |SELECT activities.id, activities.locationId, activities.title, activities.description, activities.date, activities.timeSlot, activities.type, activities.startTime, activities.endTime FROM activities
    |WHERE locationId = ?
    |ORDER BY date ASC, startTime ASC
    """.trimMargin(), mapper, 1) {
      bindString(0, locationId)
    }

    override fun toString(): String = "Activity.sq:getActivitiesByLocationId"
  }

  private inner class GetActivitiesByTripIdQuery<out T : Any>(
    public val tripId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("activities", "locations", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("activities", "locations", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_873_691_316, """
    |SELECT a.id, a.locationId, a.title, a.description, a.date, a.timeSlot, a.type, a.startTime, a.endTime FROM activities a
    |INNER JOIN locations l ON a.locationId = l.id
    |WHERE l.tripId = ?
    |ORDER BY l.`order` ASC, a.date ASC, a.startTime ASC
    """.trimMargin(), mapper, 1) {
      bindString(0, tripId)
    }

    override fun toString(): String = "Activity.sq:getActivitiesByTripId"
  }

  private inner class GetActivityByIdQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("activities", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("activities", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_556_928_407, """
    |SELECT activities.id, activities.locationId, activities.title, activities.description, activities.date, activities.timeSlot, activities.type, activities.startTime, activities.endTime FROM activities
    |WHERE id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "Activity.sq:getActivityById"
  }
}
