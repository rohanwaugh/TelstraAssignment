package com.android.excercise.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.excercise.R
import com.android.excercise.databinding.ActivityMainBinding
import com.android.excercise.viewmodel.CountryFactsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var countryFactsViewModel:CountryFactsViewModel

    lateinit var countryFactsAdapter: CountryFactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.countryFactsViewModel = countryFactsViewModel
        binding.lifecycleOwner = this

        setupRecyclerView()
        setUpSwipeToRefreshLayout()
    }


    private fun setupRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            layoutManager = linearLayoutManager

        }
    }


    private fun setUpSwipeToRefreshLayout(){
        swipeRefresh.setOnRefreshListener{
            // Need to call ViewModel
        }
    }
}
