package com.android.excercise.data

import com.android.excercise.data.api.CountryFactsAPIService
import com.android.excercise.data.api.ErrorResponse
import com.android.excercise.data.model.CountryDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryFactsRepositoryImplementation(private val countryFactsAPIService: CountryFactsAPIService) : CountryFactsRepository {
    override suspend fun getCountryFacts(
        success: (CountryDetails?) -> Unit,
        failure: (ErrorResponse) -> Unit
    ) {
        try {
            val response = withContext(Dispatchers.IO) {
                countryFactsAPIService.getCountryFacts()
            }
            if (response.isSuccessful) {
                success.invoke(response.body())
            } else {
                failure.invoke(ErrorResponse(Throwable()))
            }

        } catch (exception: Exception) {
            failure.invoke(ErrorResponse(exception))
        }
    }
}