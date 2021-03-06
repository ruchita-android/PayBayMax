package com.robosoft.paybaymax.data.api

import com.robosoft.paybaymax.utils.Constants

class ApiHelper(private val apiInterface: ApiInterface) {

    suspend fun getCurrencies() = apiInterface.getCurrencies(Constants.ACCESS_KEY)
    suspend fun getExchangeRates(currency: String) =
        apiInterface.getExchangeRates(Constants.ACCESS_KEY, currency, Constants.FORMAT)

}