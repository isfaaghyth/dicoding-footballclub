package isfaaghyth.app.fotballclub.ui.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import isfaaghyth.app.fotballclub.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by isfaaghyth on 9/21/18.
 * github: @isfaaghyth
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        Thread.sleep(1500)
        onView(withId(lstPrevMatch)).check(matches(isDisplayed()))
        Thread.sleep(1500)
        onView(withId(lstPrevMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        onView(withId(lstPrevMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))
        Thread.sleep(1500)
        onView(withId(imgHomeTeam)).check(matches(isDisplayed()))
        Thread.sleep(1500)
        onView(withId(mnFavorite)).perform(click())
        Thread.sleep(1500)
        pressBack()
        Thread.sleep(1500)
        onView(withId(viewpagerMain)).perform(swipeLeft())
        Thread.sleep(1500)
        onView(withId(lstNextMatch)).check(matches(isDisplayed()))
        Thread.sleep(1500)
        onView(withId(lstNextMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(lstNextMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        Thread.sleep(1500)
        onView(withId(imgHomeTeam)).check(matches(isDisplayed()))
        pressBack()
        Thread.sleep(1500)
        onView(withId(viewpagerMain)).perform(swipeLeft())
        Thread.sleep(1500)
        onView(withId(lstFavMatch)).check(matches(isDisplayed()))
        Thread.sleep(1500)
        onView(withId(swipeRefresh)).perform(swipeDown())
        Thread.sleep(3000)
    }

}