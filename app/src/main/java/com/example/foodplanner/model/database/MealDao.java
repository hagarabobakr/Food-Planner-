package com.example.foodplanner.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealDao {

    @Query("SELECT * FROM meal_table")
    LiveData<List<MealItemEntity>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(MealItemEntity meal);

    @Delete
    void deleteMeal(MealItemEntity meal);
}
