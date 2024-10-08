package com.example.foodplanner.presenter.home.getmeal;

import com.example.foodplanner.model.data.MealsItem;

import java.util.List;

public interface GetMealDetailsNetworkCallBack {
    public void onMealDetailsSuccessResult(List<MealsItem> meals);
    public void onMealDetailsFailureResult(String message);
}
