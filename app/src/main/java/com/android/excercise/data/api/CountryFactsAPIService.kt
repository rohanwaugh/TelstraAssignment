package com.android.excercise.data.api

import com.android.excercise.data.model.CountryDetails
import retrofit2.Response
import retrofit2.http.GET

interface CountryFactsAPIService {

    @GET("/s/2iodh4vg0eortkl/facts.js")
    fun getCountryFacts(): Response<CountryDetails?>
}