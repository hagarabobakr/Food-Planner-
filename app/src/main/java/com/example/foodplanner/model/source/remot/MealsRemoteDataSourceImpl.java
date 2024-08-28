package com.example.foodplanner.model.source.remot;

import com.example.foodplanner.model.data.CategoryResponse;
import com.example.foodplanner.model.data.CountryResponse;
import com.example.foodplanner.model.data.MealDetailResponse;
import com.example.foodplanner.model.data.MealResponse;
import com.example.foodplanner.model.network.MealApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImpl implements MealsRemoteDataSource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private MealApi mealApi;
    public MealsRemoteDataSourceImpl() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mealApi = retrofit.create(MealApi.class);
    }
    @Override
    public Call<MealResponse> fetchRandomMeal() {
        return null;
    }

    @Override
    public Call<CategoryResponse> fetchCategories() {
        return null;
    }

    @Override
    public Call<MealDetailResponse> fetchMealDetails(String id) {
        return null;
    }

    @Override
    public Call<CountryResponse> fetchCountries() {
        return null;
    }

    @Override
    public Call<MealResponse> fetchMealsByCategory(String category) {
        return null;
    }

    @Override
    public Call<MealResponse> fetchMealsByCountry(String country) {
        return null;
    }

    @Override
    public Call<MealResponse> fetchMealsByFirstLitter(String litter) {
        return null;
    }

    @Override
    public Call<MealResponse> fetchMealsByIngredient(String ingredient) {
        return null;
    }
}
