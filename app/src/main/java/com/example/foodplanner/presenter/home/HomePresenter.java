package com.example.foodplanner.presenter.home;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.NetworkCallBack;
import com.example.foodplanner.model.network.home.MealRepository;
import com.example.foodplanner.view.home.HomeView;

import java.util.List;

public class HomePresenter implements NetworkCallBack {
    private HomeView view;
    private MealRepository mealRepository;

    public HomePresenter(HomeView view, MealRepository _mealRepository) {
        this.view = view;
        this.mealRepository = _mealRepository;
    }


    @Override
    public void onRandumMealSuccessResult(List<MealsItem> meals) {
        view.showRandomMeals(meals);
    }

    @Override
    public void onRandumMealFailureResult(String errorMsg) {
        view.showError(errorMsg);
    }
}
