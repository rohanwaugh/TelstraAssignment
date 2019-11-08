package com.android.excercise.di

import com.android.excercise.data.CountryFactsRepository
import com.android.excercise.data.CountryFactsRepositoryImplementation
import com.android.excercise.data.api.CountryFactsAPIService
import com.android.excercise.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CountryFactsModule {

    /* This method will provide Retrofit Instance*/
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /* This method will provide API */
    @Provides
    fun providesCountryFactsApiService(retrofit: Retrofit): CountryFactsAPIService {
        return retrofit.create(CountryFactsAPIService::class.java)
    }

    @Provides
    fun providesCountryFactsRepository(factsApiService: CountryFactsAPIService): CountryFactsRepository {
        return CountryFactsRepositoryImplementation(factsApiService)
    }
}