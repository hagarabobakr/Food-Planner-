package com.example.foodplanner.presenter.home;

import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.NetworkCallBack;
import com.example.foodplanner.model.network.RetrofitClient;
import com.example.foodplanner.view.home.HomeView;

import java.util.List;

public class HomePresenter implements NetworkCallBack {
    private HomeView view;
    private RetrofitClient retrofitClient;


    public HomePresenter(HomeView view, RetrofitClient _retrofitClient) {
        this.view = view;
        retrofitClient = _retrofitClient;
    }

    public void getRandumMeal(){
        retrofitClient.makeGetRandomMealNetworkCall(this);
    }
    public void getCatigoryIteams(){
        retrofitClient.makeGetCatigoryItemsNetworkCall(this);
    }


    @Override
    public void onRandumMealSuccessResult(List<MealsItem> meals) {
        view.showRandomMeals(meals);
        view.showPopularMeals(meals);
    }

    @Override
    public void onRandumMealFailureResult(String errorMsg) {

        view.showError(errorMsg);
    }

    @Override
    public void onCatigoryItemsSuccessResult(List<CategoriesItem> categoriesItems) {
        view.showCategories(categoriesItems);
    }

    @Override
    public void onCatigoryItemsFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }
}
