package com.example.foodplanner.presenter.home.plan;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.database.MealsLocalDataSourceImpl;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.model.network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.model.repo.MealsRepositoryImpl;
import com.example.foodplanner.view.home.fragments.plan.MyPlanView;

import java.util.List;

public class MyPlanPresenter {
    private MyPlanView myPlanView;
   // private MealsLocalDataSourceImpl mealsLocalDataSourceImpl;
    private MealsRepositoryImpl repository;

    public MyPlanPresenter(MyPlanView myPlanView, MealsRemoteDataSourceImpl mealsRemoteDataSourceImpl, MealsLocalDataSourceImpl mealsLocalDataSource) {
        this.myPlanView = myPlanView;
        repository = MealsRepositoryImpl.getInstance(mealsLocalDataSource,mealsRemoteDataSourceImpl);
    }

    public void getLocalPlanedMeals(){

        myPlanView.showLocalData(repository.getAllPlanedMeals() );
    }
    public  void removePlanedMeal(MealPlan mealPlan){
       // mealsLocalDataSourceImpl.deletePlanMeal(mealPlan);
        repository.deletePlanMeal(mealPlan);
    }
    public void getMealsByDate(int day, int month, int year){
        LiveData<List<MealPlan>> mealsByDate = repository.getMealsByDate(day, month, year) ;
        myPlanView.showMealsByDate(mealsByDate);
    }
}
