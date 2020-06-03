package com.robosoft.paybaymax.data.repository

import ApiHelper

class QuoteRepository(private val apiHelper: ApiHelper) {

    suspend fun getData(currency: String) = apiHelper.getExchangeRates(currency)
    suspend fun getCurriencies() = apiHelper.getCurriencies()
    }
