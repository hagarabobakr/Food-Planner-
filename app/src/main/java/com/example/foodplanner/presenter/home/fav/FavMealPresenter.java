package com.example.foodplanner.presenter.home.fav;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealItemEntity;
import com.example.foodplanner.model.database.MealsLocalDataSource;
import com.example.foodplanner.view.home.fragments.fav.FavView;

public class FavMealPresenter {
private FavView favView;
private MealsLocalDataSource mealsLocalDataSource;

    public FavMealPresenter(FavView favView, MealsLocalDataSource mealsLocalDataSource) {
        this.favView = favView;
        this.mealsLocalDataSource = mealsLocalDataSource;
    }
    public void getLocalMeals(){

        favView.showLocalData( mealsLocalDataSource.getAllMeals());
    }
    public  void removeMeal(MealsItem mealItemEntity){
        mealsLocalDataSource.deleteMeal(mealItemEntity);
    }
}
