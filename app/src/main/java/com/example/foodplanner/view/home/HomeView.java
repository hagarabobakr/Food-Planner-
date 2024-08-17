package com.example.foodplanner.view.home;

import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;

import java.util.List;

public interface HomeView {

    void showRandomMeals(List<MealsItem> meals);
    void showPopularMeals(List<MealsItem> meals);
    void showCategories(List<CategoriesItem> categories);
    void showError(String message);
}
