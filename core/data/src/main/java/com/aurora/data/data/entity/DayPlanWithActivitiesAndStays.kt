package com.aurora.data.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class DayPlanWithActivitiesAndStays(
    @Embedded
    val dayPlan: DayPlanEntity,

    @Relation(
        parentColumn = "id", // DayPlanEntity.id
        entityColumn = "day_plan_id" // ActivityEntity.day_plan_id
    )
    val activities: List<ActivityEntity>,

    @Relation(
        parentColumn = "id", // DayPlanEntity.id
        entityColumn = "id",   // StayEntity.id
        associateBy = Junction(
            value = DayPlanStayJunction::class,
            parentColumn = "day_plan_id", // In DayPlanStayJunction, links to DayPlanEntity.id
            entityColumn = "stay_id"      // In DayPlanStayJunction, links to StayEntity.id
        )
    )
    val stays: List<StayEntity>
)
