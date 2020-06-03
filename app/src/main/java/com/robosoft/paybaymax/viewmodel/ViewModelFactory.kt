package com.robosoft.paybaymax.viewmodel

import ApiHelper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robosoft.paybaymax.data.repository.QuoteRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            return QuotesViewModel(QuoteRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}