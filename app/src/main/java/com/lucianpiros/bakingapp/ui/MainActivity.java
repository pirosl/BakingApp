package com.lucianpiros.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.retrofit.RecipesRetrieveService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipiesFragment articleFrag = (RecipiesFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_recipieslist);
        RecipesRetrieveService recipesRetrieveService = new RecipesRetrieveService(articleFrag);
        recipesRetrieveService.run();
    }
}
