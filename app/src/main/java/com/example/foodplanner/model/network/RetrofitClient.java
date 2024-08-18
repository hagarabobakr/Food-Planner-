package com.example.foodplanner.model.network;

import android.util.Log;

import com.example.foodplanner.model.data.CategoryResponse;
import com.example.foodplanner.model.data.MealResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
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
                    Log.i(TAG ,"onResponse MealResponse: CallBack "+response.body().meals.size());
                    networkCallback.onRandumMealSuccessResult(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailureeee MealResponse: CallBack ");
                networkCallback.onRandumMealFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void makeGetCatigoryItemsNetworkCall (NetworkCallBack networkCallback){
        Call<CategoryResponse> call = mealApi.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse CategoryResponse: CallBack "+response.body().getCategories().size());
                    networkCallback.onCatigoryItemsSuccessResult(response.body().categories);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailureeee CategoryResponse: CallBack ");
                networkCallback.onCatigoryItemsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }


}
