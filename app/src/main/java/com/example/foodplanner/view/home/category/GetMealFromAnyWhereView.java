package com.example.foodplanner.view.home.category;

import com.example.foodplanner.model.data.MealsItem;

public interface GetMealFromAnyWhereView {
    void showMealDetails(MealsItem mealsItem);

    void showError(String noMealDetailsFound);
}
