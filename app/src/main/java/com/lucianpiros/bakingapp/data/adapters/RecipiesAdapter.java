package com.lucianpiros.bakingapp.data.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;
import com.lucianpiros.bakingapp.ui.OnItemSelectedListener;

import java.util.List;

/**
 * Recipe adapter. Used in RecipiesFragment class
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipiesAdapter extends RecyclerView.Adapter<RecipiesAdapter.ViewHolder> {

    // Recipies list
    private List<Recipe> recipiesList;

    // OnItemSelectedListener
    private OnItemSelectedListener onItemSelectedListener;

    /**
     * ViewHolder implementation
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipeNameTV;
        public ImageView recipeImageIV;;

        public ViewHolder(View view) {
            super(view);
            recipeNameTV = view.findViewById(R.id.recipename);
            recipeImageIV = view.findViewById(R.id.recipeimage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemSelectedListener.onItemSelected(getAdapterPosition()); // call the onClick in the OnItemSelectedListener
        }
    }

    /**
     * Class constructor
     * @param recipiesList
     * @param onItemSelectedListener
     */
    public RecipiesAdapter(List<Recipe> recipiesList, OnItemSelectedListener onItemSelectedListener) {
        this.recipiesList = recipiesList;
        this.onItemSelectedListener = onItemSelectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipiesList.get(position);
        // Populate the data into the template view using the data object
        holder.recipeNameTV.setText(recipe.getName());

        if(recipe.getImage().isEmpty()) {
            if(recipe.getId() % 2 == 0) {
                holder.recipeImageIV.setImageResource(R.drawable.placeholder_1);
            }
            else {
                holder.recipeImageIV.setImageResource(R.drawable.placeholder_2);
            }
        }
    }

    @Override
    public int getItemCount() {
        return recipiesList.size();
    }
}
