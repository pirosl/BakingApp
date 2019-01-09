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
import android.widget.AdapterView;
import android.widget.ListView;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.DataUpdateListener;
import com.lucianpiros.bakingapp.data.RecipiesHolder;
import com.lucianpiros.bakingapp.data.adapters.RecipiesAdapter;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Recipies fragment. Displays a list of recipies.
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipiesFragment extends Fragment implements DataUpdateListener, OnItemSelectedListener {

    RecyclerView recipiesListView;

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

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        recipiesListView.setLayoutManager(mLayoutManager);
        recipiesListView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    public void onItemSelected(int position) {
        Recipe recipe = RecipiesHolder.getInstance().getRecipeAtPosition(position);
        ((OnItemSelectedListener)getActivity()).onItemSelected(recipe.getId());
    }

    public void updateData() {
        RecipiesAdapter recipiesAdapter = new RecipiesAdapter(RecipiesHolder.getInstance().getRecipiesList(), this);
        recipiesListView.setAdapter(recipiesAdapter);
    }
}
