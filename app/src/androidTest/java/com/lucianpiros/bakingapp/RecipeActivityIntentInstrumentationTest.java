package com.lucianpiros.bakingapp;

import com.lucianpiros.bakingapp.ui.MainActivity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;

public class RecipeActivityIntentInstrumentationTest {

    private static final String EXTRA_RECIPE_IDX = "recipeidx";
    private static final int EXTRA_RECIPE_IDX_VALUE = 0;

    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule
            = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void clickOnRecyclerViewItem_runsRecipeDetailsActivityIntent() {

        onView(ViewMatchers.withId(R.id.recipiesrecyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intended(
                hasExtra(EXTRA_RECIPE_IDX, EXTRA_RECIPE_IDX_VALUE)
        );
    }

}
