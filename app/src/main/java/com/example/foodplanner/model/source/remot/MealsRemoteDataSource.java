package com.example.foodplanner.model.source.remot;

import com.example.foodplanner.model.data.CategoryResponse;
import com.example.foodplanner.model.data.CountryResponse;
import com.example.foodplanner.model.data.MealDetailResponse;
import com.example.foodplanner.model.data.MealResponse;

import retrofit2.Call;

public interface MealsRemoteDataSource {
    Call<MealResponse> fetchRandomMeal();
    Call<CategoryResponse> fetchCategories();
    Call<MealDetailResponse> fetchMealDetails(String id);
    Call<CountryResponse> fetchCountries();
    Call<MealResponse> fetchMealsByCategory(String category);
    Call<MealResponse> fetchMealsByCountry(String country);
    Call<MealResponse> fetchMealsByFirstLitter(String litter);
    Call<MealResponse> fetchMealsByIngredient(String ingredient);
}
