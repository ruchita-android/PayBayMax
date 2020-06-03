package com.robosoft.paybaymax.viewmodel

import Resource
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import com.robosoft.paybaymax.data.model.CurrencyData
import com.robosoft.paybaymax.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers

class CurrenciesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    fun getCurriencies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = quoteRepository.getCurriencies()))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
    }


}