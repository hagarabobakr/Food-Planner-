package com.example.foodplanner.presenter.home.details;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealItemEntity;
import com.example.foodplanner.model.database.MealsLocalDataSource;
import com.example.foodplanner.view.home.details.MealDetailsView;

public class MealDetailsPresenter {
    private MealDetailsView view;
    private MealsLocalDataSource mealsLocalDataSource;
    public MealDetailsPresenter(MealDetailsView view, MealsLocalDataSource mealDao) {
        this.view = view;
        this.mealsLocalDataSource = mealDao;
    }

    public void addMealToFavorites(MealsItem meal) {
        // Directly insert MealsItem into the database
        mealsLocalDataSource.insertMeal(meal);
    }
}
