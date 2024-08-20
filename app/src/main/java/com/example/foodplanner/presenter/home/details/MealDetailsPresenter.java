package com.example.foodplanner.presenter.home.details;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealItemEntity;
import com.example.foodplanner.model.database.MealsLocalDataSource;
import com.example.foodplanner.view.home.details.MealDetailsView;

public class MealDetailsPresenter {
    private MealDetailsView view;
    private MealsLocalDataSource mealsLocalDataSource;
    public MealDetailsPresenter(MealDetailsView view, MealsLocalDataSource mealDao) {
        this.view = view;
        this.mealsLocalDataSource = mealDao;
    }

    public void addMealToFavorites(MealsItem meal) {
        // Convert MealsItem to MealItemEntity
        MealItemEntity mealEntity = new MealItemEntity(
                0,  // id is auto-generated
                meal.getIdMeal(),
                meal.getStrMeal(),
                meal.getStrMealThumb(),
                meal.getStrCategory(),
                meal.getStrArea(),
                meal.getStrInstructions(),
                convertIngredientsToString(meal),  // Method to convert ingredients list to string
                meal.getStrYoutube()
        );

        mealsLocalDataSource.insertMeal(mealEntity);
    }
    private String convertIngredientsToString(MealsItem meal) {
        // Join all ingredients into a single string
        StringBuilder ingredients = new StringBuilder();
        for (int i = 1; i <= 20; i++) {
            String ingredient = meal.getIngredient(i);
            if (ingredient != null && !ingredient.isEmpty()) {
                if (ingredients.length() > 0) {
                    ingredients.append(", ");
                }
                ingredients.append(ingredient);
            }
        }
        return ingredients.toString();
    }
}
