package com.example.foodplanner.model.source;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.AppDatabase;
import com.example.foodplanner.model.database.MealDao;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.model.database.data.MealPlanDao;

import java.util.List;

public class MealsLocalDataSource {
    private static final String TAG = "MealsLocalDataSource";
    AppDatabase db;
    private final MealDao mealDao;
    private MealPlanDao mealPlanDao;
    Context context;
    private static MealsLocalDataSource mealsLocalDataSource = null;
    private LiveData<List<MealsItem>> mealItemList;
    private LiveData<List<MealPlan>> mealPlanList;
    public MealsLocalDataSource(Context _context) {
        context =_context;
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "meals_database").build();
        //meal
        mealDao = db.getMealDao();
        mealItemList = mealDao.getAllMeals();
        //plan
        mealPlanDao = db.mealPlanDao();
        mealPlanList = mealPlanDao.getAllPlanMeals();
    }
    public static MealsLocalDataSource getInstance(Context context){
        if(mealsLocalDataSource == null){
            mealsLocalDataSource = new MealsLocalDataSource(context.getApplicationContext());
        }
        return mealsLocalDataSource;
    }

    public LiveData<List<MealsItem>> getAllMeals() {
        return mealItemList;

    }

    public void insertMeal(MealsItem meal) {
        new Thread(() -> {
                mealDao.insertMeal(meal);
        }).start();
    }

    public void deleteMeal(MealsItem meal) {
        new Thread(() -> {
                mealDao.deleteMeal(meal);
        }).start();
    }

    //PLAN
    public LiveData<List<MealPlan>> getAllPlanedMeals() {
        return mealPlanList;
    }
    public void insertPlanMeal(MealPlan meal) {
        Log.i(TAG, "insertPlanMeal: ");
        new Thread(() -> {
            mealPlanDao.insertPlanMeal(meal);
        }).start();
    }
    public void deletePlanMeal(MealPlan meal) {
        new Thread(() -> {
            mealPlanDao.deletePlanMeal(meal);
        }).start();
    }
    public LiveData<List<MealPlan>> getMealsByDate(int day, int month, int year) {
        return mealPlanDao.getMealsByDate(day, month, year);
    }

}
