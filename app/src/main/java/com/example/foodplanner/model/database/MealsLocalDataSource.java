package com.example.foodplanner.model.database;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class MealsLocalDataSource {
    AppDatabase db;
    private final MealDao mealDao;
    Context context;

    public MealsLocalDataSource(Context _context) {
        context =_context;
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "meals_database").build();
        mealDao = db.getMealDao();
    }

    public LiveData<List<MealItemEntity>> getAllMeals() {
        return mealDao.getAllMeals();
    }

    public void insertMeal(MealItemEntity meal) {
        new Thread(() -> {
                mealDao.insertMeal(meal);
        }).start();
    }

    public void deleteMeal(MealItemEntity meal) {
        new Thread(() -> {
                mealDao.deleteMeal(meal);
        }).start();
    }


}
