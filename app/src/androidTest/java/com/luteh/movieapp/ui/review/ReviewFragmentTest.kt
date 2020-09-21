package com.luteh.movieapp.ui.review

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.luteh.movieapp.R
import com.luteh.movieapp.ui.discover.adapter.DiscoverHolder
import com.luteh.movieapp.ui.main.MainActivity
import com.luteh.movieapp.ui.officialgenres.adapter.OfficialGenreHolder
import com.luteh.movieapp.utils.EspressoIdlingResourceRule
import org.junit.Rule
import org.junit.Test

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class ReviewFragmentTest {
    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    private fun navigate_official_genre_to_discover_screen() {
        with(onView(withId(R.id.rv_official_genre))) {
            perform(actionOnItemAtPosition<OfficialGenreHolder>(0, click()))
        }
    }

    private fun navigate_discover_to_detail_screen() {
        navigate_official_genre_to_discover_screen()

        with(onView(withId(R.id.rv_discover))) {
            perform(actionOnItemAtPosition<DiscoverHolder>(0, click()))
        }
    }

    @Test
    fun initial_state_review_screen() {
        navigate_discover_to_detail_screen()

        with(onView(withId(R.id.cv_see_review))) {
            perform(click())
        }

        onView(withId(R.id.tv_total_reviews)).check(matches(isDisplayed()))
    }

    @Test
    fun back_button_test() {
        initial_state_review_screen()
        pressBack()

        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
    }
}