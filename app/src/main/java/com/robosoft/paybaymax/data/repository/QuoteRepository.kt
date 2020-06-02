package com.robosoft.paybaymax.data.repository

import ApiHelper

class QuoteRepository(private val apiHelper: ApiHelper) {

    suspend fun getData() = apiHelper.getData()
    }
