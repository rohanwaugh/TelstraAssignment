package com.android.excercise

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.annotation.UiThreadTest
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.android.excercise.data.DataState
import com.android.excercise.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/* This is Test Class for Recyclerview testing using Expresso framework.*/
class RecyclerviewTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> =
        object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
            override fun afterActivityLaunched() {
                super.afterActivityLaunched()
                IdlingRegistry.getInstance()
                    .register(CountryFactsEspressoIdlingResource.mCountingIdlingResource)
                CountryFactsEspressoIdlingResource.increment()
            }
        }


    @Before
    @UiThreadTest
    fun setUp() {
        val mainActivity: MainActivity = activityRule.activity
        mainActivity.countryFactsViewModel.getCountryDataState()
            ?.observe(activityRule.activity, Observer {
                when (it) {
                    is DataState.Success -> {
                        CountryFactsEspressoIdlingResource.decrement()
                    }
                    is DataState.Error -> {
                        CountryFactsEspressoIdlingResource.decrement()
                    }
                }
            })
    }


    @Test
    fun testCaseToCheckIfRecyclerIsVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testCaseForRecyclerScroll() {
        // Get total item of RecyclerView
        val recyclerView = activityRule.activity.recyclerView
        val itemCount = recyclerView.adapter!!.itemCount

        // Scroll to end of page with position
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }


    @Test
    fun testCaseForRecyclerClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
    }

    /* This IdelingResource class is used to synchronize Expresso Tests
    *  with the application background tasks. Using this we avoid the android.support.test.espresso.PerformException */

    object CountryFactsEspressoIdlingResource {

        private const val RESOURCE = "COUNTRY_FACTS"

        val mCountingIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment() {
            mCountingIdlingResource.increment()
        }

        fun decrement() {
            mCountingIdlingResource.decrement()
        }

    }

}