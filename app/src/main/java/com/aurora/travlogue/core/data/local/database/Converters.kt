package com.aurora.travlogue.core.data.local.database

import androidx.room.TypeConverter
import com.aurora.travlogue.core.data.local.entities.*

class Converters {

    // DateType converters
    @TypeConverter
    fun fromDateType(value: DateType): String {
        return value.name
    }

    @TypeConverter
    fun toDateType(value: String): DateType {
        return DateType.valueOf(value)
    }

    // TimeSlot converters
    @TypeConverter
    fun fromTimeSlot(value: TimeSlot?): String? {
        return value?.name
    }

    @TypeConverter
    fun toTimeSlot(value: String?): TimeSlot? {
        return value?.let { TimeSlot.valueOf(it) }
    }

    // ActivityType converters
    @TypeConverter
    fun fromActivityType(value: ActivityType): String {
        return value.name
    }

    @TypeConverter
    fun toActivityType(value: String): ActivityType {
        return ActivityType.valueOf(value)
    }

    // BookingType converters
    @TypeConverter
    fun fromBookingType(value: BookingType): String {
        return value.name
    }

    @TypeConverter
    fun toBookingType(value: String): BookingType {
        return BookingType.valueOf(value)
    }

    // GapType converters
    @TypeConverter
    fun fromGapType(value: GapType): String {
        return value.name
    }

    @TypeConverter
    fun toGapType(value: String): GapType {
        return GapType.valueOf(value)
    }

    // TransitMode converters
    @TypeConverter
    fun fromTransitMode(value: TransitMode): String {
        return value.name
    }

    @TypeConverter
    fun toTransitMode(value: String): TransitMode {
        return TransitMode.valueOf(value)
    }
}
