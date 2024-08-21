package com.example.foodplanner.presenter.home;

import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.RetrofitClient;
import com.example.foodplanner.view.home.HomeView;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeNetworkCallBack {
    private HomeView view;
    private RetrofitClient retrofitClient;


    public HomePresenter(HomeView view, RetrofitClient _retrofitClient) {
        this.view = view;
        retrofitClient = _retrofitClient;
    }

    public void getRandumMeal() {
        retrofitClient.makeGetRandomMealNetworkCall(this);
    }

    public void getCatigoryIteams() {
        retrofitClient.makeGetCatigoryItemsNetworkCall(this);
    }

    public void getMealDetails(String id) {
        retrofitClient.makeGetHomeMealDetailsNetworkCall(this, id);
    }

    public void getCountries() {
        retrofitClient.makeGetCountryNetworkCall(this);
    }

    public void getMealsByCategorie(String category) {
        retrofitClient.makeGetMealsByCategoryNetworkCall(this, category);
    }
    public void getMealsByFirstLitter(String litter) {
        retrofitClient.makeGetMealsByFirstLitterNetworkCall(this, litter);
    }

    public void getMealsByCountry(String country) {
        retrofitClient.makeGetMealsByCountryNetworkCall(this, country);
    }
    public void getMealsByIngredient(String ingredient) {
        retrofitClient.makeGetMealsByIngredientNetworkCall(this, ingredient);
    }


    @Override
    public void onRandumMealSuccessResult(List<MealsItem> meals) {
        view.showRandomMeals(meals);
    }

    @Override
    public void onPopulerMealSuccessResult(List<MealsItem> meals) {
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
        view.showError(message);
    }

    @Override
    public void onGetCountrySuccessResult(List<MealsItem> meals) {
        List<String> areas = new ArrayList<>();
        for (MealsItem meal : meals) {
            areas.add(meal.getStrArea());
        }
        view.showChips(areas);
    }

    @Override
    public void onGetMealsByCategorieSuccessResult(List<MealsItem> meals) {
        view.showMealsByCategory(meals);
    }

    @Override
    public void onGetMealsByGetMealsByCountrySuccessResult(List<MealsItem> meals) {
        view.showMealsByCountry(meals);
    }

    @Override
    public void onGetMealsByFirstLitterSuccessResult(List<MealsItem> meals) {
        view.showPopularMeals(meals);
    }

    @Override
    public void onGetMealsByIngredientSuccessResult(List<MealsItem> meals) {
        view.showMealsByIngredient(meals);
    }
}
