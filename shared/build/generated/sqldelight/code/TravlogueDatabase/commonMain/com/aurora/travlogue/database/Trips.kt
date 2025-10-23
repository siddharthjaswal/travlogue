package com.aurora.travlogue.database

import kotlin.Long
import kotlin.String

public data class Trips(
  public val id: String,
  public val name: String,
  public val originCity: String,
  public val dateType: String,
  public val startDate: String?,
  public val endDate: String?,
  public val flexibleMonth: String?,
  public val flexibleDuration: Long?,
  public val createdAt: Long,
  public val updatedAt: Long,
)
