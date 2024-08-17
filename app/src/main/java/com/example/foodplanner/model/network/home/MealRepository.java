package com.example.foodplanner.model.network.home;

import com.example.foodplanner.model.data.MealResponse;
import com.example.foodplanner.model.network.MealApi;
import com.example.foodplanner.model.network.NetworkCallBack;
import com.example.foodplanner.model.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRepository {
    /*private MealApi mealApi;

    public MealRepository() {
        mealApi = RetrofitClient.getRetrofitInstance().create(MealApi.class);
    }
    public void getRandomMeals( NetworkCallBack<MealResponse> callBack) {
        mealApi.getRandomMeal().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onRandumMealSuccessResult(response.body());
                } else {
                    callBack.onFailureResult("Failed to load random meals");
                }
            }

            @Override public void onFailure(Call<MealResponse> call, Throwable t) {
                callBack.onFailureResult(t.getMessage());
            }
        });
    }*/
}

