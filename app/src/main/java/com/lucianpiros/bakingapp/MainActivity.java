package com.lucianpiros.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lucianpiros.bakingapp.retrofit.RecipeInterface;
import com.lucianpiros.bakingapp.retrofit.RetrofitClient;
import com.lucianpiros.bakingapp.retrofit.pojo.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String RECIPIES_BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
            Get list of recipies
         **/
        RecipeInterface recipeInterface = RetrofitClient.getClient(RECIPIES_BASE_URL).create(RecipeInterface.class);

        Call<ArrayList<Recipe>> call = recipeInterface.getRecipes();

        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                for(Recipe r : response.body()) {
                    Log.d(TAG, r.getId() + " " + r.getName() + "\n");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
