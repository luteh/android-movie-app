package com.luteh.movieapp.ui.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.luteh.movieapp.R
import com.luteh.discover.adapter.DiscoverHolder
import com.luteh.movieapp.ui.main.MainActivity
import com.luteh.genres.adapter.OfficialGenreHolder
import com.luteh.movieapp.utils.EspressoIdlingResourceRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Luthfan Maftuh on 9/18/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun navigate_official_genre_to_discover_screen() {
        with(onView(withId(R.id.rv_official_genre))) {
            perform(actionOnItemAtPosition<OfficialGenreHolder>(0, click()))
        }
    }

    @Test
    fun initial_state_detail_screen() {
        navigate_official_genre_to_discover_screen()

        with(onView(withId(R.id.rv_discover))) {
            perform(actionOnItemAtPosition<DiscoverHolder>(0, click()))
        }

        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
    }

    @Test
    fun back_button_test() {
        initial_state_detail_screen()
        pressBack()

        onView(withId(R.id.rv_discover)).check(matches(isDisplayed()))
    }
}