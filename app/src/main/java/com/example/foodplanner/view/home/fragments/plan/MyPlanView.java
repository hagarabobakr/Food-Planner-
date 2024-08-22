package com.example.foodplanner.view.home.fragments.plan;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.database.data.MealPlan;

import java.util.List;

public interface MyPlanView {
    void showLocalData(LiveData<List<MealPlan>> allPlanedMeals);
}
