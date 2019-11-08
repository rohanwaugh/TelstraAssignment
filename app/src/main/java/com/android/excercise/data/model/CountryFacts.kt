package com.android.excercise.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryFacts(

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("imageHref")
    val image: String?

) : Parcelable