package com.luteh.movieapp.ui.discover

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.luteh.movieapp.R
import com.luteh.discover.adapter.DiscoverHolder
import com.luteh.movieapp.ui.main.MainActivity
import com.luteh.genres.adapter.OfficialGenreHolder
import com.luteh.movieapp.utils.EspressoIdlingResourceRule
import com.luteh.movieapp.utils.RecyclerViewItemCountAssertion
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@RunWith(AndroidJUnit4::class)
class DiscoverFragmentTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    private fun navigate_official_genre_to_discover_screen() {
        with(onView(withId(R.id.rv_official_genre))) {
            perform(actionOnItemAtPosition<OfficialGenreHolder>(0, click()))
        }
    }

    @Test
    fun initial_state_discover_screen() {
        navigate_official_genre_to_discover_screen()

        with(onView(withId(R.id.rv_discover))) {
            check(matches(isDisplayed()))
            check(RecyclerViewItemCountAssertion(20))
        }
    }

    @Test
    fun scroll_to_eleventh_item_and_click() {
        navigate_official_genre_to_discover_screen()

        with(onView(withId(R.id.rv_discover))) {
            perform(scrollToPosition<DiscoverHolder>(10))
            perform(click())
        }
    }
}