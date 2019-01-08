package com.lucianpiros.bakingapp.data;

import com.lucianpiros.bakingapp.data.retrofit.pojo.Ingredient;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;

import java.util.ArrayList;

public class RecipiesHolder {
    private static final RecipiesHolder recipiesHolder = new RecipiesHolder();

    private ArrayList<Recipe> recipiesList;

    private RecipiesHolder() {

    }

    public static RecipiesHolder getInstance() {
        return recipiesHolder;
    }

    public void storeRecipies(ArrayList<Recipe> recipiesList) {
        this.recipiesList = recipiesList;
    }

    public ArrayList<Recipe> getRecipiesList() {
        return recipiesList;
    }

    public ArrayList<Ingredient> getRecipeIngredients(int recipeIdx) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        if(recipeIdx >= 0) {
            for (Recipe recipe : recipiesList) {
                if (recipe.getId() == recipeIdx) {
                    ingredients = new ArrayList<>();
                    ingredients.addAll(recipe.getIngredients());
                    return ingredients;
                }
            }
        }
        return ingredients;
    }
}
