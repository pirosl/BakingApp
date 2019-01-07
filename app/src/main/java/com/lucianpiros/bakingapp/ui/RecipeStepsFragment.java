package com.lucianpiros.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucianpiros.bakingapp.R;

/**
 * Recipe steps fragment. Displays recipe steps
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipeStepsFragment extends Fragment {

    public RecipeStepsFragment() {

    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recipe_steps_fragment, container, false);
    }
}
