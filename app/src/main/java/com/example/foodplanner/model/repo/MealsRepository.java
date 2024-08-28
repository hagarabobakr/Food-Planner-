package com.example.foodplanner.model.repo;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.presenter.home.HomeNetworkCallBack;
import com.example.foodplanner.presenter.home.getmeal.GetMealDetailsNetworkCallBack;

import java.util.List;

public interface MealsRepository {
    // Random Meal
    void getRandomMeal(HomeNetworkCallBack callback);

    // Category Items
    void getCategoryItems(HomeNetworkCallBack callback);

    // Meal Details (HomeNetworkCallback)
    void getMealDetails(HomeNetworkCallBack callback, String id);

    // Meal Details (GetMealDetailsNetworkCallBack)
    void getMealDetailsWithCallback(GetMealDetailsNetworkCallBack callback, String id);

    // Country
    void getCountry(HomeNetworkCallBack callback);

    // Meals By Category
    void getMealsByCategory(HomeNetworkCallBack callback, String category);

    // Meals By Country
    void getMealsByCountry(HomeNetworkCallBack callback, String country);

    // Meals By First Letter
    void getMealsByFirstLetter(HomeNetworkCallBack callback, String letter);

    // Meals By Ingredient
    void getMealsByIngredient(HomeNetworkCallBack callback, String ingredient);



    //LOCAL DATA SOURCE

    // Local Meals
    LiveData<List<MealsItem>> getAllMeals();
    void insertMeal(MealsItem meal);
    void deleteMeal(MealsItem meal);

    // Plan Meals
    LiveData<List<MealPlan>> getAllPlanedMeals();
    void insertPlanMeal(MealPlan meal);
    void deletePlanMeal(MealPlan meal);
    LiveData<List<MealPlan>> getMealsByDate(int day, int month, int year);
}
