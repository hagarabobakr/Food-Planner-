package com.example.foodplanner.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.model.database.data.MealPlanDao;

@Database(entities = {MealsItem.class, MealPlan.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance = null;

    public abstract MealDao getMealDao();
    public abstract MealPlanDao mealPlanDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "meals_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
