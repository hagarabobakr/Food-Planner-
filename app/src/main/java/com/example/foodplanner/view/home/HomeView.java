package com.example.foodplanner.view.home;

import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;

import java.util.List;

public interface HomeView {

    void showRandomMeals(List<MealsItem> meals);
    void showPopularMeals(List<MealsItem> meals);
    void showCategories(List<CategoriesItem> categories);
    void showError(String message);

    void showMealDetails(MealsItem mealsItem);

    void showChips(List<String> areas);

    void showMealsByCategory(List<MealsItem> meals);
    void showMealsByCountry(List<MealsItem> meals);
}
