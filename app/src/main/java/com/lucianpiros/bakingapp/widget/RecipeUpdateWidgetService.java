package com.lucianpiros.bakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.lucianpiros.bakingapp.data.RecipiesHolder;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Recipe;

import java.util.List;
import java.util.Random;

public class RecipeUpdateWidgetService extends IntentService {

    public static final String ACTION_UPDATE_RECIPE_INGREDIENTS_LIST = "com.lucianpiros.bakingapp.widget.update_recipe_ingredients";

    public static final String ACTION_UPDATE_RECIPE_WIDGETS = "com.lucianpiros.bakingapp.widget.update_recipe_widget";
    public static final String  RECIPE_ID = "com.lucianpiros.bakingapp.widget.recipe_id";

    public RecipeUpdateWidgetService() {
        super("RecipeUpdateWidgetService");
    }

    /**
     * Starts this service to perform UpdateRecipeWidgets action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateRecipeWidgets(Context context) {
        Intent intent = new Intent(context, RecipeUpdateWidgetService.class);
        intent.setAction(ACTION_UPDATE_RECIPE_WIDGETS);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_RECIPE_WIDGETS.equals(action)) {
                handleActionUpdateRecipeWidgets();
            }
        }
    }

    /**
     * Handle action UpdateRecipeWidgets in the provided background thread
     */
    private void handleActionUpdateRecipeWidgets() {
        // pick a random recipe
        Random random = new Random();
        int idx = random.nextInt(Integer.MAX_VALUE);

        List<Recipe> recipes = RecipiesHolder.getInstance().getRecipiesList();
        idx = idx % recipes.size();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeAppWidget.class));
        RecipeAppWidget.updatePlantWidgets(this, appWidgetManager, idx ,appWidgetIds);

        Intent intent = new Intent(ACTION_UPDATE_RECIPE_INGREDIENTS_LIST);
        intent.setAction(ACTION_UPDATE_RECIPE_INGREDIENTS_LIST);
        intent.putExtra(RECIPE_ID, idx);
        sendBroadcast(intent);
    }
}
