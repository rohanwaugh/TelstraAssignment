package com.android.excercise.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.excercise.R
import com.android.excercise.data.DataState
import com.android.excercise.databinding.ActivityMainBinding
import com.android.excercise.di.DaggerCountryFactsComponent
import com.android.excercise.viewmodel.CountryFactsViewModel
import com.android.excercise.viewmodel.CountryFactsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var countryFactsViewModelFactory: CountryFactsViewModelFactory

    lateinit var countryFactsViewModel:CountryFactsViewModel

    lateinit var countryFactsAdapter: CountryFactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerCountryFactsComponent.builder().build().inject(this)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        countryFactsViewModel =
            ViewModelProviders.of(this, countryFactsViewModelFactory).get(CountryFactsViewModel::class.java)

        binding.countryFactsViewModel = countryFactsViewModel
        binding.lifecycleOwner = this

        setupRecyclerView()
        setUpSwipeToRefreshLayout()

        countryFactsViewModel.getCountryFactsDetails()

        observeCountryFactsData()
    }


    private fun setupRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            layoutManager = linearLayoutManager
            countryFactsAdapter = CountryFactsAdapter()
            adapter = countryFactsAdapter
        }
    }


    private fun setUpSwipeToRefreshLayout(){
        swipeRefresh.setOnRefreshListener{
            countryFactsViewModel.getCountryFactsDetails()
        }
    }

    private fun observeCountryFactsData(){
        countryFactsViewModel.getCountryDataState()
            ?.observe(this, Observer { factsDataStatus ->
                when (factsDataStatus) {

                    is DataState.Success -> {
                        Log.d("XYZ",factsDataStatus.countryData?.title)
                        countryFactsAdapter.countryFactList = factsDataStatus.countryData?.factList
                        countryFactsAdapter.notifyDataSetChanged()
                        swipeRefresh.isRefreshing = false
                    }

                    is DataState.Error -> {

                    }
                }
            })
    }
}
