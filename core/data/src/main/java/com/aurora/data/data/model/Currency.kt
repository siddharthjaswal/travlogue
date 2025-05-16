package com.aurora.data.data.model

enum class Currency(val code: String, val symbol: String) {
    USD("USD", "$"),
    EUR("EUR", "€"),
    GBP("GBP", "£"),
    INR("INR", "₹"),
    JPY("JPY", "¥"),
    CAD("CAD", "CA$"),
    AUD("AUD", "A$"),
    OTHER("OTHER", "")
}