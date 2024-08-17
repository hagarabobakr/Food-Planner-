package com.example.foodplanner.model.network;

import android.util.Log;

import com.example.foodplanner.model.data.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String TAG = "RetrofitClient";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static RetrofitClient retrofitClient = null;
    private MealApi mealApi;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealApi = retrofit.create(MealApi.class);
    }

    public static RetrofitClient getRetrofitInstance() {
        if(retrofitClient == null){
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public void makeGetRandomMealNetworkCall (NetworkCallBack networkCallback){
        Call<MealResponse> call = mealApi.getRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack "+response.raw()+response.body() );
                    networkCallback.onRandumMealSuccessResult(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack ");
                networkCallback.onRandumMealFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }


}
