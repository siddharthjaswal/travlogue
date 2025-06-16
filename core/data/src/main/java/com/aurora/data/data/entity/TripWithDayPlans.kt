package com.aurora.data.data.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.aurora.data.data.entity.trip.TripEntity

data class TripWithDayPlans(
    @Embedded
    val trip: TripEntity,

    @Relation(
        parentColumn = "id",       // From TripEntity (the 'id' column)
        entityColumn = "trip_id",  // From DayPlanEntity (the 'trip_id' foreign key column)
        entity = DayEntity::class
    )
    val dayPlans: List<DayEntity>
)
