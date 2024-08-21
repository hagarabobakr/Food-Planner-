package com.example.foodplanner.view.home.fragments.fav;

import com.example.foodplanner.model.database.MealItemEntity;

public interface OnFavRecipeClickListner {
    void onFavRecipeClickListner(MealItemEntity mealItem);

    void onDeletIcClickListner(MealItemEntity mealItem);
}
