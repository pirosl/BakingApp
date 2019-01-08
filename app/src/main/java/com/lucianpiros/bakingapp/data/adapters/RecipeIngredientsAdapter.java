package com.lucianpiros.bakingapp.data.adapters;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Recipe ingredient adapter. Used in RecipieIngredientsFragment class
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipeIngredientsAdapter extends ArrayAdapter<Ingredient> {

    @BindView(R.id.recipe_ingredient)
    TextView recipeIngredientNameTV;

    private static final Map<String, String> meassureMap;
    static
    {
        meassureMap = new HashMap<>();
        meassureMap.put("G", "grams");
        meassureMap.put("UNIT", "");
        meassureMap.put("TSP", "tea spoon");
        meassureMap.put("TBLSP", "table spoon");
        meassureMap.put("CUP", "cup");
    }

    public RecipeIngredientsAdapter(Context context, ArrayList<Ingredient> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Ingredient ingredient = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_ingredient_row, parent, false);
        }

        ButterKnife.bind(this, convertView);

        // Populate the data into the template view using the data object
        Spannable quantity = new SpannableString(ingredient.getQuantity().toString());

        quantity.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.colorPrimaryChild)), 0, quantity.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        recipeIngredientNameTV.setText(quantity);
        recipeIngredientNameTV.append(" ");

        Spannable measure = new SpannableString("");
        if(meassureMap.containsKey(ingredient.getMeasure())) {
            measure = new SpannableString(meassureMap.get(ingredient.getMeasure()));
        };
        measure.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.textColor)), 0, measure.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        recipeIngredientNameTV.append(measure);
        if(measure.length() > 0) {
            recipeIngredientNameTV.append(" ");
        }

        Spannable ingred = new SpannableString(ingredient.getIngredient());
        ingred.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.textColor)), 0, ingred.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        recipeIngredientNameTV.append(ingred);

        // Return the completed view to render on screen
        return convertView;
    }
}
