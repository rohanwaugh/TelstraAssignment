package com.android.excercise.data

import com.android.excercise.data.api.ErrorResponse
import com.android.excercise.data.model.CountryDetails


sealed class DataState {

    data class Success(val countryData: CountryDetails?) : DataState()

    data class Error(val error: ErrorResponse) : DataState()

    object Loading : DataState()
}