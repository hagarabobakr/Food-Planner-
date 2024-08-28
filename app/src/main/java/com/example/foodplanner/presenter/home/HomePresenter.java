package com.example.foodplanner.presenter.home;

import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealsLocalDataSourceImpl;
import com.example.foodplanner.model.network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.model.repo.MealsRepositoryImpl;
import com.example.foodplanner.view.home.HomeView;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeNetworkCallBack {
    private HomeView view;
   // private MealsRemoteDataSourceImpl mealsRemoteDataSourceImpl;
    private MealsRepositoryImpl repository;


    public HomePresenter(HomeView view, MealsRemoteDataSourceImpl mealsRemoteDataSourceImpl, MealsLocalDataSourceImpl mealsLocalDataSource) {
        this.view = view;
        repository = MealsRepositoryImpl.getInstance(mealsLocalDataSource,mealsRemoteDataSourceImpl);
    }

    public void getRandumMeal() {
       // mealsRemoteDataSourceImpl.makeGetRandomMealNetworkCall(this);
        repository.getRandomMeal(this);
    }

    public void getCatigoryIteams() {
      //  mealsRemoteDataSourceImpl.makeGetCatigoryItemsNetworkCall(this);
        repository.getCategoryItems(this);
    }

    public void getMealDetails(String id) {
       // mealsRemoteDataSourceImpl.makeGetHomeMealDetailsNetworkCall(this, id);
        repository.getMealDetails(this,id);
    }

    public void getCountries() {

        //mealsRemoteDataSourceImpl.makeGetCountryNetworkCall(this);
        repository.getCountry(this);
    }

    public void getMealsByCategorie(String category) {
        //mealsRemoteDataSourceImpl.makeGetMealsByCategoryNetworkCall(this, category);
        repository.getMealsByCategory(this,category);
    }
    public void getMealsByFirstLitter(String litter) {
        //mealsRemoteDataSourceImpl.makeGetMealsByFirstLitterNetworkCall(this, litter);
        repository.getMealsByFirstLetter(this,litter);
    }

    public void getMealsByCountry(String country) {
        //mealsRemoteDataSourceImpl.makeGetMealsByCountryNetworkCall(this, country);
        repository.getMealsByCountry(this,country);
    }
    public void getMealsByIngredient(String ingredient) {
        //mealsRemoteDataSourceImpl.makeGetMealsByIngredientNetworkCall(this, ingredient);
        repository.getMealsByIngredient(this,ingredient);
    }


    @Override
    public void onRandumMealSuccessResult(List<MealsItem> meals) {
        view.showRandomMeals(meals);
    }

    @Override
    public void onPopulerMealSuccessResult(List<MealsItem> meals) {
    }



    @Override
    public void onCatigoryItemsSuccessResult(List<CategoriesItem> categoriesItems) {
        view.showCategories(categoriesItems);
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

    @Override
    public void onMealFailureResult(String message) {
        view.showError(message);
    }
}
