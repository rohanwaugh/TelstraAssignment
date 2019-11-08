package com.android.excercise.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryDetails(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("rows")
    val factList: List<CountryFacts>
):Parcelable