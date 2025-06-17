package com.aurora.data.data.entity.stay

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aurora.data.data.model.Currency

const val STAYS_TABLE_NAME = "stays"

@Entity(tableName = STAYS_TABLE_NAME)
data class StayEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "check_in_time") val checkInTime: Long? = null,
    @ColumnInfo(name = "check_out_time") val checkOutTime: Long? = null,
    @ColumnInfo(name = "notes") val notes: String? = null,
    @ColumnInfo(name = "link") val link: String? = null,
    @ColumnInfo(name = "price") val price: Double? = null,
    @ColumnInfo(name = "currency") val currency: Currency? = null
)
