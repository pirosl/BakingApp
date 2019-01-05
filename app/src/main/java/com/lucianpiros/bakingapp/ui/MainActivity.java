package com.lucianpiros.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.retrofit.RecipesRetrieveService;

/**
 * Main activity class
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.recipe_fragment) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in master details mode.
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipe_fragment, new RecipeFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        }

        RecipiesFragment articleFrag = (RecipiesFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_recipieslist);
        RecipesRetrieveService recipesRetrieveService = new RecipesRetrieveService(articleFrag);
        recipesRetrieveService.run();


    }
}
