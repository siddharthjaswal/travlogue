package com.aurora.travlogue.database

import kotlin.Double
import kotlin.Long
import kotlin.String

public data class Transit_options(
  public val id: String,
  public val gapId: String,
  public val mode: String,
  public val provider: String?,
  public val duration: Long,
  public val price: Double?,
  public val currency: String?,
  public val departureTime: String?,
  public val arrivalTime: String?,
  public val fetchedAt: Long,
)
