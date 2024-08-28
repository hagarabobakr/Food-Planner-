package com.example.foodplanner.presenter.home;

import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;

import java.util.List;

public interface HomeNetworkCallBack {
    public void onRandumMealSuccessResult(List<MealsItem> meals);
    public void onPopulerMealSuccessResult(List<MealsItem> meals);
     public void onCatigoryItemsSuccessResult(List<CategoriesItem> categoriesItems);
    public void onMealDetailsSuccessResult(List<MealsItem> meals);

    public void onGetCountrySuccessResult(List<MealsItem> meals);

    public void onGetMealsByCategorieSuccessResult(List<MealsItem> meals);

    public void onGetMealsByGetMealsByCountrySuccessResult(List<MealsItem> meals);

    public void onGetMealsByFirstLitterSuccessResult(List<MealsItem> meals);

    public void onGetMealsByIngredientSuccessResult(List<MealsItem> meals);

    public void onMealFailureResult(String message);
}
