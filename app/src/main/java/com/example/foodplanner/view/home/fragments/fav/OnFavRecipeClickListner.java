package com.example.foodplanner.view.home.fragments.fav;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealItemEntity;

public interface OnFavRecipeClickListner {
    void onFavRecipeClickListner(MealsItem mealItem);

    void onDeletIcClickListner(MealsItem mealItem);
}
