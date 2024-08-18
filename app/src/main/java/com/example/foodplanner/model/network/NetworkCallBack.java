package com.example.foodplanner.model.network;

import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;

import java.util.List;

public interface NetworkCallBack{
    public void onRandumMealSuccessResult(List<MealsItem> meals);
    public void onRandumMealFailureResult(String errorMsg);
    public void onCatigoryItemsSuccessResult(List<CategoriesItem> categoriesItems);
    public void onCatigoryItemsFailureResult(String errorMsg);
    public void onMealDetailsSuccessResult(List<MealsItem> meals);
    public void onMealDetailsFailureResult(String message);

    public void onGetCountrySuccessResult(List<MealsItem> meals);
}
