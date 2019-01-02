package com.lucianpiros.bakingapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.adapters.RecipiesAdapter;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;

import java.util.ArrayList;

public class RecipiesFragment extends Fragment {

    //@BindView(R.id.recipieslist)
    private ListView recipiesLis;

    public RecipiesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipies_fragment, container, false);

       // ButterKnife.bind(this, rootView);
        recipiesLis = (ListView) rootView.findViewById(R.id.recipieslist);

        return rootView;
    }

    public void updateRecipies(ArrayList<Recipe> recipiesList) {

        RecipiesAdapter recipiesAdapter = new RecipiesAdapter(getContext(), recipiesList);
        recipiesLis.setAdapter(recipiesAdapter);
    }
}
