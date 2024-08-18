package com.example.foodplanner.model.network;

import android.util.Log;

import com.example.foodplanner.model.data.CategoryResponse;
import com.example.foodplanner.model.data.CountryResponse;
import com.example.foodplanner.model.data.MealDetailResponse;
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
    public void makeGetMealDetailsNetworkCall(NetworkCallBack networkCallBack,String id){
        Call<MealDetailResponse> call = mealApi.getMealDetails(id);
        call.enqueue(new Callback<MealDetailResponse>() {
            @Override
            public void onResponse(Call<MealDetailResponse> call, Response<MealDetailResponse> response) {
                if(response.isSuccessful()&& response.body() != null){
                    Log.i(TAG ,"onResponse MealDetailResponse: CallBack "+response.body().getMeals());
                   networkCallBack.onMealDetailsSuccessResult(response.body().getMeals());
                }else {
                    networkCallBack.onMealDetailsFailureResult("No details found");
                }
            }

            @Override
            public void onFailure(Call<MealDetailResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailureeee MealDetailResponse: CallBack ");
                networkCallBack.onMealDetailsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeGetCountryNetworkCall (NetworkCallBack networkCallback){
        Call<CountryResponse> call = mealApi.getCountries();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse CountryResponse: CallBack "+response.body().getMeals());
                    networkCallback.onGetCountrySuccessResult(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailure CountryResponse: CallBack ");
                networkCallback.onMealDetailsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}


