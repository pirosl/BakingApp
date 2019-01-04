package com.lucianpiros.bakingapp.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;

import java.util.ArrayList;

/**
 * Recipe adapter. Used in RecipiedFragment class
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipiesAdapter extends ArrayAdapter<Recipe> {

    public RecipiesAdapter(Context context, ArrayList<Recipe> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Recipe recipe = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_row, parent, false);
        }

        // Lookup view for data population
        TextView recipeNameTV = (TextView) convertView.findViewById(R.id.recipename);
        ImageView recipeImageIV = (ImageView) convertView.findViewById(R.id.recipeimage);

        // Populate the data into the template view using the data object
        recipeNameTV.setText(recipe.getName());

        if(recipe.getImage().isEmpty()) {
            if(recipe.getId() % 2 == 0) {
                recipeImageIV.setImageResource(R.drawable.placeholder_1);
            }
            else {
                recipeImageIV.setImageResource(R.drawable.placeholder_2);
            }
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
