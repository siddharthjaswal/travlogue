package com.aurora.data.data.converter

import androidx.room.TypeConverter
import com.aurora.data.data.model.Currency

class CurrencyConverter {
    @TypeConverter
    fun fromCurrency(currency: Currency?): String? {
        return currency?.code
    }

    @TypeConverter
    fun toCurrency(code: String?): Currency? {
        return code?.let { currencyCode ->
            try {
                Currency.entries.firstOrNull { it.code == currencyCode }
                    ?: Currency.OTHER
            } catch (e: IllegalArgumentException) {
                Currency.OTHER
            }
        }
    }
}