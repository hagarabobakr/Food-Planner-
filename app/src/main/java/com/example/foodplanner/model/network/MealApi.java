package com.example.foodplanner.model.network;

import com.example.foodplanner.model.data.CategoryResponse;
import com.example.foodplanner.model.data.CountryResponse;
import com.example.foodplanner.model.data.IngredientResponse;
import com.example.foodplanner.model.data.MealDetailResponse;
import com.example.foodplanner.model.data.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApi {
    // Fetches a random meal recipe
    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    // Retrieves a list of meal categories
    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    // Retrieves meals based on the specified category
    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);

    // Fetches detailed information about a meal by its ID
    @GET("lookup.php")
    Call<MealDetailResponse> getMealDetails(@Query("i") String mealId);

    // Searches for meals by their name
    @GET("search.php")
    Call<MealResponse> searchMealsByName(@Query("s") String mealName);

    // Searches for meals containing a specific ingredient
    @GET("filter.php")
    Call<MealResponse> getMealsByIngredient(@Query("i") String ingredient);

    // Searches for meals based on their country of origin
    @GET("filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String area);

    // Retrieves a list of all available countries
    @GET("list.php?a=list")
    Call<CountryResponse> getCountries();

    // Retrieves a list of all available ingredients
    @GET("list.php?i=list")
    Call<IngredientResponse> getIngredients();

    // Retrieves meals for a specific day (additional feature - day specified as Query Parameter)
    @GET("search.php?f=a")
    Call<MealResponse> getMealsByFirstLitter(@Query("d") String day);
}
