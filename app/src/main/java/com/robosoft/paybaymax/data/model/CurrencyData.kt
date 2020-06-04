package com.robosoft.paybaymax.data.model


data class CurrencyData(
    val success: Boolean,
    val error: Error,
    val terms: String,
    val privacy: String,
    val timestamp: Int,
    val source: String,
    val quotes: HashMap<String, Double>
)
