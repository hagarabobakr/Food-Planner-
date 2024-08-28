package com.example.foodplanner.presenter.home.getmeal;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealsLocalDataSourceImpl;
import com.example.foodplanner.model.network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.model.repo.MealsRepositoryImpl;
import com.example.foodplanner.view.home.category.GetMealFromAnyWhereView;

import java.util.List;

public class GetMealsFromAnyWherePresenter implements GetMealDetailsNetworkCallBack {
    private GetMealFromAnyWhereView view;
    //private MealsRemoteDataSourceImpl mealsRemoteDataSourceImpl;
    private MealsRepositoryImpl repository;


    public GetMealsFromAnyWherePresenter(GetMealFromAnyWhereView view, MealsRemoteDataSourceImpl mealsRemoteDataSourceImpl, MealsLocalDataSourceImpl mealsLocalDataSource) {
        this.view = view;
        repository = MealsRepositoryImpl.getInstance(mealsLocalDataSource,mealsRemoteDataSourceImpl);
    }
    public void getMealDetails(String id){
       // mealsRemoteDataSourceImpl.makeGetMealDetailsNetworkCall(this,id);
        repository.getMealDetailsWithCallback(this,id);
    }

    @Override
    public void onMealDetailsSuccessResult(List<MealsItem> meals) {
        if (meals != null && !meals.isEmpty()) {
            view.showMealDetails(meals.get(0));
        } else {
            view.showError("No meal details found");
        }
    }

    @Override
    public void onMealDetailsFailureResult(String message) {

    }
}
