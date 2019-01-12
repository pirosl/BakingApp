package com.lucianpiros.bakingapp.data.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Ingredient;
import com.lucianpiros.bakingapp.util.ValuesUtil;

import java.util.List;

/**
 * Recipe ingredient adapter. Used in RecipieIngredientsFragment class
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.ViewHolder> {

    // Collection of recipe ingredients to be displayed through this adapted
    private List<Ingredient> recipeIngredientsList;

    /**
     * ViewHolder implementation
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeIngredientNameTV;

        public ViewHolder(View view) {
            super(view);
            recipeIngredientNameTV = view.findViewById(R.id.recipe_ingredient);
        }
    }

    /**
     * Class constructor
     *
     * @param recipeIngredientsList
     */
    public RecipeIngredientsAdapter(List<Ingredient> recipeIngredientsList) {
        this.recipeIngredientsList = recipeIngredientsList;
    }

    @Override
    public RecipeIngredientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_ingredient_row, parent, false);

        return new RecipeIngredientsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeIngredientsAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = recipeIngredientsList.get(position);
        // Populate the data into the template view using the data object
        Spannable quantity = new SpannableString(ingredient.getQuantity().toString());

        Context context = holder.recipeIngredientNameTV.getContext();

        quantity.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimaryChild)), 0, quantity.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.recipeIngredientNameTV.setText(quantity);
        holder.recipeIngredientNameTV.append(" ");

        Spannable measure = new SpannableString("");
        if(ValuesUtil.meassureMap.containsKey(ingredient.getMeasure())) {
            measure = new SpannableString(ValuesUtil.meassureMap.get(ingredient.getMeasure()));
        };
        measure.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.textColor)), 0, measure.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.recipeIngredientNameTV.append(measure);
        if(measure.length() > 0) {
            holder.recipeIngredientNameTV.append(" ");
        }

        Spannable ingred = new SpannableString(ingredient.getIngredient());
        ingred.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.textColor)), 0, ingred.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.recipeIngredientNameTV.append(ingred);
    }

    @Override
    public int getItemCount() {
        return recipeIngredientsList.size();
    }
}
