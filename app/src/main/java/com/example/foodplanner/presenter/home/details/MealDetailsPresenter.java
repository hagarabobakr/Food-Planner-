package com.example.foodplanner.presenter.home.details;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealsLocalDataSourceImpl;
import com.example.foodplanner.model.network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.model.repo.MealsRepositoryImpl;
import com.example.foodplanner.view.home.details.MealDetailsView;

public class MealDetailsPresenter {
    private MealDetailsView view;
   // private MealsLocalDataSourceImpl mealsLocalDataSourceImpl;
    MealsRepositoryImpl repository;
    public MealDetailsPresenter(MealDetailsView view, MealsRemoteDataSourceImpl mealsRemoteDataSourceImpl, MealsLocalDataSourceImpl mealsLocalDataSource) {
        this.view = view;
        repository = MealsRepositoryImpl.getInstance(mealsLocalDataSource,mealsRemoteDataSourceImpl);
    }

    public void addMealToFavorites(MealsItem meal) {
        // Directly insert MealsItem into the database
        //mealsLocalDataSourceImpl.insertMeal(meal);
        repository.insertMeal(meal);
    }
}
