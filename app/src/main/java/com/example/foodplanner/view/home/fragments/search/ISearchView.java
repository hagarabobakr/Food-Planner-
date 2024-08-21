package com.example.foodplanner.view.home.fragments.search;

import com.example.foodplanner.model.data.MealsItem;

import java.util.List;

public interface ISearchView {
    public void searchResault(List<MealsItem> meals);
    public void onSearchFailure(String msg);
}
