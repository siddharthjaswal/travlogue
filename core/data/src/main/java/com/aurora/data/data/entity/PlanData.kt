package com.aurora.data.data.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "plans")
data class PlanData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "stay_name")
    var stayName: String?,

    @ColumnInfo(name = "stay_latitude")
    var stayLatitude: Double?,

    @ColumnInfo(name = "stay_longitude")
    var stayLongitude: Double?,

    @ColumnInfo(name = "stay_check_in")
    var stayCheckIn: Double?,

    @ColumnInfo(name = "stay_check_out")
    var stayCheckOut: Double?,

    @ColumnInfo(name = "stay_notes")
    var stayNotes: String?,

    @ColumnInfo(name = "stay_price")
    var stayPrice: Int?,

    @ColumnInfo(name = "transits")
    var transits: String?,

    @ColumnInfo(name = "details")
    var details: String?,

    @ColumnInfo(name = "status")
    var status: String?,

    @ColumnInfo(name = "expenses_travel")
    var expensesTravel: Double?,

    @ColumnInfo(name = "expenses_stay")
    var expensesStay: Double?,

    @ColumnInfo(name = "other_expenses")
    var otherExpenses: String?
)