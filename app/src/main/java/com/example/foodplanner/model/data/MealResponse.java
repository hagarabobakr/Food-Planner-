package com.example.foodplanner.model.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MealResponse {

	@SerializedName("meals")
	public List<MealsItem> meals;

	public List<MealsItem> getMeals(){
		return meals;
	}
}