package com.luteh.movieapp.ui.officialgenres

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.luteh.movieapp.R
import com.luteh.movieapp.ui.main.MainActivity
import com.luteh.genres.adapter.OfficialGenreHolder
import com.luteh.movieapp.utils.EspressoIdlingResourceRule
import com.luteh.movieapp.utils.RecyclerViewItemCountAssertion
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class OfficialGenreFragmentTest {
    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun initial_state_official_genre_screen() {
        onView(withId(R.id.pb_common)).check(matches(not(isDisplayed())))
        with(onView(withId(R.id.rv_official_genre))) {
            check(matches(isDisplayed()))
            check(RecyclerViewItemCountAssertion(19))
        }
    }

    @Test
    fun scroll_to_eleventh_item_and_click() {
        with(onView(withId(R.id.rv_official_genre))) {
            perform(RecyclerViewActions.scrollToPosition<OfficialGenreHolder>(19))
            perform(click())
        }
    }
}