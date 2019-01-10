package com.lucianpiros.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.RecipiesHolder;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;
import com.lucianpiros.bakingapp.ui.MainActivity;

/**
 * Implementation of App Widget functionality.
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RecipeAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int recipeIdx, int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_app_widget);

        // populate Remoteviews object
        Recipe recipe = RecipiesHolder.getInstance().getRecipeAtPosition(recipeIdx);

        //views.setTextViewText(R.id.recipe_name, recipe.getName());

        if(recipe.getImage().isEmpty()) {
            if(recipe.getId() % 2 == 0) {
                views.setImageViewResource(R.id.recipe_image, R.drawable.placeholder_1);
            }
            else {
                views.setImageViewResource(R.id.recipe_image, R.drawable.placeholder_2);;
            }
        }
        // Create an Intent to launch MainActivity when clicked
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.recipe_image, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        RecipeUpdateWidgetService.startActionUpdateRecipeWidgets(context);
    }

    /**
     * Updates all widget instances given the widget Ids and display information
     *
     * @param context          The calling context
     * @param appWidgetManager The widget manager
     * @param recipeIdx        Recipe index
     * @param appWidgetIds     Array of widget Ids to be updated
     */
    public static void updatePlantWidgets(Context context, AppWidgetManager appWidgetManager,
                                          int recipeIdx, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipeIdx, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

