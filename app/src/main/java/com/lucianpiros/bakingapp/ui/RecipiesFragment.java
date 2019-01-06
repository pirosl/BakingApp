package com.lucianpiros.bakingapp.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
public class RecipiesFragment extends Fragment implements DataUpdateListener {

    @BindView(R.id.recipieslist)
    ListView recipiesListView;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callback {
        /**
         * When an item has been selected - notify up.
         */
        void onItemSelected(int itemIdx);
    }

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

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnItemClick(R.id.recipieslist)
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
        Recipe recipe = (Recipe) parent.getItemAtPosition(position);
        ((Callback)getActivity()).onItemSelected(recipe.getId());
    }

    public void updateData() {
        RecipiesAdapter recipiesAdapter = new RecipiesAdapter(getContext(), RecipiesHolder.getInstance().getRecipiesList());
        recipiesListView.setAdapter(recipiesAdapter);
    }
}
