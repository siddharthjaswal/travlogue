package com.aurora.travlogue.feature.tripdetail.domain.models

import com.aurora.travlogue.core.domain.model.Activity
import com.aurora.travlogue.core.domain.model.Location
import com.aurora.travlogue.core.domain.model.TimeSlot

/**
 * Represents a single day's schedule in the trip
 * Contains the date, location, and activities grouped by time slot
 */
data class DaySchedule(
    val date: String, // ISO format: yyyy-MM-dd
    val dayNumber: Int, // Day 1, Day 2, etc.
    val location: Location? = null,
    val activitiesByTimeSlot: Map<TimeSlot, List<Activity>> = emptyMap(),
    val dayNotes: String? = null,
    val hasActivities: Boolean = activitiesByTimeSlot.values.flatten().isNotEmpty()
)
