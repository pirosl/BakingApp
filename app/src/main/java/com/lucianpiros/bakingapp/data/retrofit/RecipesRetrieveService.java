package com.lucianpiros.bakingapp.data.retrofit;

import com.lucianpiros.bakingapp.data.DataUpdateListener;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesRetrieveService {
    private final static String RECIPIES_BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    private DataUpdateListener<ArrayList<Recipe> > dataUpdateListener;

    public RecipesRetrieveService(DataUpdateListener<ArrayList<Recipe> > dataUpdateListener) {
        this.dataUpdateListener = dataUpdateListener;
    }

    public void run() {

        /**
         Get list of recipies
         **/
        RecipeInterface recipeInterface = RetrofitClient.getClient(RECIPIES_BASE_URL).create(RecipeInterface.class);

        Call<ArrayList<Recipe>> call = recipeInterface.getRecipes();

        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                ArrayList<Recipe> recipiesList = response.body();

                dataUpdateListener.updateData(recipiesList);
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
