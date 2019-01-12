package com.lucianpiros.bakingapp;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.lucianpiros.bakingapp.ui.MainActivity;
import com.lucianpiros.bakingapp.ui.RecipeActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeDetailsActivityInstrumentedTest {
    private static final int VIDEO_TAB = 1;
    private static final int INGREDIENTS_TAB = 0;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void registerIdlingResource() {
        idlingResource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void clickOnRecipe_showsRecipeDetails() {

        onView(withId(R.id.recipiesrecyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.sliding_tabs))
                .check(matches(isDisplayed()));
    }


    @Test
    public void clickOnIngredients_dontShowsVideoPlayerView() {

        Matcher<View> matcher = allOf(withText("Ingredients"),
                isDescendantOfA(withId(R.id.sliding_tabs)));
        onView(matcher).perform(click());
        SystemClock.sleep(800); // Wait a little until the content is loaded

        onView(
                allOf(
                        withId(R.id.video_view),
                        withParent(withParent(withId(R.id.viewpager))),
                        isDisplayed()))
                .check(doesNotExist());
    }

    @Test
    public void clickOnIngredients_showsIngredientsView() {

        Matcher<View> matcher = allOf(withText("Ingredients"),
                isDescendantOfA(withId(R.id.sliding_tabs)));
        onView(matcher).perform(click());
        SystemClock.sleep(800); // Wait a little until the content is loaded

        onView(withId(R.id.recipieingredientsrecyclerview))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickOnSteps_showsVideoPlayerView() {

        Matcher<View> matcher = allOf(withText("Steps"),
                isDescendantOfA(withId(R.id.sliding_tabs)));
        onView(matcher).perform(click());
        SystemClock.sleep(800); // Wait a little until the content is loaded

        onView(withId(R.id.video_view))
                .check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }
}
