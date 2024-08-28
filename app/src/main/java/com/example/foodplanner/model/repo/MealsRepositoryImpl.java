package com.example.foodplanner.model.repo;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealsLocalDataSourceImpl;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.model.network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.presenter.home.HomeNetworkCallBack;
import com.example.foodplanner.presenter.home.getmeal.GetMealDetailsNetworkCallBack;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository {
    private static MealsRepositoryImpl repository = null;
    private MealsLocalDataSourceImpl localDataSource;
    private MealsRemoteDataSourceImpl remoteDataSource;
    private final String TAG = "MealsRepository";

    private MealsRepositoryImpl(MealsLocalDataSourceImpl localDataSource, MealsRemoteDataSourceImpl remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public MealsRepositoryImpl(MealsLocalDataSourceImpl localDataSource) {
        this.localDataSource = localDataSource;
    }

    public MealsRepositoryImpl(MealsRemoteDataSourceImpl remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static synchronized MealsRepositoryImpl getInstance(MealsLocalDataSourceImpl localDataSource, MealsRemoteDataSourceImpl remoteDataSource) {
        if (repository == null) {
            repository = new MealsRepositoryImpl(localDataSource, remoteDataSource);
        }
        return repository;
    }

    @Override
    public void getRandomMeal(HomeNetworkCallBack callback) {
        remoteDataSource.makeGetRandomMealNetworkCall(callback);
    }

    @Override
    public void getCategoryItems(HomeNetworkCallBack callback) {
        remoteDataSource.makeGetCatigoryItemsNetworkCall(callback);
    }

    @Override
    public void getMealDetails(HomeNetworkCallBack callback, String id) {
        remoteDataSource.makeGetHomeMealDetailsNetworkCall(callback, id);
    }

    @Override
    public void getMealDetailsWithCallback(GetMealDetailsNetworkCallBack callback, String id) {
        remoteDataSource.makeGetMealDetailsNetworkCall(callback, id);
    }

    @Override
    public void getCountry(HomeNetworkCallBack callback) {
        remoteDataSource.makeGetCountryNetworkCall(callback);
    }

    @Override
    public void getMealsByCategory(HomeNetworkCallBack callback, String category) {
        remoteDataSource.makeGetMealsByCategoryNetworkCall(callback, category);
    }

    @Override
    public void getMealsByCountry(HomeNetworkCallBack callback, String country) {
        remoteDataSource.makeGetMealsByCountryNetworkCall(callback, country);
    }

    @Override
    public void getMealsByFirstLetter(HomeNetworkCallBack callback, String letter) {
        remoteDataSource.makeGetMealsByFirstLitterNetworkCall(callback, letter);
    }

    @Override
    public void getMealsByIngredient(HomeNetworkCallBack callback, String ingredient) {
        remoteDataSource.makeGetMealsByIngredientNetworkCall(callback, ingredient);
    }

    @Override
    public LiveData<List<MealsItem>> getAllMeals() {
        return localDataSource.getAllMeals();
    }

    @Override
    public void insertMeal(MealsItem meal) {
        localDataSource.insertMeal(meal);
    }

    @Override
    public void deleteMeal(MealsItem meal) {
        localDataSource.deleteMeal(meal);
    }

    @Override
    public LiveData<List<MealPlan>> getAllPlanedMeals() {
        return localDataSource.getAllPlanedMeals();
    }

    @Override
    public void insertPlanMeal(MealPlan meal) {
        localDataSource.insertPlanMeal(meal);
    }

    @Override
    public void deletePlanMeal(MealPlan meal) {
        localDataSource.deletePlanMeal(meal);
    }

    @Override
    public LiveData<List<MealPlan>> getMealsByDate(int day, int month, int year) {
        return localDataSource.getMealsByDate(day, month, year);
    }
}
