package com.lucianpiros.bakingapp.data;

import com.lucianpiros.bakingapp.data.retrofit.pojo.Ingredient;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class used to temporarly store recipies
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipiesHolder {
    private static final RecipiesHolder recipiesHolder = new RecipiesHolder();

    // Recipies list
    private List<Recipe> recipiesList;

    private RecipiesHolder() {

    }

    public static RecipiesHolder getInstance() {
        return recipiesHolder;
    }

    /**
     * Save recipies list
     * @param recipiesList
     */
    public void storeRecipies(ArrayList<Recipe> recipiesList) {
        this.recipiesList = recipiesList;
    }

    /**
     * Retrieve recipies list
     * @return
     */
    public List<Recipe> getRecipiesList() {
        return recipiesList;
    }

    /**
     * Get recipe at given position
     * @param position
     * @return
     */
    public Recipe getRecipeAtPosition(int position) {
        return recipiesList.get(position);
    }

    /**
     * Retrieve ingredients for a given recipe
     * @param recipeIdx
     * @return
     */
    public List<Ingredient> getRecipeIngredients(int recipeIdx) {
        if(recipeIdx >= 0) {
            for (Recipe recipe : recipiesList) {
                if (recipe.getId() == recipeIdx) {
                    return recipe.getIngredients();
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * Retrieve steps for a given recipe
     * @param recipeIdx
     * @return
     */
    public ArrayList<Step> getRecipeSteps(int recipeIdx) {
        ArrayList<Step> ingredients = new ArrayList<>();
        if(recipeIdx >= 0) {
            for (Recipe recipe : recipiesList) {
                if (recipe.getId() == recipeIdx) {
                    ingredients = new ArrayList<>();
                    ingredients.addAll(recipe.getSteps());
                    return ingredients;
                }
            }
        }
        return ingredients;
    }
}
