package com.lucianpiros.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucianpiros.bakingapp.R;

/**
 * Recipe fragment. Displays a tabbed form
 * containing ingredients and recipe steps
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipeFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View detailsView = inflater.inflate(R.layout.recipe_fragment, container, false);
        TextView tv = (TextView)detailsView.findViewById(R.id.test);

        tv.setText("This is a test");

        return detailsView;
    }
}
