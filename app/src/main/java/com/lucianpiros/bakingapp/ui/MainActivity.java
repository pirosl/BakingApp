package com.lucianpiros.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.RecipiesHolder;
import com.lucianpiros.bakingapp.data.retrofit.RecipesRetrieveService;
import com.lucianpiros.bakingapp.data.retrofit.RetrofitIdlingResource;

/**
 * Main activity class
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    private boolean mMasterDetailFlow;

    @Nullable
    RetrofitIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.recipe_fragment) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in master details mode.
            mMasterDetailFlow = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipe_fragment, new RecipeFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        }
        else {
            mMasterDetailFlow = false;
        }

        RecipiesFragment recipiesFrag = (RecipiesFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_recipieslist);
        RecipesRetrieveService recipesRetrieveService = new RecipesRetrieveService(recipiesFrag, idlingResource);
        recipesRetrieveService.run();
    }

    public void onItemSelected(int itemIdx) {
        if (mMasterDetailFlow) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(RecipeFragment.RECIPE_IDX, itemIdx);

            RecipeFragment fragment = new RecipeFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_fragment, fragment)
                    .commit();
        }
        else {
            Intent intent = new Intent(this, RecipeActivity.class);
            intent.putExtra(getResources().getString(R.string.activity_extra_param), itemIdx);
            startActivity(intent);
        }
    }

    public void onInitialItemSelected() {
        if (mMasterDetailFlow) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            RecipeFragment fragment = new RecipeFragment();

            if(RecipiesHolder.getInstance().getRecipiesList().size() > 0) {
                Bundle arguments = new Bundle();
                arguments.putInt(RecipeFragment.RECIPE_IDX, RecipiesHolder.getInstance().getRecipiesList().get(0).getId());
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipe_fragment, fragment)
                        .commit();
            }
            else {
                findViewById(R.id.recipe_fragment).setVisibility(View.GONE);
            }
        }
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new RetrofitIdlingResource();
        }
        return idlingResource;
    }
}
