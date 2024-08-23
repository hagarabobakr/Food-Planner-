package com.example.foodplanner.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.data.MealsItem;

import java.util.List;

@Dao
public interface MealDao {

    @Query("SELECT * FROM meals")
    LiveData<List<MealsItem>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(MealsItem meal);

    @Delete
    void deleteMeal(MealsItem meal);

    @Query("SELECT * FROM meals")
    List<MealsItem> getAllMealsList();
}
