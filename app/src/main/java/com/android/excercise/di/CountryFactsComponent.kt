package com.android.excercise.di

import com.android.excercise.ui.MainActivity
import dagger.Component

@Component(modules = [CountryFactsModule::class])
interface CountryFactsComponent {
    fun inject(mainActivity: MainActivity)
}