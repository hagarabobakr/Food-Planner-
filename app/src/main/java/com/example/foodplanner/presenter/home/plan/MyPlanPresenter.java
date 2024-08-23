package com.example.foodplanner.presenter.home.plan;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealsLocalDataSource;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.view.home.fragments.fav.FavView;
import com.example.foodplanner.view.home.fragments.plan.MyPlanView;

import java.util.List;

public class MyPlanPresenter {
    private MyPlanView myPlanView;
    private MealsLocalDataSource mealsLocalDataSource;

    public MyPlanPresenter(MyPlanView myPlanView, MealsLocalDataSource mealsLocalDataSource) {
        this.myPlanView = myPlanView;
        this.mealsLocalDataSource = mealsLocalDataSource;
    }

    public void getLocalPlanedMeals(){

        myPlanView.showLocalData( mealsLocalDataSource.getAllPlanedMeals());
    }
    public  void removePlanedMeal(MealPlan mealPlan){
        mealsLocalDataSource.deletePlanMeal(mealPlan);
    }
    public void getMealsByDate(int day, int month, int year){
        LiveData<List<MealPlan>> mealsByDate = mealsLocalDataSource.getMealsByDate(day, month, year);
        myPlanView.showMealsByDate(mealsByDate);
    }
}
