package com.android.excercise.viewmodel

import androidx.lifecycle.*
import com.android.excercise.data.CountryFactsRepository
import com.android.excercise.data.DataState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryFactsViewModel @Inject constructor (private val countryFactsRepository: CountryFactsRepository): ViewModel() {

    // MutableLiveData object is private so it will not get updated from outside
    private val countryFactsState: MutableLiveData<DataState>? = MutableLiveData()

    //Exposing live data to activity and not MutableLiveData
    fun getCountryDataState(): LiveData<DataState>? = countryFactsState

    fun getCountryFactsDetails(){
        countryFactsState?.value = DataState.Loading
        viewModelScope.launch {
            countryFactsRepository.getCountryFacts(
                success = { countryFactDetails ->
                    countryFactsState?.value = DataState.Success(countryFactDetails)
                },
                failure = { errorResponse ->
                    countryFactsState?.value = DataState.Error(errorResponse)
                }
            )
        }
    }

}

@Suppress("UNCHECKED_CAST")
class CountryFactsViewModelFactory @Inject constructor (private val viewModel: CountryFactsViewModel) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CountryFactsViewModel::class.java)) {
            viewModel as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}