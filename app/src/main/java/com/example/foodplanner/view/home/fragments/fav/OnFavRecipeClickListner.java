package com.example.foodplanner.view.home.fragments.fav;

import com.example.foodplanner.model.data.MealsItem;

public interface OnFavRecipeClickListner {
    void onFavRecipeClickListner(MealsItem mealItem);

    void onDeletIcClickListner(MealsItem mealItem);

    void onAddToPlanClickListner(MealsItem mealItem);
}
