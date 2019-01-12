package com.lucianpiros.bakingapp.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.DataUpdateListener;
import com.lucianpiros.bakingapp.data.RecipiesHolder;
import com.lucianpiros.bakingapp.data.adapters.RecipiesAdapter;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;

/**
 * Recipies fragment. Displays a list of recipies.
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipiesFragment extends Fragment implements DataUpdateListener, OnItemSelectedListener {

    RecyclerView recipiesListView;
    TextView emptyTextView;

    public RecipiesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipies_fragment, container, false);

        recipiesListView = rootView.findViewById(R.id.recipiesrecyclerview);
        emptyTextView = rootView.findViewById(R.id.emptyview);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        recipiesListView.setLayoutManager(mLayoutManager);
        recipiesListView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    public void onItemSelected(int position) {
        Recipe recipe = RecipiesHolder.getInstance().getRecipeAtPosition(position);
        ((OnItemSelectedListener)getActivity()).onItemSelected(recipe.getId());
    }

    public void onInitialItemSelected() {
        // do nothing here
    }

    public void updateData() {
        RecipiesAdapter recipiesAdapter = new RecipiesAdapter(RecipiesHolder.getInstance().getRecipiesList(), this);
        recipiesListView.setAdapter(recipiesAdapter);

        if(RecipiesHolder.getInstance().getRecipiesList().size() > 0) {
            recipiesListView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
        }
        else {
            recipiesListView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        }
        ((OnItemSelectedListener) getActivity()).onInitialItemSelected();
    }
}
