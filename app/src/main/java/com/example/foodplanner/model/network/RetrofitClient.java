package com.example.foodplanner.model.network;

import android.util.Log;

import com.example.foodplanner.model.data.CategoryResponse;
import com.example.foodplanner.model.data.CountryResponse;
import com.example.foodplanner.model.data.MealDetailResponse;
import com.example.foodplanner.model.data.MealResponse;
import com.example.foodplanner.presenter.home.details.GetMealDetailsNetworkCallBack;

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

    public void makeGetRandomMealNetworkCall (HomeNetworkCallBack homeNetworkCallback){
        Call<MealResponse> call = mealApi.getRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse MealResponse: CallBack "+response.body().meals.size());
                    homeNetworkCallback.onRandumMealSuccessResult(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailureeee MealResponse: CallBack ");
                homeNetworkCallback.onRandumMealFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void makeGetCatigoryItemsNetworkCall (HomeNetworkCallBack homeNetworkCallback){
        Call<CategoryResponse> call = mealApi.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse CategoryResponse: CallBack "+response.body().getCategories().size());
                    homeNetworkCallback.onCatigoryItemsSuccessResult(response.body().categories);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailureeee CategoryResponse: CallBack ");
                homeNetworkCallback.onCatigoryItemsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void makeGetHomeMealDetailsNetworkCall(HomeNetworkCallBack homeNetworkCallBack, String id){
        Call<MealDetailResponse> call = mealApi.getMealDetails(id);
        call.enqueue(new Callback<MealDetailResponse>() {
            @Override
            public void onResponse(Call<MealDetailResponse> call, Response<MealDetailResponse> response) {
                if(response.isSuccessful()&& response.body() != null){
                    Log.i(TAG ,"onResponse MealDetailResponse: CallBack "+response.body().getMeals());
                   homeNetworkCallBack.onMealDetailsSuccessResult(response.body().getMeals());
                }else {
                    homeNetworkCallBack.onMealDetailsFailureResult("No details found");
                }
            }

            @Override
            public void onFailure(Call<MealDetailResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailureeee MealDetailResponse: CallBack ");
                homeNetworkCallBack.onMealDetailsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    //another activity
    public void makeGetMealDetailsNetworkCall(GetMealDetailsNetworkCallBack getMealDetailsNetworkCallBack, String id){
        Call<MealDetailResponse> call = mealApi.getMealDetails(id);
        call.enqueue(new Callback<MealDetailResponse>() {
            @Override
            public void onResponse(Call<MealDetailResponse> call, Response<MealDetailResponse> response) {
                if(response.isSuccessful()&& response.body() != null){
                    Log.i(TAG ,"onResponse makeGetMealDetailsNetworkCall: CallBack "+response.body().getMeals());
                    getMealDetailsNetworkCallBack.onMealDetailsSuccessResult(response.body().getMeals());
                }else {
                    getMealDetailsNetworkCallBack.onMealDetailsFailureResult("No details found");
                }
            }

            @Override
            public void onFailure(Call<MealDetailResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailureeee makeGetMealDetailsNetworkCall: CallBack ");
                getMealDetailsNetworkCallBack.onMealDetailsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void makeGetCountryNetworkCall (HomeNetworkCallBack homeNetworkCallback){
        Call<CountryResponse> call = mealApi.getCountries();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse CountryResponse: CallBack "+response.body().getMeals());
                    homeNetworkCallback.onGetCountrySuccessResult(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailure CountryResponse: CallBack ");
                homeNetworkCallback.onMealDetailsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void makeGetMealsByCategoryNetworkCall(HomeNetworkCallBack homeNetworkCallback, String category){
        Call<MealResponse> call = mealApi.getMealsByCategory(category);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse GetMealsByCategorieNetworkCall: CallBack "+response.body().meals.size());
                    homeNetworkCallback.onGetMealsByCategorieSuccessResult(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailure GetMealsByCategorieNetworkCall: CallBack ");
                homeNetworkCallback.onMealDetailsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void makeGetMealsByCountryNetworkCall (HomeNetworkCallBack homeNetworkCallback, String country){
        Call<MealResponse> call = mealApi.getMealsByCountry(country);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse GetMealsByCountry: CallBack "+response.body().meals.size());
                    homeNetworkCallback.onGetMealsByGetMealsByCountrySuccessResult(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailure GetMealsByCountry: CallBack ");
                homeNetworkCallback.onMealDetailsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void makeGetMealsByFirstLitterNetworkCall (HomeNetworkCallBack homeNetworkCallback, String litter){
        Call<MealResponse> call = mealApi.getMealsByFirstLitter(litter);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse GetMealsByFirstLitterNetworkCall : CallBack "+response.body().meals.size());
                    homeNetworkCallback.onGetMealsByFirstLitterSuccessResult(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onFailure GetMealsByCountry: CallBack ");
                homeNetworkCallback.onMealDetailsFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
}


