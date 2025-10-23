package com.aurora.travlogue.database

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.aurora.travlogue.database.shared.newInstance
import com.aurora.travlogue.database.shared.schema
import kotlin.Unit

public interface TravlogueDatabase : Transacter {
  public val activityQueries: ActivityQueries

  public val bookingQueries: BookingQueries

  public val gapQueries: GapQueries

  public val locationQueries: LocationQueries

  public val transitOptionQueries: TransitOptionQueries

  public val tripQueries: TripQueries

  public companion object {
    public val Schema: SqlSchema<QueryResult.Value<Unit>>
      get() = TravlogueDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): TravlogueDatabase =
        TravlogueDatabase::class.newInstance(driver)
  }
}
