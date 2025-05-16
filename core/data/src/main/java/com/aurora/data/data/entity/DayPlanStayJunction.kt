package com.aurora.data.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

const val DAY_PLANS_STAY_JUNCTION_TABLE_NAME = "day_plan_stay_junction"

@Entity(
    tableName = DAY_PLANS_STAY_JUNCTION_TABLE_NAME,
    primaryKeys = ["day_plan_id", "stay_id"],
    foreignKeys = [
        ForeignKey(
            entity = DayPlanEntity::class,
            parentColumns = ["id"],
            childColumns = ["day_plan_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StayEntity::class,
            parentColumns = ["id"],
            childColumns = ["stay_id"],
            onDelete = ForeignKey.CASCADE // Or SET_NULL/RESTRICT if a stay can exist independently
            // and be unlinked from a day plan. CASCADE is often simpler.
        )
    ],
    indices = [Index(value = ["day_plan_id"]), Index(value = ["stay_id"])]
)
data class DayPlanStayJunction(
    @ColumnInfo(name = "day_plan_id") val dayPlanId: Long,
    @ColumnInfo(name = "stay_id") val stayId: Long
)
