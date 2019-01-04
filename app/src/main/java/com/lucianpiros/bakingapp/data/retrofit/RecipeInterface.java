package com.lucianpiros.bakingapp.data.retrofit;

import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit call
 *
 * @author Lucian Piros
 * @version 1.0
 */
public interface RecipeInterface {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipes();
}
