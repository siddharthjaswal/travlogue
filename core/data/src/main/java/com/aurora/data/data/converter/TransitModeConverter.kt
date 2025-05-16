package com.aurora.data.data.converter

import androidx.room.TypeConverter
import com.aurora.data.data.model.TransitMode

class TransitModeConverter {
    @TypeConverter
    fun fromTransitMode(value: TransitMode?): String? {
        return value?.name
    }

    @TypeConverter
    fun toTransitMode(value: String?): TransitMode? {
        return value?.let {
            try {
                TransitMode.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
}