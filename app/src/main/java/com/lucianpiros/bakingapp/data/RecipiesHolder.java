package com.lucianpiros.bakingapp.data;

import com.lucianpiros.bakingapp.data.retrofit.pojo.Ingredient;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipiesHolder {
    private static final RecipiesHolder recipiesHolder = new RecipiesHolder();

    private List<Recipe> recipiesList;

    private RecipiesHolder() {

    }

    public static RecipiesHolder getInstance() {
        return recipiesHolder;
    }

    public void storeRecipies(ArrayList<Recipe> recipiesList) {
        this.recipiesList = recipiesList;
    }

    public List<Recipe> getRecipiesList() {
        return recipiesList;
    }

    public Recipe getRecipeAtPosition(int position) {
        return recipiesList.get(position);
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
