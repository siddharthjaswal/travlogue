package com.aurora.travlogue.database

import kotlin.Double
import kotlin.Long
import kotlin.String

public data class Locations(
  public val id: String,
  public val tripId: String,
  public val name: String,
  public val country: String,
  public val date: String?,
  public val latitude: Double?,
  public val longitude: Double?,
  public val order: Long,
  public val timezone: String?,
  public val arrivalDateTime: String?,
  public val departureDateTime: String?,
)
