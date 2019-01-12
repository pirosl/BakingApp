package com.lucianpiros.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.lucianpiros.bakingapp.R;
import com.lucianpiros.bakingapp.data.retrofit.pojo.Ingredient;
import com.lucianpiros.bakingapp.util.ValuesUtil;

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

            Ingredient ingredient = recipeIngredientsList.get(position);

            StringBuffer infoToBeDisplayed = new StringBuffer();
            infoToBeDisplayed.append(new Double(ingredient.getQuantity()).toString());
            infoToBeDisplayed.append(" ");

            String measure = ValuesUtil.meassureMap.get(ingredient.getMeasure());
            infoToBeDisplayed.append(measure);
            if(measure.length() > 0) {
                infoToBeDisplayed.append(" ");
            }

            infoToBeDisplayed.append(ingredient.getIngredient());

            views.setTextViewText(R.id.widgetItemTaskNameLabel, infoToBeDisplayed);

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
