package com.example.foodplanner.model.database.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealPlanDao {
    @Query("SELECT * FROM meal_plan")
    LiveData<List<MealPlan>> getAllPlanMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlanMeal(MealPlan mealPlan);

    @Delete
    void deletePlanMeal(MealPlan mealPlan);

    @Query("SELECT * FROM meal_plan WHERE day_number = :day AND month = :month AND year = :year")
    LiveData<List<MealPlan>> getMealsByDate(int day, int month, int year);
}
