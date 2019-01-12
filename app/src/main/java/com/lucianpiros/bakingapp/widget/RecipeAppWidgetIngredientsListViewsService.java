package com.lucianpiros.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Ingredient;

import java.util.List;

public class RecipeAppWidgetIngredientsListViewsService  extends RemoteViewsService {
    private List<Ingredient> recipeIngredientsList;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeAppWidgetIngredientsListViewsFactory(this.getApplicationContext(),intent);
    }

    class RecipeAppWidgetIngredientsListViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        Context mContext = null;

        public RecipeAppWidgetIngredientsListViewsFactory(Context context,Intent intent) {
            mContext = context;
        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            recipeIngredientsList = RecipeAppWidget.getRecipeIngredientsList();
        }

        @Override
        public void onDestroy() {
        }

        @Override
        public int getCount() {
            return recipeIngredientsList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.recipe_app_widget_ingredient);

            views.setTextViewText(R.id.widgetItemTaskNameLabel, recipeIngredientsList.get(position).getIngredient());

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
