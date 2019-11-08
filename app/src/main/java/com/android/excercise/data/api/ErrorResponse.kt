package com.android.excercise.data.api

import com.android.excercise.utils.Constants.Companion.SERVICE_ERROR_MESSAGE
import com.google.gson.JsonParser
import retrofit2.HttpException

class ErrorResponse constructor(throwable: Throwable) {
    var errorMessage: String

    init {
        errorMessage = when (throwable) {
            is HttpException -> {
                val errorJsonString = throwable.response()?.errorBody()?.string()
                JsonParser()
                    .parse(errorJsonString)
                    .asJsonObject["message"]
                    .toString()
            }
            else -> throwable.message ?: SERVICE_ERROR_MESSAGE
        }
    }
}