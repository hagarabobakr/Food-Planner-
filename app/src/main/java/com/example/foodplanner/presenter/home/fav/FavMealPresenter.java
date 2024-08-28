package com.example.foodplanner.presenter.home.fav;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealsLocalDataSourceImpl;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.model.network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.model.repo.MealsRepositoryImpl;
import com.example.foodplanner.view.home.fragments.fav.FavView;

public class FavMealPresenter {
private FavView favView;
//private MealsLocalDataSourceImpl mealsLocalDataSourceImpl;
private MealsRepositoryImpl repository;

    public FavMealPresenter(FavView favView, MealsRemoteDataSourceImpl mealsRemoteDataSourceImpl, MealsLocalDataSourceImpl mealsLocalDataSource) {
        this.favView = favView;
        repository = MealsRepositoryImpl.getInstance(mealsLocalDataSource,mealsRemoteDataSourceImpl);
    }
    public void getLocalMeals(){

        favView.showLocalData( repository.getAllMeals());
    }
    public  void removeMeal(MealsItem mealItemEntity){
       // mealsLocalDataSourceImpl.deleteMeal(mealItemEntity);
        repository.deleteMeal(mealItemEntity);
    }
    public void insertPlanedMeal(MealPlan meal){

        //mealsLocalDataSourceImpl.insertPlanMeal(meal);
        repository.insertPlanMeal(meal);
    }
}
