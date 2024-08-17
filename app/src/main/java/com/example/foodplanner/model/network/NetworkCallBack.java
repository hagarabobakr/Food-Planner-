package com.example.foodplanner.model.network;

import com.example.foodplanner.model.data.MealsItem;

import java.util.List;

public interface NetworkCallBack{
    public void onRandumMealSuccessResult(List<MealsItem> meals);
    public void onRandumMealFailureResult(String errorMsg);
}
