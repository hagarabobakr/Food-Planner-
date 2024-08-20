package com.example.foodplanner.presenter.home.getmeal;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.RetrofitClient;
import com.example.foodplanner.view.home.category.GetMealFromAnyWhereView;

import java.util.List;

public class GetMealsFromAnyWherePresenter implements GetMealDetailsNetworkCallBack {
    private GetMealFromAnyWhereView view;
    private RetrofitClient retrofitClient;


    public GetMealsFromAnyWherePresenter(GetMealFromAnyWhereView view, RetrofitClient _retrofitClient) {
        this.view = view;
        retrofitClient = _retrofitClient;
    }
    public void getMealDetails(String id){retrofitClient.makeGetMealDetailsNetworkCall(this,id);}

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

    }
}
