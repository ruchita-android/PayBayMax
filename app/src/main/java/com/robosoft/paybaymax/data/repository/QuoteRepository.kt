package com.robosoft.paybaymax.data.repository

import com.robosoft.paybaymax.data.api.ApiHelper

class QuoteRepository(private val apiHelper: ApiHelper) {

    suspend fun getData(currency: String) = apiHelper.getExchangeRates(currency)
    suspend fun getCurrencies() = apiHelper.getCurrencies()
}
