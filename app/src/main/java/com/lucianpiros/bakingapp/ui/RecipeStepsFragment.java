package com.lucianpiros.bakingapp.ui;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.RecipiesHolder;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Step;

import java.util.ArrayList;

/**
 * Recipe steps fragment. Displays recipe steps
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipeStepsFragment extends Fragment {

    private PlayerView playerView;
    private SimpleExoPlayer player;

    private TextView shortDescription;
    private TextView longDescription;

    private ArrayList<Step> recipeSteps;
    private int currentStep;

    public RecipeStepsFragment() {

    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.recipe_steps_fragment, container, false);
        playerView = rootView.findViewById(R.id.video_view);

        shortDescription = rootView.findViewById(R.id.step_shortdescription);
        longDescription = rootView.findViewById(R.id.step_longdescription);

        Bundle bundle = getArguments();
        int recipeIdx = bundle.getInt(getResources()
                .getString(R.string.activity_extra_param));

        recipeSteps = RecipiesHolder.getInstance().getRecipeSteps(recipeIdx);
        currentStep = 0;

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                rootView.findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_prev :
                                if(currentStep > 0) {
                                    currentStep--;
                                    releasePlayer();
                                    initializePlayer();
                                }
                                break;
                            case R.id.action_next:
                                if(currentStep < recipeSteps.size()) {
                                    currentStep++;
                                    releasePlayer();
                                    initializePlayer();
                                }
                                break;
                        }
                        return true;
                    }
                });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(getContext());

        playerView.setPlayer(player);

        if(currentStep >= 0 && currentStep < recipeSteps.size()) {
            Uri uri = Uri.parse(recipeSteps.get(currentStep).getVideoURL());
            MediaSource mediaSource = buildMediaSource(uri);
            player.prepare(mediaSource, true, false);

            shortDescription.setText(recipeSteps.get(currentStep).getShortDescription());
            longDescription.setText(recipeSteps.get(currentStep).getDescription());
        };
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer")).
                createMediaSource(uri);
    }
}
