package com.example.foodplanner.model.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.model.database.data.MealPlanDao;

import java.util.List;

public class MealsLocalDataSourceImpl {
    private static final String TAG = "MealsLocalDataSource";
    AppDatabase db;
    private final MealDao mealDao;
    private MealPlanDao mealPlanDao;
    Context context;
    private static MealsLocalDataSourceImpl mealsLocalDataSourceImpl = null;
    private LiveData<List<MealsItem>> mealItemList;
    private LiveData<List<MealPlan>> mealPlanList;
    public MealsLocalDataSourceImpl(Context _context) {
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
    public static MealsLocalDataSourceImpl getInstance(Context context){
        if(mealsLocalDataSourceImpl == null){
            mealsLocalDataSourceImpl = new MealsLocalDataSourceImpl(context.getApplicationContext());
        }
        return mealsLocalDataSourceImpl;
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
