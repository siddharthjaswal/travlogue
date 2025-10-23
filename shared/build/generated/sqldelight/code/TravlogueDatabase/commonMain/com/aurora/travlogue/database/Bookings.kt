package com.aurora.travlogue.database

import kotlin.Double
import kotlin.String

public data class Bookings(
  public val id: String,
  public val tripId: String,
  public val type: String,
  public val confirmationNumber: String?,
  public val provider: String,
  public val startDateTime: String,
  public val endDateTime: String?,
  public val timezone: String,
  public val endTimezone: String?,
  public val fromLocation: String?,
  public val toLocation: String?,
  public val price: Double?,
  public val currency: String?,
  public val notes: String?,
  public val imageUri: String?,
)
