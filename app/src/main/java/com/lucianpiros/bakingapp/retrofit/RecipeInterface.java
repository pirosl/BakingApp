package com.lucianpiros.bakingapp.retrofit;

import com.lucianpiros.bakingapp.retrofit.pojo.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeInterface {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipes();
}
