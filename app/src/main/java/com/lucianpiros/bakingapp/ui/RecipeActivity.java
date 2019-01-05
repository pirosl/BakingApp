package com.lucianpiros.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lucianpiros.bakingapp.R;

/**
 * Recipies activity. Embeed recipe fragment
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipeActivity extends AppCompatActivity {
    private final String LOG_TAG = RecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            // Retrieve movie passed as parameter from Main Activity
/*            Bundle bundle = getIntent().getExtras();
            Uri movieUri = bundle.getParcelable(getResources()
                    .getString(R.string.activity_extra_param));

            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailsFragment.DETAIL_MOVIE, movieUri);
*/

            RecipeFragment fragment = new RecipeFragment();
          //  fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_recipe, fragment)
                    .commit();
        }
    }
}
