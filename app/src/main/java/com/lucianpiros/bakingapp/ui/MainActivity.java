package com.lucianpiros.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.adapters.RecipiesAdapter;
import com.lucianpiros.bakingapp.data.retrofit.RecipeInterface;
import com.lucianpiros.bakingapp.data.retrofit.RetrofitClient;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;

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
                ArrayList<Recipe> recipiesList = response.body();
                RecipiesAdapter recipiesAdapter = new RecipiesAdapter(getApplicationContext(), recipiesList);

                RecipiesFragment articleFrag = (RecipiesFragment)
                        getSupportFragmentManager().findFragmentById(R.id.fragment_recipieslist);
                articleFrag.updateRecipies(recipiesAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
