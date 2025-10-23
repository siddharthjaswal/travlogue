package com.aurora.travlogue.database

import kotlin.Long
import kotlin.String

public data class Gaps(
  public val id: String,
  public val tripId: String,
  public val gapType: String,
  public val fromLocationId: String,
  public val toLocationId: String,
  public val fromDate: String,
  public val toDate: String,
  public val isResolved: Long,
)
