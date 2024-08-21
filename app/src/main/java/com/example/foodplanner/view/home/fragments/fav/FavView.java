package com.example.foodplanner.view.home.fragments.fav;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.database.MealItemEntity;

import java.util.List;

public interface FavView {
    void showLocalData(LiveData<List<MealItemEntity>> allMeals);
}
