package com.android.excercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.excercise.data.DataState

class CountryFactsViewModel: ViewModel() {

    // MutableLiveData object is private so it will not get updated from outside
    private val countryFactsState: MutableLiveData<DataState>? = MutableLiveData()

    //Exposing live data to activity and not MutableLiveData
    fun getCountryDataState(): LiveData<DataState>? = countryFactsState

    fun getCountryFactsDetails(){

        countryFactsState?.value = DataState.Loading

    }

}