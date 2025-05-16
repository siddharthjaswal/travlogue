package com.aurora.data.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TripWithDayPlans(
    @Embedded
    val trip: TripEntity,

    @Relation(
        parentColumn = "id",       // From TripEntity (the 'id' column)
        entityColumn = "trip_id",  // From DayPlanEntity (the 'trip_id' foreign key column)
        entity = DayPlanEntity::class
    )
    val dayPlans: List<DayPlanEntity>
)
