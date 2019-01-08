package com.lucianpiros.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.RecipiesHolder;
import com.lucianpiros.bakingapp.data.adapters.RecipeIngredientsAdapter;

/**
 * Recipe ingredients fragment. Displays recipe ingredients
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipeIngredientsFragment extends Fragment {

  //  @BindView(R.id.recipeingredientslist)
    ListView recipeIngredientsListView;

    public RecipeIngredientsFragment() {

    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.recipe_ingredients_fragment, container, false);

        Bundle bundle = getArguments();
        int recipeIdx = bundle.getInt(getResources()
                .getString(R.string.activity_extra_param));

      //  ButterKnife.bind(rootView);
        recipeIngredientsListView = rootView.findViewById(R.id.recipeingredientslist);
        RecipeIngredientsAdapter recipeIngredientsAdapter = new RecipeIngredientsAdapter(getContext(), RecipiesHolder.getInstance().getRecipeIngredients(recipeIdx));
        recipeIngredientsListView.setAdapter(recipeIngredientsAdapter);

        return rootView;
    }
}
