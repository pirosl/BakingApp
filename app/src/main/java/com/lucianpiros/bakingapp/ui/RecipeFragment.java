package com.lucianpiros.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucianpiros.bakingapp.R;

/**
 * Recipe fragment. Displays a tabbed form
 * containing ingredients and recipe steps
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipeFragment extends Fragment {

    public static final String RECIPE_IDX = "recipeidx";


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View detailsView = inflater.inflate(R.layout.recipe_fragment, container, false);

        ViewPager viewPager = (ViewPager) detailsView.findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        RecipeFragmentPagerAdapter adapter = new RecipeFragmentPagerAdapter(getContext(), getFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) detailsView.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return detailsView;
    }

    class RecipeFragmentPagerAdapter extends FragmentPagerAdapter {

        private Context mContext;

        public RecipeFragmentPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        // This determines the fragment for each tab
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new RecipeIngredientsFragment();
            } else if (position == 1){
                return new RecipeStepsFragment();
            }
            return null;
        }

        // This determines the number of tabs
        @Override
        public int getCount() {
            return 2;
        }

        // This determines the title for each tab
        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            switch (position) {
                case 0:
                    return "Ingredients";//mContext.getString(R.string.category_usefulinfo);
                case 1:
                    return "Steps";//mContext.getString(R.string.category_places);
                default:
                    return null;
            }
        }

    }
}
