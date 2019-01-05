package com.lucianpiros.bakingapp.data;

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
}
