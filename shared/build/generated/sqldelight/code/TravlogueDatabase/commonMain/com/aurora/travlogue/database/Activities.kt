package com.aurora.travlogue.database

import kotlin.String

public data class Activities(
  public val id: String,
  public val locationId: String,
  public val title: String,
  public val description: String?,
  public val date: String?,
  public val timeSlot: String?,
  public val type: String,
  public val startTime: String?,
  public val endTime: String?,
)
