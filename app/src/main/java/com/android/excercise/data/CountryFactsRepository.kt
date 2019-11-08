package com.android.excercise.data

import com.android.excercise.data.api.ErrorResponse
import com.android.excercise.data.model.CountryDetails

interface CountryFactsRepository {

    suspend fun getCountryFacts(success: (CountryDetails?) -> (Unit), failure: (ErrorResponse) -> (Unit))
}